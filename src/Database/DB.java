package Database;

import com.Commands.*;

import java.util.Stack;

/**
 * Created by chaitu on 16/02/20.
 */
public class DB {
    DataStore ds;
    Transaction ts;
    Stack<Transaction> transactionStack;

    public DB (DataStore ds) {
        this.ds = ds;
        ts = null;
    }

    public void execute(Command cmd) {
        switch (cmd.getCommandType()) {
            case SET:
                set(cmd);
                break;
            case UNSET:
                unset(cmd);
                break;
            case GET:
                get(cmd);
                break;
            case BEGIN:
                begin();
                break;
            case COMMIT:
                commit();
                break;
            case ROLLBACK:
                rollback();
                break;
            case END:
                end();
                break;
        }
    }

    public void commit() {
        if (ts == null) {
            System.out.println("Nothing to commit!");
        } else {
            ds.commitTransaction(ts);
            ts = null;
        }
    }

    public void end() {
        System.exit(0);
    }

    public void rollback() {
        if (ts == null) {
            System.out.println("Nothing to commit!");
        } else {
            ts = null;
            System.out.println("Rolled back successfully!");
        }
    }

    public void begin() {
        if (ts == null) {
            ts = new Transaction();
        } else {
            transactionStack.push(ts);
            ts = new Transaction(transactionStack.peek());
        }
    }

    public String get(Command cmd) {
        if (ts == null) {
            return ((Get) cmd).getVal(ds);
        } else {
            if (ts.containsKey(((Get) cmd).getKey())) {
                return ts.getValue(((Get) cmd).getKey());
            } else {
                return ((Get) cmd).getVal(ds);
            }
        }
    }

    public void set(Command cmd) {
        if (ts == null) {
            ((Set) cmd).execute(ds);
        } else {
            ts.applyOperation((WriteOperation) cmd, ds);
        }
    }

    public void unset(Command cmd) {
        if (ts == null) {
            ((Unset) cmd).execute(ds);
        } else {
            ts.applyOperation((WriteOperation) cmd, ds);
        }
    }
}
