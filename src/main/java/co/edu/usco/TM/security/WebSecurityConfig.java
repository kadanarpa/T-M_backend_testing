package co.edu.usco.TM.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/append", "/delete/{id}", "/modify/{id}").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin();
                

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder().username("daisy").password("1602").roles("ADMIN")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder().username("nicky").password("0406").roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
