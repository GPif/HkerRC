package com.gpif.hkerrc.cmds;

import com.google.gson.JsonElement;

import java.io.Serializable;

public interface Command extends Serializable{
    String getName();
    Class getConfigActivityClass();
    String getClassName();
    JsonElement getJsonElement();
}
