package model;

import java.util.Date;
import java.util.List;

public class TrainMaintenance {
    private int id;
    private Date date;
    private String type;
    private int trainId;
    Train train;
    List<Employee> employees;

    public TrainMaintenance(int id, Date date, String type, int trainId) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.trainId = trainId;
    }

    public TrainMaintenance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainMaintenance)) return false;

        TrainMaintenance that = (TrainMaintenance) o;

        if (getId() != that.getId()) return false;
        if (getTrainId() != that.getTrainId()) return false;
        if (!getDate().equals(that.getDate())) return false;
        if (!getType().equals(that.getType())) return false;
        if (getTrain() != null ? !getTrain().equals(that.getTrain()) : that.getTrain() != null) return false;
        return getEmployees() != null ? getEmployees().equals(that.getEmployees()) : that.getEmployees() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + getTrainId();
        result = 31 * result + (getTrain() != null ? getTrain().hashCode() : 0);
        result = 31 * result + (getEmployees() != null ? getEmployees().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TrainMaintenance{" +
                "id=" + id +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", trainId=" + trainId +
                ", train=" + train +
                ", employees=" + employees +
                '}';
    }
}
