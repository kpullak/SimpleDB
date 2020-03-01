package Database;

import java.util.ArrayList;

/**
 * Created by chaitu on 15/02/20.
 */
public interface DataStore {

    public void set(String key, String val);
    public void commitTransaction(Transaction ts);
    public String get(String key);
    public void unset(String key);
    public int numEqualTo(String value);

}
