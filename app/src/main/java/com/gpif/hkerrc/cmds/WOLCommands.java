package com.gpif.hkerrc.cmds;

import android.app.Activity;
import android.os.AsyncTask;

import com.gpif.hkerrc.utils.WOL;

public class WOLCommands implements Commands {
    public String name;
    public String ip;
    public String mac;

    private AsyncTask<String, Float, Integer> task;

    private class RunWol extends AsyncTask<String, Float, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            WOL wol = new WOL(params[0],params[1],params[2]);
            try {
                wol.send();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }

    }


    public WOLCommands(String name, String ip, String mac) {
        this.name = name;
        this.ip = ip;
        this.mac = mac;
    }

    public WOLCommands(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Activity getConfigActivity() {
        return null;
    }

    @Override
    public AsyncTask<String, Float, Integer> getCmdAsyncTask() {
        task  = new RunWol();
        return task;
    }
}
