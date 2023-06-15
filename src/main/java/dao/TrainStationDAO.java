package dao;


import model.TrainStation;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainStationDAO implements DAO<TrainStation> {

    @Override
    public TrainStation get(int id) throws SQLException, IOException {
        Connection connection = Database.getConnection();
        TrainStation trainStation = null;
        String sql = "SELECT id, name, location FROM train_stations WHERE id = ?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int trainStationId = rs.getInt("id");
            String name = rs.getString("name");
            String location = rs.getString("location");

            trainStation = new TrainStation(trainStationId, name, location);
        }
        return trainStation;
    }

    @Override
    public List<TrainStation> getAll() throws SQLException {
        TrainStation trainStation;
        try (Connection connection = Database.getConnection()) {
            List<TrainStation> trainStations = new ArrayList<>();
            String sql = "SELECT id, name, location FROM train_stations";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String location = rs.getString("location");

                    trainStation = new TrainStation(id, name, location);
                    trainStations.add(trainStation);
                }
            }
            return trainStations;
        }
    }

    @Override
    public void save(TrainStation trainStation) throws SQLException {
        String sql = "INSERT INTO train_stations (name, location) VALUES (?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, trainStation.getName());
            ps.setString(2, trainStation.getLocation());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TrainStation trainStation) throws SQLException, IOException {
        String sql = "UPDATE train_stations SET name = ?, location = ? WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, trainStation.getName());
            ps.setString(2, trainStation.getLocation());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(TrainStation trainStation) throws SQLException, IOException {
        String sql = "DELETE FROM train_stations WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, trainStation.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
