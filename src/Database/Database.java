package Database;

import com.Commands.Command;
import com.Commands.Set;
import com.Commands.Get;
import com.Commands.Unset;
import com.Commands.NumEqualTo;
import com.Commands.WriteOperation;

import java.util.Stack;

/**
 * The Database class contains the data store (In-memory), state of current and nested transactions.
 * It accepts commands and executes them.
 *
 * @author krishna
 *
 */

public class Database {
    DataStore ds;
    Transaction ts;
    Stack<Transaction> transStack;

    public Database(DataStore ds) {
        this.ds = ds;
        ts = null;
        transStack = new Stack<Transaction>();
    }

    public void execute(Command cmd) {
        switch (cmd.getCommandType()) {
            case SET:
                set(cmd);
                break;
            case GET:
                get(cmd);
                break;
            case UNSET:
                unset(cmd);
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
            case INVALID:
                System.out.println("Invalid Command");
        }
    }


    public void set(Command cmd) {
        if (ts == null)
            ((Set) cmd).execute(ds);
        else
            ts.applyOperation((WriteOperation) cmd, ds);
    }

    public void get(Command cmd) {
        String value;
        if (ts == null)
            value = ((Get) cmd).getVal(ds);
        else {
            if (ts.containsKey(((Get) cmd).getKey()))
                value = ts.getValue(((Get) cmd).getKey());
            else
                value = ((Get) cmd).getVal(ds);
        }
        if (value == null)
            System.out.println("NULL");
        else
            System.out.println(value);
    }

    public void unset(Command cmd) {
        if (ts == null)
            ((Unset) cmd).execute(ds);
        else
            ts.applyOperation((WriteOperation) cmd, ds);
    }

    public void commit() {
        if (ts == null)
            System.out.println("NO TRANSACTION");
        else {
            ds.commitTransaction(ts);
            ts = null;
            transStack.clear();
        }
    }

    public void rollback() {
        if (ts != null) {
            ts = null;
            if (!transStack.isEmpty()) {
                ts = transStack.pop();
            } else {
                System.out.println("NO TRANSACTION");
                return;
            }
        } else {
            System.out.println("NO TRANSACTION");
            return;
        }
        System.out.println("Rolled back the transaction successfully!");
    }

    public void begin() {
        if (ts != null) {
            transStack.push(ts);
            ts = new Transaction(transStack.peek());
        } else {
            ts = new Transaction();
        }
    }

    public void end() {
        System.exit(0);
    }
}
