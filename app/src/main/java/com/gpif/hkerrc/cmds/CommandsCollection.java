package com.gpif.hkerrc.cmds;

import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommandsCollection implements Serializable{
    private List<Command> array = new ArrayList<>();
    private static String confFile =  "hkerrc.cfg";

    public CommandsCollection() {
    }

    public List<Command> getArray() {
        return array;
    }

    public void setArray(List<Command> array) {
        this.array = array;
    }

    public void add(Command cmd) {
        this.array.add(cmd);
    }

    public void remove(Integer position) {
        this.array.remove((int) position);
    }

    public Command get(Integer position){
        return this.array.get(position);
    }

    public void set(Integer position, Command cmd) {
        this.array.set(position, cmd);
    }

    public void saveConf(Context c) {
        if (isExternalStorageWritable()){
            //Convert to json string
            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(CommandsCollection.class, new ComCollSerializer());
            gson.registerTypeAdapter(CommandsCollection.class, new ComCollDeserializer());
            Gson g = gson.create();
            String json = g.toJson(this);

            File file = new File(c.getExternalFilesDir(null), confFile);
            try {
                FileOutputStream os = new FileOutputStream(file);
                os.write(json.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static CommandsCollection loadConf(Context c) {
        return null;
    }

    /* Checks if external storage is available for read and write */
    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

}
