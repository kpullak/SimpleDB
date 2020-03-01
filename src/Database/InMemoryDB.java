package Database;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Created by chaitu on 16/02/20.
 */
public class InMemoryDB implements DataStore {

    HashMap<String, String> store;

    public InMemoryDB() {
        store = new HashMap<String, String>();
    }

    @Override
    public void set(String key, String val) {
        store.put(key, val);
    }

    @Override
    public void commitTransaction(Transaction ts) {
        if (ts != null) {
            for (String key : ts.getDelKeys()) {
                unset(key);
            } for (Entry<String, String> entry : ts.getKeyOps().entrySet()) {
                set(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public String get(String key) {
        if (store.containsKey(key)) {
            return store.get(key);
        } else {
            return null;
        }
    }

    @Override
    public void unset(String key) {
        if (store.containsKey(key)) {
            store.remove(key);
        } else {
            System.out.println("Key not found!");
            return;
        }
    }

    @Override
    public int numEqualTo(String value) {
        return 0;
    }
}
