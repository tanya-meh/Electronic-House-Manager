package org.example.dto;

public class EmployeeNameNumBuildingsDto {
    private long employeeId;
    private String employeeName;
    private long numBuildings;
    private long companyId;

    public EmployeeNameNumBuildingsDto(long employeeId, String employeeName, long numBuildings, long companyId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.numBuildings = numBuildings;
        this.companyId = companyId;
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

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "EmployeeNameNumBuildingsDto{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", numBuildings=" + numBuildings +
                ", companyId=" + companyId +
                '}';
    }
}
