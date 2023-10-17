/*Aktuell wird nicht benötigt, da ich überprüfe aktuell nur ID und nicht passwort ode Benutzername


package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class SimpleAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private BenutzerRepository benutzerRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Extrahieren der benutzerId aus dem Header
        String benutzerIdHeader = request.getHeader("benutzerId");

        // Überprüfen, ob die benutzerId vorhanden ist
        if (benutzerIdHeader == null || benutzerIdHeader.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Versuchen, die benutzerId in einen Long-Wert umzuwandeln
        Long benutzerId;
        try {
            benutzerId = Long.parseLong(benutzerIdHeader);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Überprüfen, ob ein Benutzer mit der gegebenen ID existiert
        Optional<Benutzer> optionalBenutzer = benutzerRepository.findById(benutzerId);
        if (!optionalBenutzer.isPresent()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Wenn alles in Ordnung ist, fahren mit der nächsten Filterkette fort
        filterChain.doFilter(request, response);
    }
}

 */