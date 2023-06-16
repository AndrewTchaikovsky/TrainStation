package com.laba.solvd.db.model;

import java.util.List;

public class PlatformType {
    private int id;
    private String type;

    public PlatformType() {
    }

    public PlatformType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlatformType)) return false;

        PlatformType that = (PlatformType) o;

        if (getId() != that.getId()) return false;
        return getType().equals(that.getType());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getType().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PlatformType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
