package org.example.dto;

public class EmployeeNameNumBuildingsDto {
    private long employeeId;
    private String employeeName;
    private long numBuildings;

    public EmployeeNameNumBuildingsDto(long employeeId, String employeeName, long numBuildings) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.numBuildings = numBuildings;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public long getNumBuildings() {
        return numBuildings;
    }

    public void setNumBuildings(long numBuildings) {
        this.numBuildings = numBuildings;
    }

    @Override
    public String toString() {
        return "EmployeeNameNumBuildingsDto{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", numBuildings=" + numBuildings +
                '}';
    }
}
