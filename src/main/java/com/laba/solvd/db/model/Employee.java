package com.laba.solvd.db.model;

import java.util.List;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String position;
    List<EmployeeShift> employeeShifts;
    List<TrainMaintenance> trainMaintenances;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, String position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<EmployeeShift> getEmployeeShifts() {
        return employeeShifts;
    }

    public void setEmployeeShifts(List<EmployeeShift> employeeShifts) {
        this.employeeShifts = employeeShifts;
    }

    public List<TrainMaintenance> getTrainMaintenances() {
        return trainMaintenances;
    }

    public void setTrainMaintenances(List<TrainMaintenance> trainMaintenances) {
        this.trainMaintenances = trainMaintenances;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (getId() != employee.getId()) return false;
        if (!getFirstName().equals(employee.getFirstName())) return false;
        if (!getLastName().equals(employee.getLastName())) return false;
        if (!getPosition().equals(employee.getPosition())) return false;
        if (getEmployeeShifts() != null ? !getEmployeeShifts().equals(employee.getEmployeeShifts()) : employee.getEmployeeShifts() != null)
            return false;
        return getTrainMaintenances() != null ? getTrainMaintenances().equals(employee.getTrainMaintenances()) : employee.getTrainMaintenances() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getPosition().hashCode();
        result = 31 * result + (getEmployeeShifts() != null ? getEmployeeShifts().hashCode() : 0);
        result = 31 * result + (getTrainMaintenances() != null ? getTrainMaintenances().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", employeeShifts=" + employeeShifts +
                ", trainMaintenances=" + trainMaintenances +
                '}';
    }
}
