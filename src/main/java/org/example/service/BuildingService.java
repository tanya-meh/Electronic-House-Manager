package org.example.service;

import org.example.dao.ApartmentDao;
import org.example.dao.BuildingDao;
import org.example.dto.BuildingDto;
import org.example.dto.CreateBuildingDto;
import org.example.dto.TaxDto;
import org.example.entity.Apartment;
import org.example.entity.Building;
import org.example.entity.EmployeeInCompany;
import org.example.entity.Resident;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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
            building.setEmployeeInCompany(buildingDto.getEmployeeInCompany());
            building.setBaseTaxes(buildingDto.getBaseTaxes());
        } else {
            throw new IllegalArgumentException("Building not found.");
        }
        BuildingDao.updateBuilding(building);
    }

    public void deleteBuilding(long id) {
        Building building = BuildingDao.getBuildingById(id);
        if(building != null) {
            BuildingDao.deleteBuilding(building);
        } else {
            throw new IllegalArgumentException("Building not found.");
        }
    }

    public void setEmployeeInCompany(long buildingId, EmployeeInCompany employeeInCompany){
        Building building = BuildingDao.getBuildingById(buildingId);
        if(building != null) {
            building.setEmployeeInCompany(employeeInCompany);
            BuildingDao.updateBuilding(building);
        } else {
            throw new IllegalArgumentException("Building not found.");
        }
    }

    public void addApartmentToBuilding(long apartmentId, long buildingId) {
        Apartment apartment = ApartmentDao.getApartmentById(apartmentId);
        Building building = BuildingDao.getBuildingById(buildingId);

        if(apartment != null && building != null) {
            apartment.setBuilding(building);
            ApartmentDao.updateApartment(apartment);
        } else {
            throw new IllegalArgumentException("Building or Apartment not found.");
        }
    }

    public Set<Apartment> getBuildingApartments(long buildingId) {
        if(BuildingDao.getBuildingById(buildingId) != null) {
            return BuildingDao.getBuildingApartments(buildingId);
        } else {
            throw new IllegalArgumentException("Building not found.");
        }
    }

    public long getNumberOfBuildingApartments(long buildingId) {
        if(BuildingDao.getBuildingById(buildingId) != null) {
            return BuildingDao.getNumberOfBuildingApartments(buildingId);
        } else {
            throw new IllegalArgumentException("Building not found.");
        }
    }

    public List<Resident> getBuildingResidents(long buildingId) {
        if(BuildingDao.getBuildingById(buildingId) != null) {
            return BuildingDao.getBuildingResidents(buildingId);
        } else {
            throw new IllegalArgumentException("Building not found.");
        }
    }

    public long getNumberOfBuildingResidents(long buildingId) {
        if(BuildingDao.getBuildingById(buildingId) != null) {
            return BuildingDao.getNumberOfBuildingApartments(buildingId);
        } else {
            throw new IllegalArgumentException("Building not found.");
        }
    }

    public BigDecimal getBuildingSumUnpaidTaxes(long buildingId) {
        if(BuildingDao.getBuildingById(buildingId) != null) {
            return BuildingDao.getBuildingSumUnpaidTaxes(buildingId);
        } else {
            throw new IllegalArgumentException("Building not found.");
        }
    }

    public BigDecimal getBuildingSumPaidTaxes(long buildingId) {
        if(BuildingDao.getBuildingById(buildingId) != null) {
            return BuildingDao.getBuildingSumPaidTaxes(buildingId);
        } else {
            throw new IllegalArgumentException("Building not found.");
        }
    }

    public List<TaxDto> getBuildingUnpaidTaxes(long buildingId) {
        if(BuildingDao.getBuildingById(buildingId) != null) {
            return BuildingDao.getBuildingUnpaidTaxes(buildingId);
        } else {
            throw new IllegalArgumentException("Building not found.");
        }
    }

    public List<TaxDto> getBuildingPaidTaxes(long buildingId) {
        if(BuildingDao.getBuildingById(buildingId) != null) {
            return BuildingDao.getBuildingPaidTaxes(buildingId);
        } else {
            throw new IllegalArgumentException("Building not found.");
        }
    }
}
