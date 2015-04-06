package com.gpif.hkerrc.cmds;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandFactory {
    private static ArrayList list;

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

    public static ArrayList getHandleCmd() {
        initClass();
        return list;
    }

    private static void initClass() {
        list = new ArrayList();
        HashMap mMap = new HashMap();
        WOLCommand wol = new WOLCommand(null);
        mMap.put("name","Wake On LAN command");
        mMap.put("class_name",wol.getClassName());
        list.add(mMap);
    }
}
