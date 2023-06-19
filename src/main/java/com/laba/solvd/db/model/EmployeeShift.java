package com.laba.solvd.db.model;

import java.util.Date;

public class EmployeeShift {
    private int id;
    private Date startDate;
    private Date endDate;

    public EmployeeShift() {
    }

    public EmployeeShift(int id, Date startDate, Date endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeShift)) return false;

        EmployeeShift that = (EmployeeShift) o;

        if (getId() != that.getId()) return false;
        if (!getStartDate().equals(that.getStartDate())) return false;
        return getEndDate().equals(that.getEndDate());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getStartDate().hashCode();
        result = 31 * result + getEndDate().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EmployeeShift{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}