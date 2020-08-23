package com.budget.manager.config.filter;

import com.budget.manager.service.AuthService;
import com.budget.manager.shared.dto.UserDto;
import com.budget.manager.shared.properties.JwtProperties;
import com.budget.manager.shared.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final AuthService authService;
    private final JwtUtils jwtUtils;
    private final JwtProperties properties;

    public JwtRequestFilter(AuthService authService, JwtUtils jwtUtils, JwtProperties properties) {
        this.authService = authService;
        this.jwtUtils = jwtUtils;
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(properties.getHeader());

        String userIdInToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            userIdInToken = jwtUtils.extractSubject(jwt);
        }

        if (userIdInToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDto userDto = this.authService.getUserByUserId(userIdInToken);

            if (StringUtils.equals(userDto.getUserId(), userIdInToken)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDto.getUserId(), null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        chain.doFilter(request, response);
    }


}