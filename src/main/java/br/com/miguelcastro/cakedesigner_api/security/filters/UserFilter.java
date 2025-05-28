package br.com.miguelcastro.cakedesigner_api.security.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.miguelcastro.cakedesigner_api.security.providers.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (request.getRequestURI().startsWith("/user") && header != null) {
            var token = this.jwtProvider.validateToken(header);

            if (token == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            request.setAttribute("user_id", token.getSubject());
            var roles = token.getClaim("roles").asList(Object.class);
            var grants = roles.stream().map(
                    role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
                    .toList();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token.getSubject(), null,
                    grants);

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}
