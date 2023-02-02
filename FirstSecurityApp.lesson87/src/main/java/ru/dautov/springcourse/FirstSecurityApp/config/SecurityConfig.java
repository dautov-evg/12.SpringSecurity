package ru.dautov.springcourse.FirstSecurityApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.dautov.springcourse.FirstSecurityApp.services.PersonDetailsService;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {   // здесь вся конфигурация Spring Security

    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // конфигурируем сам Spring Security (вход, ошибки и т д)
        // конфигурируем авторизацию
        http.authorizeRequests()
//                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/auth/login", "/auth/registration", "/error").permitAll() //настраиваем чтобы на эти страницы могли войти даже неаутентифицированные пользователи
                .anyRequest().hasAnyRole("USER", "ADMIN")

                .and()
//                конфигурируем страничку логина
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login") //куда отправлять данные с формы (Spring проверет сам)
                .defaultSuccessUrl("/hello", true) //куда направлять, в случае валидных данных
                .failureUrl("/auth/login?error") //Куда направлять в случае невалидных данных
                .and() //разлогирование
                .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login"); // при переходе на /logout, произойдет разлогирование.
                                                        // Удялится сессия и кукис. Автоматически осуществится переход на страницу /login
    }


    //    Настраиваем аутентификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
//        шифрование
        return new BCryptPasswordEncoder();
    }
}
