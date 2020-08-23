package com.budget.manager.config.filter;

import com.budget.manager.exception.InvalidTokenException;
import com.budget.manager.exception.ResourceNotFoundException;
import com.budget.manager.service.AuthService;
import com.budget.manager.shared.dto.UserDto;
import com.budget.manager.shared.properties.JwtProperties;
import com.budget.manager.shared.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

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
    private final HandlerExceptionResolver resolver;

    public JwtRequestFilter(AuthService authService, JwtUtils jwtUtils, JwtProperties properties, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.authService = authService;
        this.jwtUtils = jwtUtils;
        this.properties = properties;
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(properties.getHeader());

        String userIdInToken = null;

        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String jwt = authorizationHeader.substring(7);

                userIdInToken = jwtUtils.extractSubject(jwt);
            }

            if (userIdInToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDto userDto = this.authService.getUserByUserId(userIdInToken);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDto.getUserId(), null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

            chain.doFilter(request, response);
        }
        // delegates exceptions to ExceptionHandler
        catch (ExpiredJwtException ex) {
            resolver.resolveException(request, response, null, new InvalidTokenException("token.expired", ""));
        } catch (ResourceNotFoundException ex) {
            resolver.resolveException(request, response, null, ex);
        }

    }

}