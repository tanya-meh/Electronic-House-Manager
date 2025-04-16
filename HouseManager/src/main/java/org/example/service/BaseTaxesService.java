package org.example.service;

import org.example.dao.BaseTaxesDao;
import org.example.dao.BuildingDao;
import org.example.dto.BaseTaxesDto;
import org.example.dto.CreateBaseTaxesDto;
import org.example.entity.BaseTaxes;
import org.example.entity.Building;

public class BaseTaxesService {
    public BaseTaxesDto createBaseTaxes(CreateBaseTaxesDto createBaseTaxesDto) {
        BaseTaxes baseTaxes = new BaseTaxes(
                createBaseTaxesDto.getTaxPerSquareMeter(),
                createBaseTaxesDto.getTaxPerResident(),
                createBaseTaxesDto.getTaxPerPet()
        );

        BaseTaxesDao.createBaseTaxes(baseTaxes);

        return new BaseTaxesDto(baseTaxes.getId(),
                baseTaxes.getTaxPerSquareMeter(),
                baseTaxes.getTaxPerResident(),
                baseTaxes.getTaxPerPet());
    }

    public void updateBaseTaxes(BaseTaxesDto baseTaxesDto) {
        BaseTaxes baseTaxes = BaseTaxesDao.getBaseTaxesById(baseTaxesDto.getId());
        if(baseTaxes != null) {
            baseTaxes.setTaxPerSquareMeter(baseTaxesDto.getTaxPerSquareMeter());
            baseTaxes.setTaxPerResident(baseTaxesDto.getTaxPerResident());
            baseTaxes.setTaxPerPet(baseTaxesDto.getTaxPerPet());
            baseTaxes.setBuilding(baseTaxesDto.getBuilding());
        } else {
            throw new IllegalArgumentException("BaseTaxes not found.");
        }

        BaseTaxesDao.updateBaseTaxes(baseTaxes);
    }

    public void deleteBaseTaxes(long id) {
        BaseTaxes baseTaxes = BaseTaxesDao.getBaseTaxesById(id);
        if(baseTaxes != null) {
            BaseTaxesDao.deleteBaseTaxes(baseTaxes);
        } else {
            throw new IllegalArgumentException("BaseTaxes not found.");
        }
    }

    public void applyBaseTaxesToBuilding(long baseTaxesId, long buildingId) {
        BaseTaxes baseTaxes = BaseTaxesDao.getBaseTaxesById(baseTaxesId);
        Building building = BuildingDao.getBuildingById(buildingId);
        if (baseTaxes != null && building != null) {
            baseTaxes.setBuilding(building);
            BaseTaxesDao.updateBaseTaxes(baseTaxes);
        } else {
            throw new IllegalArgumentException("BaseTaxes or Building not found.");
        }
    }
}
