import dao.Database;
import dao.TrainStationDAO;
import org.apache.log4j.Logger;
import service.TrainStationService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = Database.getConnection();
        logger.info(connection);

//        TrainStationService ts = new TrainStationService();
//        ts.getTrainStation(1);

        TrainStationDAO trainStationDAO = new TrainStationDAO();
        logger.info(trainStationDAO.get(1));

    }
}