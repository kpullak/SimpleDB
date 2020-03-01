package com.Commands;

import Database.DataStore;

/**
 * Created by chaitu on 15/02/20.
 */
public class Set implements WriteOperation, Command{

    String key;
    String val;

    public void execute(DataStore ds) {
        ds.set(key, val);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Set(String key, String val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.SET;
    }
}
