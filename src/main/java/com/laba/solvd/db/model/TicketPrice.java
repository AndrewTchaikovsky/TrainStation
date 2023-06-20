package com.laba.solvd.db.model;

public class TicketPrice {
    private Integer id;
    private double price;

    public TicketPrice() {
    }

    public TicketPrice(Integer id, double price) {
        this.id = id;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketPrice)) return false;

        TicketPrice that = (TicketPrice) o;

        if (getId() != that.getId()) return false;
        return Double.compare(that.getPrice(), getPrice()) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TicketPrice{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
