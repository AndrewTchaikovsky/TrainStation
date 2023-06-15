package model;

import java.util.List;

public class TicketType {
    private int id;
    private String type;
    List<Ticket> tickets;

    public TicketType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public TicketType() {
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketType)) return false;

        TicketType that = (TicketType) o;

        if (getId() != that.getId()) return false;
        if (!getType().equals(that.getType())) return false;
        return getTickets() != null ? getTickets().equals(that.getTickets()) : that.getTickets() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getType().hashCode();
        result = 31 * result + (getTickets() != null ? getTickets().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TicketType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}
