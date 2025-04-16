package org.example.dto;

public class CompanyEmployeeBuildingDto {
    private long companyId;
    private String companyName;
    private long employeeId;
    private String employeeName;
    private long buildingId;
    private long numberOfBuildings;

    public CompanyEmployeeBuildingDto(long companyId, String companyName, long employeeId, String employeeName, long buildingId, long numberOfBuildings) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.buildingId = buildingId;
        this.numberOfBuildings = numberOfBuildings;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    public long getNumberOfBuildings() {
        return numberOfBuildings;
    }

    public void setNumberOfBuildings(long numberOfBuildings) {
        this.numberOfBuildings = numberOfBuildings;
    }

    @Override
    public String toString() {
        if (buildingId == 0) {
            return "CompanyEmployeeBuildingDto{" +
                    "companyId=" + companyId +
                    ", companyName='" + companyName + '\'' +
                    ", employeeId=" + employeeId +
                    ", employeeName='" + employeeName + '\'' +
                    ", numberOfBuildings=" + numberOfBuildings +
                    '}';
        } else {
            return "CompanyEmployeeBuildingDto{" +
                    "companyId=" + companyId +
                    ", companyName='" + companyName + '\'' +
                    ", employeeId=" + employeeId +
                    ", employeeName='" + employeeName + '\'' +
                    ", buildingId=" + buildingId +
                    '}';
        }

    }
}
