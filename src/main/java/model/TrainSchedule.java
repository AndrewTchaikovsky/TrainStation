package model;

import java.util.List;

public class TrainSchedule {
    private int id;
    private String date;
    private int stationId;
    private int trainId;
    TrainStation trainStation;
    Train train;

    public TrainSchedule(int id, String date, int stationId, int trainId) {
        this.id = id;
        this.date = date;
        this.stationId = stationId;
        this.trainId = trainId;
    }

    public TrainSchedule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public TrainStation getTrainStation() {
        return trainStation;
    }

    public void setTrainStation(TrainStation trainStation) {
        this.trainStation = trainStation;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainSchedule)) return false;

        TrainSchedule that = (TrainSchedule) o;

        if (getId() != that.getId()) return false;
        if (getStationId() != that.getStationId()) return false;
        if (getTrainId() != that.getTrainId()) return false;
        if (!getDate().equals(that.getDate())) return false;
        if (getTrainStation() != null ? !getTrainStation().equals(that.getTrainStation()) : that.getTrainStation() != null)
            return false;
        return getTrain() != null ? getTrain().equals(that.getTrain()) : that.getTrain() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getStationId();
        result = 31 * result + getTrainId();
        result = 31 * result + (getTrainStation() != null ? getTrainStation().hashCode() : 0);
        result = 31 * result + (getTrain() != null ? getTrain().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TrainSchedule{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", stationId=" + stationId +
                ", trainId=" + trainId +
                ", trainStation=" + trainStation +
                ", train=" + train +
                '}';
    }
}
