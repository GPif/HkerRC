package com.gpif.hkerrc.cmds;

import android.app.Activity;
import android.os.AsyncTask;

public interface Commands {
    String getName();
    Activity getConfigActivity();
    //String as parameters, Float between 0 and 1 as progress if any, Integer as retrun
    AsyncTask<String,Float,Integer> getCmdAsyncTask();
}
