package com.Commands;

/**
 * Created by chaitu on 15/02/20.
 */
public class Commit implements Command {

    public CommandType getCommandType() {
        return CommandType.COMMIT;
    }
}
