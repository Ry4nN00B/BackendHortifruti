package me.ry4nn00b.hortifruti.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final SecurityFilter authenticatorFilter;

    public SecurityConfig(SecurityFilter authenticatorFilter) {
        this.authenticatorFilter = authenticatorFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/register").hasAuthority("GERENTE")
                        .requestMatchers("/categorias/**").hasAnyAuthority("GERENTE", "ESTOQUISTA")
                        .requestMatchers("/produtos/**").hasAnyAuthority("GERENTE", "ESTOQUISTA")
                        .requestMatchers("/estoque/**").hasAnyAuthority("GERENTE", "ESTOQUISTA")
                        .requestMatchers("/fornecedores/**").hasAuthority("GERENTE")
                        .requestMatchers("/vendas/**").hasAnyAuthority("GERENTE", "OPERADOR")
                        .requestMatchers("/promocoes/**").hasAnyAuthority("GERENTE", "OPERADOR")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(authenticatorFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
