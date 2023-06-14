package model;

import java.util.List;

public class Platform {
    private int id;
    private int number;
    private int stationId;
    private int typeId;
    private List<PlatformType> platformTypes;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Platform)) return false;

        Platform platform = (Platform) o;

        if (getId() != platform.getId()) return false;
        if (getNumber() != platform.getNumber()) return false;
        if (getStationId() != platform.getStationId()) return false;
        return getTypeId() == platform.getTypeId();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getNumber();
        result = 31 * result + getStationId();
        result = 31 * result + getTypeId();
        return result;
    }

    @Override
    public String toString() {
        return "Platforms{" +
                "id=" + id +
                ", number=" + number +
                ", stationId=" + stationId +
                ", typeId=" + typeId +
                '}';
    }
}
