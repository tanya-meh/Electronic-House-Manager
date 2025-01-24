package org.example.service;


import org.example.dao.ApartmentDao;
import org.example.dao.BuildingDao;
import org.example.dto.ApartmentDto;
import org.example.dto.CreateApartmentDto;
import org.example.entity.Apartment;
import org.example.entity.Building;

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
            apartment.setOwners(apartmentDto.getOwners());
            apartment.setResidents(apartmentDto.getResidents());
            apartment.setNumberOfPets(apartmentDto.getNumberOfPets());
            apartment.setTaxes(apartmentDto.getTaxes());
        }
        ApartmentDao.updateApartment(apartment);
    }

    public void deleteApartment(long id) {
        Apartment apartment = ApartmentDao.getApartmentById(id);
        if(apartment != null) {
            ApartmentDao.deleteApartment(apartment);
        }
    }

    public void addApartmentToBuilding(long apartmentId, long buildingId) {
        Apartment apartment = ApartmentDao.getApartmentById(apartmentId);
        Building building = BuildingDao.getBuildingById(buildingId);

        if(apartment != null && building != null){
            apartment.setBuilding(building);
            ApartmentDao.updateApartment(apartment);
        }
    }
}
