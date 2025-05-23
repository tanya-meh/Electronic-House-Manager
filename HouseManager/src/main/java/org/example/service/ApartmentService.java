package org.example.service;


import org.example.dao.ApartmentDao;
import org.example.dao.OwnerDao;
import org.example.dao.ResidentDao;
import org.example.dto.ApartmentDto;
import org.example.dto.CreateApartmentDto;
import org.example.entity.*;

import java.math.BigDecimal;
import java.util.Set;

public class ApartmentService {
    public ApartmentDto createApartment(CreateApartmentDto apartmentDto) {
        Apartment apartment = new Apartment(
                apartmentDto.getNumber(),
                apartmentDto.getFloor(),
                apartmentDto.getArea()
        );

        ApartmentDao.createApartment(apartment);

        return new ApartmentDto(
                apartment.getId(),
                apartment.getNumber(),
                apartment.getFloor(),
                apartment.getArea());
    }

    public void updateApartment(ApartmentDto apartmentDto) {
        Apartment apartment = ApartmentDao.getApartmentById(apartmentDto.getId());
        if(apartment != null) {
            apartment.setNumber(apartmentDto.getNumber());
            apartment.setFloor(apartmentDto.getFloor());
            apartment.setArea(apartmentDto.getArea());
            apartment.setBuilding(apartmentDto.getBuilding());
            apartment.setNumberOfPets(apartmentDto.getNumberOfPets());
        } else {
            throw new IllegalArgumentException("Apartment not found.");
        }

        ApartmentDao.updateApartment(apartment);
    }

    public void deleteApartment(long id) {
        Apartment apartment = ApartmentDao.getApartmentById(id);
        if(apartment != null) {
            ApartmentDao.deleteApartment(apartment);
        } else {
            throw new IllegalArgumentException("Apartment not found.");
        }
    }

    public void setNumOfPetsInApartment(int numOfPets, long apartmentId) {
        Apartment apartment = ApartmentDao.getApartmentById(apartmentId);
        if(apartment != null) {
            apartment.setNumberOfPets(numOfPets);
            ApartmentDao.updateApartment(apartment);
        } else {
            throw new IllegalArgumentException("Apartment not found.");
        }
    }

    public void addResidentToApartment(long residentId, long apartmentId) {
        Apartment apartment = ApartmentDao.getApartmentById(apartmentId);
        Resident resident = ResidentDao.getResidentById(residentId);

        if (apartment == null || resident == null) {
            throw new IllegalArgumentException("Apartment or Resident not found.");
        }

        Set<Resident> residents = ApartmentDao.getApartmentResidents(apartmentId);
        residents.add(resident);
        apartment.setResidents(residents);
        ApartmentDao.updateApartment(apartment);

        Set<Apartment> apartments = ResidentDao.getResidentApartments(residentId);
        apartments.add(apartment);
        resident.setApartments(apartments);
        ResidentDao.updateResident(resident);
    }

    public void addApartmentOwner(long ownerId, long apartmentId) {
        Apartment apartment = ApartmentDao.getApartmentById(apartmentId);
        Owner owner = OwnerDao.getOwnerById(ownerId);

        if (apartment == null || owner == null) {
            throw new IllegalArgumentException("Apartment or Owner not found.");
        }

        Set<Owner> owners = ApartmentDao.getApartmentOwners(apartmentId);
        owners.add(owner);
        apartment.setOwners(owners);
        ApartmentDao.updateApartment(apartment);

        Set<Apartment> apartments = OwnerDao.getOwnerApartments(ownerId);
        apartments.add(apartment);
        owner.setApartments(apartments);
        OwnerDao.updateOwner(owner);
    }

    public BigDecimal calculateTaxAmountForApartment(long apartmentId) {
        BigDecimal taxAmount = BigDecimal.ZERO;
        Apartment apartment = ApartmentDao.getApartmentById(apartmentId);
        if(apartment != null) {
            Building building = apartment.getBuilding();
            if(building != null) {
                BaseTaxes baseTaxes = building.getBaseTaxes();
                if(baseTaxes != null) {
                    BigDecimal taxPerSquareMeter = baseTaxes.getTaxPerSquareMeter();
                    BigDecimal taxPerResident = baseTaxes.getTaxPerResident();
                    BigDecimal taxPerPet = baseTaxes.getTaxPerPet();

                    long residentsOver7UseLift = ApartmentDao.countResidentsOver7UseLiftInApartment(apartmentId);

                    taxAmount = (taxPerSquareMeter.multiply(apartment.getArea()))
                            .add(taxPerResident.multiply(BigDecimal.valueOf(residentsOver7UseLift)))
                            .add(taxPerPet.multiply(BigDecimal.valueOf(apartment.getNumberOfPets())));

                }
            }
        } else {
            throw new IllegalArgumentException("Apartment not found.");
        }
        return taxAmount;

    }

}
