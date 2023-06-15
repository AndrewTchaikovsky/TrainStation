package model;

import java.util.List;

public class Passenger {
    private int id;
    private String firstName;
    private String lastName;
    private int trainId;
    Train train;
    List<Ticket> tickets;

    public Passenger(int id, String firstName, String lastName, int trainId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.trainId = trainId;
    }

    public Passenger() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;

        Passenger passenger = (Passenger) o;

        if (getId() != passenger.getId()) return false;
        if (getTrainId() != passenger.getTrainId()) return false;
        if (!getFirstName().equals(passenger.getFirstName())) return false;
        if (!getLastName().equals(passenger.getLastName())) return false;
        if (getTrain() != null ? !getTrain().equals(passenger.getTrain()) : passenger.getTrain() != null) return false;
        return getTickets() != null ? getTickets().equals(passenger.getTickets()) : passenger.getTickets() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getTrainId();
        result = 31 * result + (getTrain() != null ? getTrain().hashCode() : 0);
        result = 31 * result + (getTickets() != null ? getTickets().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", trainId=" + trainId +
                ", train=" + train +
                ", tickets=" + tickets +
                '}';
    }
}
