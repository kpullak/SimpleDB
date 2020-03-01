package com.Commands;

/**
 * Created by chaitu on 15/02/20.
 */
public class Rollback implements Command {

    public CommandType getCommandType() {
        return CommandType.ROLLBACK;
    }

}
