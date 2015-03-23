package com.gpif.hkerrc.cmds;

import java.io.Serializable;

public interface Command extends Serializable{
    String getName();
    Class getConfigActivityClass();
}
