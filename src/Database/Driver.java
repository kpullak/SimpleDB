package Database;

/**
 * Created by chaitu on 15/02/20.
 */
public class Driver {

    public static void main(String[] args) {
        Database db = new Database(new InMemDB());
        Parser parser = new Parser();
        while (parser.hasCommand()) {
            db.execute(parser.nextCommand());
        }
    }
}
