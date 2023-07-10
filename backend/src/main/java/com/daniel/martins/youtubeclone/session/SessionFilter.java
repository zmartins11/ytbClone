package com.daniel.martins.youtubeclone.session;


import com.daniel.martins.youtubeclone.user.CurrentUser;
import com.daniel.martins.youtubeclone.user.CurrentUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SessionFilter extends OncePerRequestFilter {

    private final InMemorySessionRegistry sessionRegistry;
    private final CurrentUserService currentUserService;

    @Autowired
    public SessionFilter(final InMemorySessionRegistry sessionRegistry, CurrentUserService currentUserService) {
        this.sessionRegistry = sessionRegistry;
        this.currentUserService = currentUserService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(sessionId == null || sessionId.length() == 0) {
            chain.doFilter(request,response);
            return;
        }

        final String username = sessionRegistry.getUsernameForSession(sessionId);
        if(username == null) {
            chain.doFilter(request, response);
            return;
        }

        final CurrentUser currentUser = currentUserService.loadUserByUsername(username);

        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                currentUser,
                null,
                currentUser.getAuthorities()
        );

        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);

    }
}
