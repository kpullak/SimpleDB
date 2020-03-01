package com.Commands;

import Database.DataStore;

/**
 * Created by chaitu on 15/02/20.
 */
public interface WriteOperation {

    public void execute(DataStore ds);

    public String getKey();
}
