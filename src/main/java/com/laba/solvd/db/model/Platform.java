package com.laba.solvd.db.model;

public class Platform {
    private Integer id;
    private int number;
    private PlatformType platformTypes;

    public Platform() {
    }

    public Platform(Integer id, int number) {
        this.id = id;
        this.number = number;
    }

    public Platform(int number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public PlatformType getPlatformType() {
        return platformTypes;
    }

    public void setPlatformType(PlatformType platformTypes) {
        this.platformTypes = platformTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Platform)) return false;

        Platform platform = (Platform) o;

        if (getId() != platform.getId()) return false;
        if (getNumber() != platform.getNumber()) return false;
        return getPlatformType() != null ? getPlatformType().equals(platform.getPlatformType()) : platform.getPlatformType() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getNumber();
        result = 31 * result + (getPlatformType() != null ? getPlatformType().hashCode() : 0);
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
