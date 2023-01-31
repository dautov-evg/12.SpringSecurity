package ru.dautov.springcourse.FirstSecurityApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.dautov.springcourse.FirstSecurityApp.services.PersonDetailsService;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {   // здесь вся конфигурация Spring Security

    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // конфигурируем сам Spring Security (вход, ошибки и т д)

        http.csrf().disable() //отключили токе csrf(временно). Чтобы работало
                // конфигурируем авторизацию
                .authorizeRequests()
                .antMatchers("/auth/login", "/auth/registration", "/error").permitAll()  //настраиваем чтобы на эти страницы могли войти даже неаутентифицированные пользователи
                .anyRequest().authenticated()  //все остальные страницы доступны только через авторизацию
                .and()
//                конфигурируем страничку логина
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login") //куда отправлять данные с формы (Spring проверет сам)
                .defaultSuccessUrl("/hello", true) //куда направлять, в случае валидных данных
                .failureUrl("/auth/login?error");    //Куда направлять в случае невалидных данных
    }


    //    Настраиваем аутентификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
