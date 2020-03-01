package com.Commands;

/**
 * Created by chaitu on 15/02/20.
 */

import Database.DataStore;

public class NumEqualTo implements Command {

    public CommandType getCommandType() {
        return CommandType.ROLLBACK;
    }

    String value;

    public String getValue(){
        return value;
    }

    public NumEqualTo(String value){
        this.value = value;
    }

    public int getCount(DataStore ds){
        return ds.numEqualTo(value);
    }
}
