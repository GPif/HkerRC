package com.gpif.hkerrc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gpif.hkerrc.cmds.CommandsCollection;
import com.gpif.hkerrc.cmds.ssh.SSHCommand;


public class SshConfigActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_ssh_layout);
        Intent sshConfig = getIntent();

        final Integer position = sshConfig.getExtras().getInt("list_position");
        final String action = sshConfig.getStringExtra("action");
        final CommandsCollection values = CommandsCollection.loadConf(this);

        final EditText nameView     = (EditText) findViewById(R.id.nameText);
        final EditText hostView     = (EditText) findViewById(R.id.hostText);
        final EditText portView     = (EditText) findViewById(R.id.portText);
        final EditText userView     = (EditText) findViewById(R.id.userText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText cmdText      = (EditText) findViewById(R.id.cmdText);

        Button saveButton = (Button) findViewById(R.id.save_button) ;

        if (action.equals("edit")) {
            nameView.setText(values.get(position).getName());
            hostView.setText(((SSHCommand) values.get(position)).host);
            userView.setText(((SSHCommand) values.get(position)).user);
            portView.setText(((SSHCommand) values.get(position)).port.toString());
            passwordText.setText(((SSHCommand) values.get(position)).password);
            cmdText.setText(((SSHCommand) values.get(position)).command);
        }

        saveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameView.getText().toString();
                String host = hostView.getText().toString();
                String port = portView.getText().toString();
                String user = userView.getText().toString();
                String password = passwordText.getText().toString();
                String cmd  = cmdText.getText().toString();

                Integer p;
                try {
                    p = Integer.parseInt(port);
                }catch (Exception e) {
                    p = 22;
                }

                SSHCommand sshcmd = new SSHCommand(name,host,p,user,password,cmd);
                if (action.equals("edit")) {
                    values.set(position,sshcmd);
                } else {
                    values.add(sshcmd);
                }
                values.saveConf(getApplicationContext());
                finish();
            }
        });
    }
}
