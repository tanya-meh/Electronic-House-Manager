package org.example.dao;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.entity.Apartment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentDaoTest {

    private static SessionFactory sessionFactory = null;
    private Session session = null;

    private List<String> validate(Apartment apartment) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(apartment)
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

    @Test
    void givenValidApartment_whenSave_thenGetOk() {
        Apartment apartment = new Apartment(12, 5, BigDecimal.valueOf(75.5));
        apartment.setNumberOfPets(2);

        ApartmentDao.createApartment(apartment);
        Apartment savedApartment = ApartmentDao.getApartmentById(1);

        assertNotNull(savedApartment);
        assertEquals(12, savedApartment.getNumber());
        assertEquals(5, savedApartment.getFloor());
        assertEquals(0, savedApartment.getArea().compareTo(BigDecimal.valueOf(75.5)));
    }

    @Test
    void whenInvalidApartmentNumber_thenAssertConstraintViolations() {
        Apartment apartment = new Apartment(-1, 5, BigDecimal.valueOf(75.5));

        List<String> messages = validate(apartment);

        assertEquals(1, messages.size());
        assertTrue(messages.contains("must be greater than 0"));
    }

    @Test
    void whenInvalidApartmentFloor_thenAssertConstraintViolations() {
        Apartment apartment = new Apartment(12, -2, BigDecimal.valueOf(75.5));

        List<String> messages = validate(apartment);

        assertEquals(1, messages.size());
        assertTrue(messages.contains("must be greater than 0"));
    }

    @Test
    void whenInvalidApartmentArea_thenAssertConstraintViolations() {
        Apartment apartment = new Apartment(12, 5, BigDecimal.valueOf(-50.0));

        List<String> messages = validate(apartment);

        assertEquals(1, messages.size());
        assertTrue(messages.contains("must be greater than 0"));
    }

    @Test
    void whenInvalidNumberOfPets_thenAssertConstraintViolations() {
        Apartment apartment = new Apartment(12, 5, BigDecimal.valueOf(75.5));
        apartment.setNumberOfPets(-3);

        List<String> messages = validate(apartment);

        assertEquals(1, messages.size());
        assertTrue(messages.contains("must be greater than or equal to 0"));
    }

    @Test
    void whenAllInvalidFields_thenAssertMultipleConstraintViolations() {
        Apartment apartment = new Apartment(-10, -5, BigDecimal.valueOf(-75.5));
        apartment.setNumberOfPets(-1);

        List<String> messages = validate(apartment);

        assertEquals(4, messages.size());
        assertTrue(messages.contains("must be greater than 0"));
        assertTrue(messages.contains("must be greater than or equal to 0"));
    }

    @Test
    void whenSaveInvalidApartment_thenAssertPersistenceException() {
        Apartment apartment = new Apartment(-10, -5, BigDecimal.valueOf(-75.5));

        assertThrows(RuntimeException.class, () -> ApartmentDao.createApartment(apartment),
                "Persisting an invalid Apartment should throw an exception.");
    }
}
