package model;

public class TicketPrice {
    private int id;
    private double price;
    private int ticketId;

    public TicketPrice(int id, double price, int ticketId) {
        this.id = id;
        this.price = price;
        this.ticketId = ticketId;
    }

    public TicketPrice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketPrice)) return false;

        TicketPrice that = (TicketPrice) o;

        if (getId() != that.getId()) return false;
        if (Double.compare(that.getPrice(), getPrice()) != 0) return false;
        return getTicketId() == that.getTicketId();
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getTicketId();
        return result;
    }

    @Override
    public String toString() {
        return "TicketPrice{" +
                "id=" + id +
                ", price=" + price +
                ", ticketId=" + ticketId +
                '}';
    }
}
