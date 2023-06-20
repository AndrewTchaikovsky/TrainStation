package com.laba.solvd.db.model;

import java.util.Date;
import java.util.List;

public class TrainMaintenance {
    private Integer id;
    private Date date;
    private String type;

    public TrainMaintenance() {
    }

    public TrainMaintenance(Integer id, Date date, String type) {
        this.id = id;
        this.date = date;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        if (!(o instanceof TrainMaintenance)) return false;

        TrainMaintenance that = (TrainMaintenance) o;

        if (getId() != that.getId()) return false;
        if (!getDate().equals(that.getDate())) return false;
        return getType().equals(that.getType());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getType().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TrainMaintenance{" +
                "id=" + id +
                ", date=" + date +
                ", type='" + type + '\'' +
                '}';
    }
}
