package org.example.service;

import org.example.dao.PersonDao;
import org.example.dto.CreatePersonDto;
import org.example.dto.PersonDto;
import org.example.entity.Person;

public class PersonService {
    public PersonDto createPerson(CreatePersonDto createPersonDto) {
        Person person = new Person(
                createPersonDto.getName()
        );

        PersonDao.createPerson(person);

        return new PersonDto(
                person.getId(),
                person.getName());
    }

    public void updatePerson(PersonDto PersonDto) {
        Person person = PersonDao.getPersonById(PersonDto.getId());
        if(person != null) {
            person.setName(PersonDto.getName());
        }
        PersonDao.updatePerson(person);
    }

    public void deletePerson(long id) {
        Person person = PersonDao.getPersonById(id);
        if(person != null) {
            PersonDao.deletePerson(person);
        }
    }
}
