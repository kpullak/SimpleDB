package com.Commands;

/**
 * Created by chaitu on 15/02/20.
 */

import Database.DataStore;

public class Get implements Command {

    String key;

    // this is not a regular getter method, this is an instantiating object to this class
    public Get(String key) {
        this.key = key;
    }

    public CommandType getCommandType() {
        return CommandType.GET;
    }

    public String getVal(DataStore ds) {
        return ds.get(key);
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}