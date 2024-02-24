package ac.ke.rondavels.marverick.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class Securitymock {

    public static final String[] ENDPOINTS_WHITELIST = {
            "/images/**",
            "/",
            "/403",
            "/css/**",
            "/js/**",
            "/all_blogs",
            "/blog/**",
            "/error",
            "/login",
            "events",
            "events/**",
            "rooms/**",
            "/gallery",
            "/about_us", "/message"
            ,"/contact", "/book",
            "/404/","/logout/",
    };

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http(
//                //headersConfigurer -> headersConfigurer
////                                .contentSecurityPolicy(csp -> csp.policyDirectives("form-action 'self'; report-uri /csp-report-endpoint"))
////                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
////                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::deny)
////                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
//                        //.httpStrictTransportSecurity(hsts -> hsts.includeSubDomains(true).maxAgeInSeconds(31536000))
//                )

        http


                .authorizeHttpRequests(request -> request
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(ENDPOINTS_WHITELIST).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/_/**")).hasRole("ADMIN")

                        .anyRequest().hasRole("ADMIN")
                )


                .formLogin(form -> form
                        // .loginPage("/login")
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            for (GrantedAuthority auth : authentication.getAuthorities()) {
                                if (auth.getAuthority().equals("ROLE_ADMIN")) {
                                    response.sendRedirect("/_/");
                                } else if (auth.getAuthority().equals("ROLE_USER")) {
                                    response.sendRedirect("/account");
                                }
                            }
                        })
                        .failureUrl("/login?error=true")
                )


                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                )

                .csrf().disable()



                .exceptionHandling(access -> access.accessDeniedPage("/errors/404"));



        return http.build();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());

        return provider;
    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//        JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
//        jdbcImpl.setDataSource(dataSource);
//        jdbcImpl.setUsersByUsernameQuery("select username, password, enabled from users where username=?");
//        jdbcImpl.setAuthoritiesByUsernameQuery("select username, authority from authorities where username=?");
//        return jdbcImpl;
//    }


    // hard coded user and admin


    @Bean

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                if (username.equals("admin")) {
                    return User.withUsername("admin")
                            .password(passwordEncoder().encode("admin"))
                            .roles("ADMIN")
                            .build();
                } else if (username.equals("user")) {
                    return User.withUsername("user")
                            .password(passwordEncoder().encode("user"))
                            .roles("USER")
                            .build();
                }
                throw new UsernameNotFoundException("User not found");
            }
        };
    }




}