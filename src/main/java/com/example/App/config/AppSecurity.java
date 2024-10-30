package com.example.App.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
// @EnableWebSecurity для підтримки Web-безпеки Spring Security
// та забезпечення інтеграції Spring MVC.
@EnableWebSecurity
public class AppSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    // Interface PasswordEncoder - інтерфейс для кодування паролів.
    // Class BCryptPasswordEncoder - Реалізація PasswordEncoder,
    // який використовує функцію хешування BCrypt.
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Якщо маємо різні алгоритми хешування паролів (bcrypt, SHA-256),
    // які записані в БД, наприклад. Тоді PasswordEncoder наступний
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

    // Class HttpSecurity дозволяє налаштувати веб-захист для конкретних http-запитів.
    // https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/builders/HttpSecurity.html
    //
    // HttpSecurity.csrf()
    // https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/builders/HttpSecurity.html#cors(org.springframework.security.config.Customizer)
    //
    // CSRF (Cross-Site Request Forgery, а також XSRF) – атака, яка призводить
    // до того, що хакер може виконати дії від імені інших, зареєстрованих користувачів.
    // Наприклад, надсилання повідомлень, переказ грошей з рахунку на рахунок або зміна паролів.
    // https://en.wikipedia.org/wiki/Cross-site_request_forgery
    // https://owasp.org/www-community/attacks/csrf
    //
    // Interface SecurityFilterChain визначає ланцюжок фільтрів.
    // https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/SecurityFilterChain.html
    //
    // Class AbstractHttpConfigurer додає зручний базовий клас для екземплярів
    // Interface SecurityConfigurer, які працюють на Class HttpSecurity.
    // https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/configurers/AbstractHttpConfigurer.html
    //
    // Interface SecurityConfigurer
    // https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/SecurityConfigurer.html
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                // Spring Security дозволяє моделювати (кастомізувати)
                // нашу авторизацію на рівні запиту (request).
                // https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/index").permitAll()
                                .requestMatchers("/users").hasRole("ADMIN"))
                // Spring Security забезпечує підтримку імені користувача та пароля,
                // які надаються через HTML форму.
                // https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html
                // Тут застосована конфігурація для кастомної форми входу (login),
                // яка надається за певним посиланням
                .formLogin(form ->
                        form.loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/users")
                                .permitAll())
                // За замовчуванням Spring Security підтримує кінцеву точку /logout.
                // Надається користувачам змога вийти.
                // https://docs.spring.io/spring-security/reference/servlet/authentication/logout.html
                .logout(logout ->
                        logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll());
        return http.build();
    }

    // Class AuthenticationManagerBuilder - SecurityBuilder використовується для створення
    // AuthenticationManager. Дозволяє легко здійснювати автентифікацію, додавати
    // UserDetailsService та AuthenticationProvider.
    // https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/config/annotation/authentication/builders/AuthenticationManagerBuilder.html
    // Interface SecurityBuilder
    // https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/config/annotation/SecurityBuilder.html
    // Interface AuthenticationManager
    // https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/authentication/AuthenticationManager.html
    // Interface UserDetailsService
    // https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/core/userdetails/UserDetailsService.html
    // Interface AuthenticationProvider
    // https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/authentication/AuthenticationProvider.html
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
