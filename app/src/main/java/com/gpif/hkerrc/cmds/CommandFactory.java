package com.gpif.hkerrc.cmds;

import com.google.gson.JsonElement;
import com.gpif.hkerrc.cmds.ssh.SSHCommand;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandFactory {
    private static ArrayList list;

    public static Command getCmd(String class_name, String name) {
        WOLCommand wol = new WOLCommand(name);
        if(class_name.equals(wol.getClassName())) {
            return wol;
        }
        SSHCommand ssh = new SSHCommand();
        if(class_name.equals(ssh.getClassName())) {
            return ssh;
        }
        return null;
    }

    public static Command getFromJson(String class_name, JsonElement elt) {
        if(class_name.equals((new WOLCommand(null)).getClassName())) {
            return WOLCommand.fromJsonElement(elt);
        }
        if(class_name.equals((new SSHCommand()).getClassName())) {
            return SSHCommand.fromJsonElement(elt);
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
        HashMap mMap2 = new HashMap();
        SSHCommand ssh = new SSHCommand();
        mMap2.put("name", "SSH command");
        mMap2.put("class_name",ssh.getClassName());
        list.add(mMap2);
    }
}
