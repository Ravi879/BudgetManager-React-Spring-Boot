package com.budget.manager.config;

import com.budget.manager.config.filter.JwtRequestFilter;
import com.budget.manager.config.filter.MdcFilter;
import com.budget.manager.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final AuthService authService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtRequestFilter jwtRequestFilter;
    private final MdcFilter mdcFilter;

    public SecurityConfig(AuthService authService, BCryptPasswordEncoder bCryptPasswordEncoder, JwtRequestFilter jwtRequestFilter, MdcFilter mdcFilter) {
        this.authService = authService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtRequestFilter = jwtRequestFilter;
        this.mdcFilter = mdcFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().and()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests().antMatchers("/api/*").permitAll()
                .anyRequest().authenticated()
                .and()
                // specifying that no session should be created, due to this, it prevents Authorization header from being cached, otherwise it caching the authorization header.
                // we want each rest api call to authorize again with token and not to create session and cached it.
                // this makes rest api stateless.
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // add filter for authenticate incoming request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        // add filter for logging custom word in logback pattern
        http.addFilterBefore(mdcFilter, JwtRequestFilter.class);
    }

    // set UserDetailsService implementation and password encryption strategy
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // set password encoder and custom UserDetailsService
        auth.userDetailsService(authService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // CORS config
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        final CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS"));
        config.setExposedHeaders(Arrays.asList("Authorization", "content-type"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "content-type"));

        source.registerCorsConfiguration("/**", config);

        return source;
    }
}