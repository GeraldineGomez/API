package com.simm.sensor.api.security;

import com.google.firebase.auth.FirebaseToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String idToken = securityUtils.getTokenFromRequest(request);
        FirebaseToken decodedToken = null;
        // try {
        //     decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        // } catch (Exception e) {
        //     log.error("Firebase Exception {}", e.getLocalizedMessage());
        //     System.out.println("No hay autenticacion");
        // }
        if (decodedToken == null) {
            CustomPrincipal customPrincipal = new CustomPrincipal();
            customPrincipal.setUid("1234");
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    customPrincipal, idToken, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}