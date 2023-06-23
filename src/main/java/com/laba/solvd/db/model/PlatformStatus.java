package com.laba.solvd.db.model;

import java.util.List;

public class PlatformStatus {
    private Integer id;
    private String status;

    public PlatformStatus() {
    }

    public PlatformStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlatformStatus)) return false;

        PlatformStatus that = (PlatformStatus) o;

        if (!getId().equals(that.getId())) return false;
        return getStatus().equals(that.getStatus());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getStatus().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PlatformStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
