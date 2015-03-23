package com.gpif.hkerrc.cmds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommandsCollection implements Serializable{
    private List<Command> array = new ArrayList<>();
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

}
