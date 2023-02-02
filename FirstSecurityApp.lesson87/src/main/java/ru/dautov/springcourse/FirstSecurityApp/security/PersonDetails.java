package ru.dautov.springcourse.FirstSecurityApp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.dautov.springcourse.FirstSecurityApp.models.Person;

import java.util.Collection;
import java.util.Collections;

public class PersonDetails implements UserDetails {

    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(person.getRole())); //в будующем мы будем возврщать коллекцию прав у этого пользователя
    }

    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {  //аккаунт не просрочен
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {  //аккаунт не заблокирован
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  //пароль не просрочен
        return true;
    }

    @Override
    public boolean isEnabled() {  //аккаунт велючен/работает
        return true;
    }

//    Нужно чтобы получать данные аутентифицированного пользователя
    public Person getPerson() {
        return this.person;
    }
}
