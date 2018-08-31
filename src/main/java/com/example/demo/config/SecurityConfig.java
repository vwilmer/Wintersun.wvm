package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private UserService userDetailService;

//    @Autowired
//    public SecurityConfig(UserService userDetailService) {
//        this.userDetailService = userDetailService;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // v1
//        http.formLogin()
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/**")
//                .hasRole("USER");

        http.csrf().disable()
                .authorizeRequests()
//                .antMatchers("/", "/winter/login", "/winter/registrar", "/winter/listar").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .logout()
                .permitAll();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        authenticationProvider.setUserDetailsService(userDetailService);
//        return authenticationProvider;
//    }

    // cada ves que la app inicia, entonces se crea nuevos usuarios
//    @Bean
//    public ApplicationRunner applicationRunner() {
//        // running every run code
//        System.out.println("NO PUEDE SER");
//        return args -> {
//            userDetailService.create(new UserEntity(null, "wilmer", passwordEncoder().encode("wvillca"), "ROLE_USER"));
//            userDetailService.create(new UserEntity(null, "froilan", passwordEncoder().encode("fmamani"), "ROLE_USER"));
//        };
//    }

    // opcion valida con el metodo anterior
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(authenticationProvider());
//    }

    private DataSource dataSource;

    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // nueva opcion
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        String SQL1 = "SELECT username, password, active FROM wv_usuarios_v2 WHERE username = ?";
//        String SQL2 = "SELECT u.username, ur.roles FROM wv_usuarios_v2 u INNER JOIN wv_user_role ur ON u.id = ur.user_id WHERE u.username = ?";

        auth.jdbcAuthentication()
                .dataSource(this.dataSource);
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .usersByUsernameQuery(SQL1)
//                .authoritiesByUsernameQuery(SQL2);
    }
}
