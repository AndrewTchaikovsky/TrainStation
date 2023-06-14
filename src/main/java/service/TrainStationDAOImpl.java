package service;

import dao.Database;
import dao.TrainStationDAO;
import model.TrainStation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrainStationDAOImpl implements TrainStationDAO {

    @Override
    public TrainStation get(int id) throws SQLException, IOException {
        Connection con = Database.getConnection();
        TrainStation trainStation = null;
        String sql = "SELECT id, name, location FROM train_stations WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            int trainStationId = rs.getInt("employeeId");
            String name = rs.getString("name");
            String location = rs.getString("location");

           trainStation = new TrainStation(trainStationId, name, location);
        }
        return trainStation;
    }

    @Override
    public List<TrainStation> getAll() throws SQLException {
        return null;
    }

    @Override
    public int save(TrainStation trainStation) throws SQLException {
        return 0;
    }

    @Override
    public int insert(TrainStation trainStation) throws SQLException, IOException {
        Connection con = Database.getConnection();

        String sql = "INSERT INTO train_stations (name, location) VALUES (?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, trainStation.getName());
        ps.setString(2, trainStation.getLocation());

        int result = ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return result;
    }

    @Override
    public int update(TrainStation trainStation) throws SQLException, IOException {
        Connection connection = Database.getConnection();

        String sql = "UPDATE train_stations SET name = ?, location = ? WHERE id = ?";

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, trainStation.getName());
        ps.setString(2, trainStation.getLocation());

        int result = ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(connection);

        return result;
    }

    @Override
    public int delete(TrainStation trainStation) throws SQLException, IOException {
        Connection connection = Database.getConnection();

        String sql = "DELETE FROM train_stations WHERE id = ?";

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, trainStation.getId());

        int result = ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(connection);

        return result;
    }
}
