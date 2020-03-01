package com.Commands;

import Database.DataStore;

/**
 * Created by chaitu on 15/02/20.
 */
public class Unset implements WriteOperation, Command {

    public String key;

    public Unset(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void execute(DataStore ds) {
        ds.unset(key);
    }
    public CommandType getCommandType() {
        return CommandType.UNSET;
    }
}
