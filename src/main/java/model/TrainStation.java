package model;

import java.util.List;

public class TrainStation {
    private int id;
    private String name;
    private String location;
    private List<Employee> employees;
    private List<Platform> platforms;
    private List<TrainSchedule> trainSchedules;

    public TrainStation(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
    public TrainStation() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainStation)) return false;

        TrainStation that = (TrainStation) o;

        if (getId() != that.getId()) return false;
        if (!getName().equals(that.getName())) return false;
        return getLocation().equals(that.getLocation());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getLocation().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "model.TrainStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
