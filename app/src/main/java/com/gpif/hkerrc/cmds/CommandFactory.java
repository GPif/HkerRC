package com.gpif.hkerrc.cmds;

import com.google.gson.JsonElement;

import java.util.ArrayList;

public class CommandFactory {
    private static ArrayList list = new ArrayList();

    public static Command getCmd(String class_name, String name) {
        WOLCommand wol = new WOLCommand(name);
        if(class_name.equals(wol.getClassName())) {
            return wol;
        }
        return null;
    }

    public static Command getFromJson(String class_name, JsonElement elt) {
        if(class_name.equals((new WOLCommand(null)).getClassName())) {
            return WOLCommand.fromJsonElement(elt);
        }
        return null;
    }
}
