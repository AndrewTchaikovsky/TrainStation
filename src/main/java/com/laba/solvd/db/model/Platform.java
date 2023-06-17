package com.laba.solvd.db.model;

public class Platform {
    private int id;
    private int number;
    private PlatformType platformTypes;

    public Platform() {
    }

    public Platform(int id, int number) {
        this.id = id;
        this.number = number;
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

    public PlatformType getPlatformTypes() {
        return platformTypes;
    }

    public void setPlatformTypes(PlatformType platformTypes) {
        this.platformTypes = platformTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Platform)) return false;

        Platform platform = (Platform) o;

        if (getId() != platform.getId()) return false;
        if (getNumber() != platform.getNumber()) return false;
        return getPlatformTypes() != null ? getPlatformTypes().equals(platform.getPlatformTypes()) : platform.getPlatformTypes() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getNumber();
        result = 31 * result + (getPlatformTypes() != null ? getPlatformTypes().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "id=" + id +
                ", number=" + number +
                ", platformTypes=" + platformTypes +
                '}';
    }
}
