package com.laba.solvd.db.model;

public class Ticket {
    private int id;
    private int seatNumber;
    private TicketType ticketType;
    private TicketPrice ticketPrice;

    public Ticket() {
    }

    public Ticket(int id, int seatNumber) {
        this.id = id;
        this.seatNumber = seatNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public TicketPrice getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(TicketPrice ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (getId() != ticket.getId()) return false;
        if (getSeatNumber() != ticket.getSeatNumber()) return false;
        if (getTicketType() != null ? !getTicketType().equals(ticket.getTicketType()) : ticket.getTicketType() != null)
            return false;
        return getTicketPrice() != null ? getTicketPrice().equals(ticket.getTicketPrice()) : ticket.getTicketPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getSeatNumber();
        result = 31 * result + (getTicketType() != null ? getTicketType().hashCode() : 0);
        result = 31 * result + (getTicketPrice() != null ? getTicketPrice().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatNumber=" + seatNumber +
                ", ticketType=" + ticketType +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
