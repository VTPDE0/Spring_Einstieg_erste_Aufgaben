package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
//change für commit


import org.springframework.web.filter.OncePerRequestFilter;
// Filter in Spring, der sicherstellt, dass der Filter nur einmal pro Anfrage ausgeführt wird.

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private BenutzerRepository benutzerRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String benutzername = request.getHeader("benutzername");
        String passwort = request.getHeader("passwort");

        if (benutzername == null || benutzername.isEmpty() || passwort == null || passwort.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Optional<Benutzer> optionalBenutzer = benutzerRepository.findByBenutzername(benutzername);
        if (!optionalBenutzer.isPresent() || !optionalBenutzer.get().getPasswort().equals(passwort)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
