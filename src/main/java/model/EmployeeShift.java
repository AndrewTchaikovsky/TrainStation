package model;

import java.util.Date;

public class EmployeeShift {
    private int id;
    private Date startDate;
    private Date endDate;
    private int employeeId;
    Employee employee;

    public EmployeeShift(int id, Date startDate, Date endDate, int employeeId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employeeId = employeeId;
    }

    public EmployeeShift() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeShift)) return false;

        EmployeeShift that = (EmployeeShift) o;

        if (getId() != that.getId()) return false;
        if (getEmployeeId() != that.getEmployeeId()) return false;
        if (!getStartDate().equals(that.getStartDate())) return false;
        if (!getEndDate().equals(that.getEndDate())) return false;
        return getEmployee() != null ? getEmployee().equals(that.getEmployee()) : that.getEmployee() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getStartDate().hashCode();
        result = 31 * result + getEndDate().hashCode();
        result = 31 * result + getEmployeeId();
        result = 31 * result + (getEmployee() != null ? getEmployee().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EmployeeShift{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", employeeId=" + employeeId +
                ", employee=" + employee +
                '}';
    }
}
