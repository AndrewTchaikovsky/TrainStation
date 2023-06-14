package model;

public class TrainSchedule {
    private int id;
    private String date;
    private int trainId;
    private int stationId;

    public TrainSchedule(int id, String date, int trainId, int stationId) {
        this.id = id;
        this.date = date;
        this.trainId = trainId;
        this.stationId = stationId;
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

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainSchedule)) return false;

        TrainSchedule that = (TrainSchedule) o;

        if (getId() != that.getId()) return false;
        if (getTrainId() != that.getTrainId()) return false;
        if (getStationId() != that.getStationId()) return false;
        return getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getTrainId();
        result = 31 * result + getStationId();
        return result;
    }

    @Override
    public String toString() {
        return "TrainSchedules{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", trainId=" + trainId +
                ", stationId=" + stationId +
                '}';
    }
}
