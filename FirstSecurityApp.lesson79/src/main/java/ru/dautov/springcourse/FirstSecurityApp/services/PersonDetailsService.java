package ru.dautov.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dautov.springcourse.FirstSecurityApp.models.Person;
import ru.dautov.springcourse.FirstSecurityApp.repositories.PeopleRepositiry;
import ru.dautov.springcourse.FirstSecurityApp.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepositiry peopleRepositiry;

    @Autowired
    public PersonDetailsService(PeopleRepositiry peopleRepositiry) {
        this.peopleRepositiry = peopleRepositiry;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepositiry.findByUsername(username);
        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        return new PersonDetails(person.get());
    }
}
