package model;

public class Ticket {
    private int id;
    private int seatNumber;
    private int typeId;
    private int passengerId;
    TicketType ticketType;
    TicketPrice ticketPrice;
    Passenger passenger;

    public Ticket(int id, int seatNumber, int typeId, int passengerId) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.typeId = typeId;
        this.passengerId = passengerId;
    }

    public Ticket() {
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
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

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (getId() != ticket.getId()) return false;
        if (getSeatNumber() != ticket.getSeatNumber()) return false;
        if (getTypeId() != ticket.getTypeId()) return false;
        if (getPassengerId() != ticket.getPassengerId()) return false;
        if (getTicketType() != null ? !getTicketType().equals(ticket.getTicketType()) : ticket.getTicketType() != null)
            return false;
        if (getTicketPrice() != null ? !getTicketPrice().equals(ticket.getTicketPrice()) : ticket.getTicketPrice() != null)
            return false;
        return getPassenger() != null ? getPassenger().equals(ticket.getPassenger()) : ticket.getPassenger() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getSeatNumber();
        result = 31 * result + getTypeId();
        result = 31 * result + getPassengerId();
        result = 31 * result + (getTicketType() != null ? getTicketType().hashCode() : 0);
        result = 31 * result + (getTicketPrice() != null ? getTicketPrice().hashCode() : 0);
        result = 31 * result + (getPassenger() != null ? getPassenger().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatNumber=" + seatNumber +
                ", typeId=" + typeId +
                ", passengerId=" + passengerId +
                ", ticketType=" + ticketType +
                ", ticketPrice=" + ticketPrice +
                ", passenger=" + passenger +
                '}';
    }
}
