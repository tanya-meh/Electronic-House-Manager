package org.example.dto;

public class ResidentNameAgeBuildingDto {
    private long residentId;
    private String residentName;
    private int age;
    private long buildingId;

    public ResidentNameAgeBuildingDto(long residentId, String residentName, int age, long buildingId) {
        this.residentId = residentId;
        this.residentName = residentName;
        this.age = age;
        this.buildingId = buildingId;
    }

    public long getResidentId() {
        return residentId;
    }

    public void setResidentId(long residentId) {
        this.residentId = residentId;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    @Override
    public String toString() {
        return "ResidentNameAgeBuildingDto{" +
                "residentId=" + residentId +
                ", residentName='" + residentName + '\'' +
                ", age=" + age +
                ", buildingId=" + buildingId +
                '}';
    }
}
