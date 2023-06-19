package com.laba.solvd.db.model;

import java.util.List;

public class TrainStation {
    private int id;
    private String name;
    private String location;
    private List<Employee> employees;
    private List<Platform> platforms;
    private List<TrainSchedule> trainSchedules;

    public TrainStation() {
    }

    public TrainStation(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public TrainStation(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public List<TrainSchedule> getTrainSchedules() {
        return trainSchedules;
    }

    public void setTrainSchedules(List<TrainSchedule> trainSchedules) {
        this.trainSchedules = trainSchedules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainStation)) return false;

        TrainStation that = (TrainStation) o;

        if (getId() != that.getId()) return false;
        if (!getName().equals(that.getName())) return false;
        if (!getLocation().equals(that.getLocation())) return false;
        if (getEmployees() != null ? !getEmployees().equals(that.getEmployees()) : that.getEmployees() != null)
            return false;
        if (getPlatforms() != null ? !getPlatforms().equals(that.getPlatforms()) : that.getPlatforms() != null)
            return false;
        return getTrainSchedules() != null ? getTrainSchedules().equals(that.getTrainSchedules()) : that.getTrainSchedules() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getLocation().hashCode();
        result = 31 * result + (getEmployees() != null ? getEmployees().hashCode() : 0);
        result = 31 * result + (getPlatforms() != null ? getPlatforms().hashCode() : 0);
        result = 31 * result + (getTrainSchedules() != null ? getTrainSchedules().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TrainStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", employees=" + employees +
                ", platforms=" + platforms +
                ", trainSchedules=" + trainSchedules +
                '}';
    }
}
