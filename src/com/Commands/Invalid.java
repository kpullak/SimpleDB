package com.Commands;

/**
 * Created by chaitu on 15/02/20.
 */
public class Invalid implements Command {

    public CommandType getCommandType() {
        return CommandType.INVALID;
    }
}
