package org.example.service;

import org.example.dao.ApartmentDao;
import org.example.dao.BuildingDao;
import org.example.dto.BuildingDto;
import org.example.dto.CreateBuildingDto;
import org.example.entity.Apartment;
import org.example.entity.Building;
import org.example.entity.EmployeeInCompany;

import java.math.BigDecimal;

public class BuildingService {
    public BuildingDto createBuilding(CreateBuildingDto createBuildingDto) {
        Building building = new Building(
                createBuildingDto.getAddress(),
                createBuildingDto.getNumberOfFloors(),
                createBuildingDto.getBuiltUpArea(),
                createBuildingDto.getCommonArea()
        );
        BuildingDao.createBuilding(building);

        return new BuildingDto(
                building.getId(),
                building.getAddress(),
                building.getNumberOfFloors(),
                building.getBuiltUpArea(),
                building.getCommonArea());
    }

    public void updateBuilding(BuildingDto buildingDto) {
        Building building = BuildingDao.getBuildingById(buildingDto.getId());
        if(building != null) {
            building.setAddress(buildingDto.getAddress());
            building.setNumberOfFloors(buildingDto.getNumberOfFloors());
            building.setBuiltUpArea(buildingDto.getBuiltUpArea());
            building.setCommonArea(buildingDto.getCommonArea());
            building.setApartments(buildingDto.getApartments());
            building.setEmployeeInCompany(buildingDto.getEmployeeInCompany());
            building.setBaseTaxes(buildingDto.getBaseTaxes());
        }
        BuildingDao.updateBuilding(building);
    }

    public void deleteBuilding(long id) {
        Building building = BuildingDao.getBuildingById(id);
        if(building != null) {
            BuildingDao.deleteBuilding(building);
        }
    }

    public void setEmployeeInCompany(long buildingId, EmployeeInCompany employeeInCompany){
        Building building = BuildingDao.getBuildingById(buildingId);
        if(building != null) {
            building.setEmployeeInCompany(employeeInCompany);
            BuildingDao.updateBuilding(building);
        }
    }

    public void addApartmentToBuilding(long apartmentId, long buildingId) {
        Apartment apartment = ApartmentDao.getApartmentById(apartmentId);
        Building building = BuildingDao.getBuildingById(buildingId);

        if(apartment != null && building != null) {
            apartment.setBuilding(building);
            ApartmentDao.updateApartment(apartment);

        }

    }

}
