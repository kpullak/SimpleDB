package Database;

import com.Commands.WriteOperation;
import com.Commands.*;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by chaitu on 16/02/20.
 */
public class Transaction {

    HashMap<String, String> keyOperations;
    HashSet<String> delKeys;

    public Transaction() {
        keyOperations = new HashMap<String, String>();
        delKeys = new HashSet<String>();
    }

    public HashSet<String> getDelKeys() {
        return delKeys;
    }

    public String getValue(String key) {
        if (keyOperations.containsKey(key)) {
            return keyOperations.get(key);
        } else {
            return null;
        }
    }

    public Transaction(Transaction parent) {
        this.keyOperations = parent.keyOperations;
        this.delKeys = parent.delKeys;
    }

    public boolean containsKey(String key) {
        if (keyOperations.containsKey(key) || delKeys.contains(key)) {
            return true;
        } else {
            return false;
        }
    }

    public HashMap<String, String> getKeyOps() {
        return keyOperations;
    }

    public void applyOperation(WriteOperation op, DataStore ds) {

        String key = op.getKey();
        String value;

        // check if the key is part of the current transaction or not
        if (keyOperations.containsKey(key)) {
            value = ds.get(key);
            if (op instanceof Set) {
                value = ((Set) op).getValue();
                keyOperations.put(key, value);
            } else if (op instanceof Unset) {
                keyOperations.remove(key);
                delKeys.add(key);
            }
        } else if (delKeys.contains(key)) {
            if (op instanceof Set) {
                delKeys.remove(key);
                keyOperations.put(key, ((Set) op).getValue());
            } else if (op instanceof Unset) {
                // pass
            }
        }
    }

    public void commit(DataStore ds) {
        ds.commitTransaction(this);
    }
}