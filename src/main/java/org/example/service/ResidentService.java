package org.example.service;

import org.example.dao.ResidentDao;
import org.example.dto.PersonDto;
import org.example.dto.ResidentDto;
import org.example.entity.Resident;

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
            resident.setApartments(residentDto.getApartments());
        }
        ResidentDao.updateResident(resident);
    }

    public void deleteResident(long id) {
        Resident resident = ResidentDao.getResidentById(id);
        if(resident != null) {
            ResidentDao.deleteResident(resident);
        }
    }


}
