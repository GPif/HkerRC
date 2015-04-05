package com.gpif.hkerrc.cmds;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.gpif.hkerrc.WolConfigActivity;

import java.lang.reflect.Type;

public class WOLCommand implements Command {
    public String name;
    public String ip;
    public String mac;

    public WOLCommand(String name, String ip, String mac) {
        this.name = name;
        this.ip = ip;
        this.mac = mac;
    }

    public WOLCommand(String name) {
        this.name = name;
    }

    public static WOLCommand fromJsonElement(JsonElement jso) {
        Gson g = new Gson();
        Type wolType = new TypeToken<WOLCommand>() {}.getType();
        return ((WOLCommand) g.fromJson(jso,wolType ));
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public JsonElement getJsonElement() {
        Gson g = new Gson();
        return g.toJsonTree(this);
    }

    @Override
    public String getClassName() {
        return "WOLCommand";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class getConfigActivityClass() {
        return WolConfigActivity.class;
    }
}
