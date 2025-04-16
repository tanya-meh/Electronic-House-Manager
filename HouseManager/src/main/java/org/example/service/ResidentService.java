package org.example.service;

import org.example.dao.BuildingDao;
import org.example.dao.ResidentDao;
import org.example.dto.PersonDto;
import org.example.dto.ResidentDto;
import org.example.dto.ResidentNameAgeBuildingDto;
import org.example.entity.Resident;
import org.example.exception.MinimumGreaterThanMaximumException;

import java.util.List;

public class ResidentService {
    public ResidentDto createResident(ResidentDto residentDto) {
        Resident resident = new Resident(
                residentDto.getName(),
                residentDto.getAge(),
                residentDto.usesLift()
        );

        ResidentDao.createResident(resident);

        return new ResidentDto(
                resident.getId(),
                resident.getName(),
                resident.getAge(),
                resident.usesLift());
    }

    public ResidentDto createResident(PersonDto personDto) {
        Resident resident = new Resident();
        resident.setId(personDto.getId());
        resident.setName(personDto.getName());

        ResidentDao.createResident(resident);

        return new ResidentDto(
                resident.getId(),
                resident.getName()
        );
    }

    public void updateResident(ResidentDto residentDto) {
        Resident resident = ResidentDao.getResidentById(residentDto.getId());
        if(resident != null) {
            resident.setName(residentDto.getName());
            resident.setAge(residentDto.getAge());
            resident.setUsesLift(residentDto.usesLift());
        } else {
            throw new IllegalArgumentException("Resident not found.");
        }
        ResidentDao.updateResident(resident);
    }

    public void deleteResident(long id) {
        Resident resident = ResidentDao.getResidentById(id);
        if(resident != null) {
            ResidentDao.deleteResident(resident);
        } else {
            throw new IllegalArgumentException("Resident not found.");
        }
    }


    public List<ResidentNameAgeBuildingDto> filterResidentsInBuildingByNameAndAge(long buildingId, String name, int minAge, int maxAge) {
        if(BuildingDao.getBuildingById(buildingId) == null) {
             throw new IllegalArgumentException("Building not found.");
        }

        if(minAge > maxAge) {
            throw new MinimumGreaterThanMaximumException("minAge greater than maxAge.");
        }
        return ResidentDao.filterResidentsInBuildingByNameAndAge(buildingId, name, minAge, maxAge);
    }

    public List<ResidentNameAgeBuildingDto> sortResidentsInBuildingByNameAndAge(long buildingId, boolean nameAsc, boolean ageAsc) {
        if(BuildingDao.getBuildingById(buildingId) == null) {
            throw new IllegalArgumentException("Building not found.");
        }
        return ResidentDao.sortResidentsInBuildingByNameAndAge(buildingId, nameAsc, ageAsc);
    }


}
