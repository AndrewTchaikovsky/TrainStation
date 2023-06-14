import dao.Database;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws SQLException, IOException {

        Connection connection = Database.getConnection();
        logger.info(connection);

    }
}