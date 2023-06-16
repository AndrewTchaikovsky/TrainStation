package com.laba.solvd.db.model;

import java.util.List;

public class Train {
    private int id;
    private String name;
    private String type;
    List<TrainSchedule> trainSchedules;
    List<TrainMaintenance> trainMaintenances;
    List<Passenger> passengers;

    public Train() {
    }

    public Train(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TrainSchedule> getTrainSchedules() {
        return trainSchedules;
    }

    public void setTrainSchedules(List<TrainSchedule> trainSchedules) {
        this.trainSchedules = trainSchedules;
    }

    public List<TrainMaintenance> getTrainMaintenances() {
        return trainMaintenances;
    }

    public void setTrainMaintenances(List<TrainMaintenance> trainMaintenances) {
        this.trainMaintenances = trainMaintenances;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Train)) return false;

        Train train = (Train) o;

        if (getId() != train.getId()) return false;
        if (!getName().equals(train.getName())) return false;
        if (!getType().equals(train.getType())) return false;
        if (getTrainSchedules() != null ? !getTrainSchedules().equals(train.getTrainSchedules()) : train.getTrainSchedules() != null)
            return false;
        if (getTrainMaintenances() != null ? !getTrainMaintenances().equals(train.getTrainMaintenances()) : train.getTrainMaintenances() != null)
            return false;
        return getPassengers() != null ? getPassengers().equals(train.getPassengers()) : train.getPassengers() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + (getTrainSchedules() != null ? getTrainSchedules().hashCode() : 0);
        result = 31 * result + (getTrainMaintenances() != null ? getTrainMaintenances().hashCode() : 0);
        result = 31 * result + (getPassengers() != null ? getPassengers().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", trainSchedules=" + trainSchedules +
                ", trainMaintenances=" + trainMaintenances +
                ", passengers=" + passengers +
                '}';
    }
}
