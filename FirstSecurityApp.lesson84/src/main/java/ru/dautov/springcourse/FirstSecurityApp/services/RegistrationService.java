package ru.dautov.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dautov.springcourse.FirstSecurityApp.models.Person;
import ru.dautov.springcourse.FirstSecurityApp.repositories.PeopleRepositiry;

@Service
public class RegistrationService {
    private final PeopleRepositiry peopleRepositiry;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PeopleRepositiry peopleRepositiry, PasswordEncoder passwordEncoder) {
        this.peopleRepositiry = peopleRepositiry;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Person person) {
//        получаем пароль, шифруем его и записываем в БД
        person.setPassword(passwordEncoder.encode(person.getPassword()));

        peopleRepositiry.save(person);
    }
}
