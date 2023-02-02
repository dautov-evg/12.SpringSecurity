package ru.dautov.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dautov.springcourse.FirstSecurityApp.models.Person;

import java.util.Optional;

@Repository
public interface PeopleRepositiry extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
}
