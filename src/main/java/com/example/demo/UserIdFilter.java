package com.example.demo;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

public class UserIdFilter implements Filter {

    @Autowired
    private BenutzerRepository benutzerRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String benutzerIdHeader = httpRequest.getHeader("benutzerId");

        if (benutzerIdHeader == null || benutzerIdHeader.isEmpty()) {
            httpResponse.getWriter().write("BenutzerId Header fehlt!");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Unauthorized
            return;
        }

        // Benutzer überprüfen
        Long benutzerId;
        try {
            benutzerId = Long.parseLong(benutzerIdHeader);
        } catch (NumberFormatException e) {
            httpResponse.getWriter().write("Ungültige BenutzerId!");
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Bad Request
            return;
        }

        if (!benutzerRepository.existsById(benutzerId)) {
            httpResponse.getWriter().write("Benutzer nicht gefunden!");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Unauthorized
            return;
        }

        // Benutzer aus der Datenbank abrufen
        Optional<Benutzer> optionalBenutzer = benutzerRepository.findById(benutzerId);
        if (!optionalBenutzer.isPresent()) {
            httpResponse.getWriter().write("Benutzer nicht gefunden!");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Unauthorized
            return;
        }

        // Benutzer im Request speichern
        httpRequest.setAttribute("authenticatedUser", optionalBenutzer.get());

        // Weiter mit der Anfragekette
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
