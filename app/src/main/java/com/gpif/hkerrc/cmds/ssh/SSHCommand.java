package com.gpif.hkerrc.cmds.ssh;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.gpif.hkerrc.SshConfigActivity;
import com.gpif.hkerrc.cmds.Command;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.Properties;

import android.util.Log;

public class SSHCommand implements Command {

    public String name;
    public String host;
    public Integer port;
    public String user;
    public String password;
    public String command;

    public SSHCommand(String name, String host, Integer port,
                      String user, String password, String command){
        this.host = host;
        this.name = name;
        this.port = port;
        this.user = user;
        this.password = password;
        this.command = command;
    }

    public SSHCommand() {
        this.host = "";
        this.name = "";
        this.port = 22;
        this.user = "";
        this.password = "";
        this.command = "";
    }

    public static SSHCommand fromJsonElement(JsonElement jso) {
        Gson g = new Gson();
        Type wolType = new TypeToken<SSHCommand>() {}.getType();
        return ((SSHCommand) g.fromJson(jso,wolType ));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Class getConfigActivityClass() {
        return SshConfigActivity.class;
    }

    @Override
    public String getClassName() {
        return "SSHCommand";
    }

    @Override
    public JsonElement getJsonElement() {
        Gson g = new Gson();
        return g.toJsonTree(this);
    }

    @Override
    public void run() {
        new RunSSHCmd().execute(this.user, this.password, this.host, this.command);
    }

    private class RunSSHCmd extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {
                executeRemoteCommand(params[0], params[1], params[2], 22, params[3]);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        public String executeRemoteCommand(
                String username,
                String password,
                String hostname,
                int port,
                String command) throws Exception {

            JSch jsch = new JSch();
            Session session = jsch.getSession(username, hostname, 22);
            session.setPassword(password);

            // Avoid asking for key confirmation
            Properties prop = new Properties();
            prop.put("StrictHostKeyChecking", "no");
            Log.d("SSH","Send ssh command");
            session.setConfig(prop);

            session.connect();

            // SSH Channel
            ChannelExec channelssh = (ChannelExec)
                    session.openChannel("exec");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            channelssh.setOutputStream(baos);

            // Execute command
            channelssh.setCommand(command);
            channelssh.connect();
            channelssh.disconnect();

            return baos.toString();
        }
    }
}
