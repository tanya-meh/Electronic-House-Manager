package org.example.service;


import org.example.dao.OwnerDao;
import org.example.dto.CreateOwnerDto;
import org.example.dto.OwnerDto;
import org.example.dto.PersonDto;
import org.example.entity.Owner;

public class OwnerService {
    public OwnerDto createOwner(CreateOwnerDto createOwnerDto) {
        Owner owner = new Owner(
                createOwnerDto.getName()
        );

        OwnerDao.createOwner(owner);

        return new OwnerDto(
                owner.getId(),
                owner.getName());
    }

    public OwnerDto createOwner(PersonDto personDto) {
        Owner owner = new Owner();
        owner.setId(personDto.getId());
        owner.setName(personDto.getName());

        OwnerDao.createOwner(owner);

        return new OwnerDto(
                owner.getId(),
                owner.getName()
        );
    }

    public void updateOwner(OwnerDto OwnerDto) {
        Owner owner = OwnerDao.getOwnerById(OwnerDto.getId());
        if(owner != null) {
            owner.setName(OwnerDto.getName());
        } else {
            throw new IllegalArgumentException("Owner not found");
        }
        OwnerDao.updateOwner(owner);
    }

    public void deleteOwner(long id) {
        Owner owner = OwnerDao.getOwnerById(id);
        if(owner != null) {
            OwnerDao.deleteOwner(owner);
        } throw new IllegalArgumentException("Owner not found.");
    }
}
