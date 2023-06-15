package model;

import java.util.List;

public class Platform {
    private int id;
    private int number;
    private int stationId;
    private int typeId;
    PlatformType platformTypes;
    TrainStation trainStation;

    public Platform(int id, int number, int stationId, int typeId) {
        this.id = id;
        this.number = number;
        this.stationId = stationId;
        this.typeId = typeId;
    }

    public Platform() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public PlatformType getPlatformTypes() {
        return platformTypes;
    }

    public void setPlatformTypes(PlatformType platformTypes) {
        this.platformTypes = platformTypes;
    }

    public TrainStation getTrainStation() {
        return trainStation;
    }

    public void setTrainStation(TrainStation trainStation) {
        this.trainStation = trainStation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Platform)) return false;

        Platform platform = (Platform) o;

        if (getId() != platform.getId()) return false;
        if (getNumber() != platform.getNumber()) return false;
        if (getStationId() != platform.getStationId()) return false;
        if (getTypeId() != platform.getTypeId()) return false;
        if (getPlatformTypes() != null ? !getPlatformTypes().equals(platform.getPlatformTypes()) : platform.getPlatformTypes() != null)
            return false;
        return getTrainStation() != null ? getTrainStation().equals(platform.getTrainStation()) : platform.getTrainStation() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getNumber();
        result = 31 * result + getStationId();
        result = 31 * result + getTypeId();
        result = 31 * result + (getPlatformTypes() != null ? getPlatformTypes().hashCode() : 0);
        result = 31 * result + (getTrainStation() != null ? getTrainStation().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "id=" + id +
                ", number=" + number +
                ", stationId=" + stationId +
                ", typeId=" + typeId +
                ", platformTypes=" + platformTypes +
                ", trainStation=" + trainStation +
                '}';
    }
}
