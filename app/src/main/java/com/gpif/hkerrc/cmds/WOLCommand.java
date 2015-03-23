package com.gpif.hkerrc.cmds;

import com.gpif.hkerrc.WolConfigActivity;

import java.io.Serializable;

public class WOLCommand implements Command, Serializable {
    public String name;
    public String ip;
    public String mac;

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

    public WOLCommand(String name, String ip, String mac) {
        this.name = name;
        this.ip = ip;
        this.mac = mac;
    }

    public WOLCommand(String name) {
        this.name = name;
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
