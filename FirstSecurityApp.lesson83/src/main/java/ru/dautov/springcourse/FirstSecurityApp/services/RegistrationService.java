package ru.dautov.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dautov.springcourse.FirstSecurityApp.models.Person;
import ru.dautov.springcourse.FirstSecurityApp.repositories.PeopleRepositiry;

@Service
public class RegistrationService {
    private final PeopleRepositiry peopleRepositiry;

    @Autowired
    public RegistrationService(PeopleRepositiry peopleRepositiry) {
        this.peopleRepositiry = peopleRepositiry;
    }

    @Transactional
    public void register(Person person) {
        peopleRepositiry.save(person);
    }
}
