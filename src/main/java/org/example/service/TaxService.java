package org.example.service;

import org.example.dao.ApartmentDao;
import org.example.dao.TaxDao;
import org.example.dto.CreateTaxDto;
import org.example.dto.TaxDto;
import org.example.entity.Apartment;
import org.example.entity.Tax;

import java.time.LocalDate;

public class TaxService {
    public TaxDto createTax(CreateTaxDto createTaxDto) {
        Tax tax = new Tax(
                createTaxDto.getDateOfIssue()
        );

        TaxDao.createTax(tax);

        return new TaxDto(tax.getId(),
                tax.getDateOfIssue());
    }

    public void updateTax(TaxDto taxDto) {
        Tax tax = TaxDao.getTaxById(taxDto.getId());
        if(tax != null) {
            tax.setDateOfIssue(taxDto.getDateOfIssue());
            tax.setAmount(taxDto.getAmount());
            tax.setDateOfPayment(taxDto.getDateOfPayment());
            tax.setApartment(taxDto.getApartment());
        }
        TaxDao.updateTax(tax);
    }

    public void deleteTax(long id) {
        Tax tax = TaxDao.getTaxById(id);
        if(tax != null) {
            TaxDao.deleteTax(tax);
        }
    }

    public void applyTaxToApartment(long taxId, long apartmentId) {
        Tax tax = TaxDao.getTaxById(taxId);
        Apartment apartment = ApartmentDao.getApartmentById(apartmentId);
        if (tax != null && apartment != null) {
            ApartmentService apartmentService = new ApartmentService();

            tax.setApartment(apartment);
            tax.setAmount(apartmentService.calculateTaxAmountForApartment(apartmentId));

            TaxDao.updateTax(tax);
        }
    }

    public void payTax(long taxId) {
        Tax tax = TaxDao.getTaxById(taxId);
        tax.setDateOfPayment(LocalDate.now());
        TaxDao.updateTax(tax);
    }

}
