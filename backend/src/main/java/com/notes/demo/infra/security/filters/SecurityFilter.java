package com.notes.demo.infra.security.filters;

import com.notes.demo.domain.user.UserAccount;
import com.notes.demo.domain.user.UserPrincipal;
import com.notes.demo.repositories.UserRepository;
import com.notes.demo.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = this.recoverToken(request);
        if(token != null){
            var subject = tokenService.validateToke(token);
            if(!subject.isEmpty()){
                UserAccount user = userRepository.findByUsername(subject);
                UserDetails userDetails = new UserPrincipal(user);

                if(user != null){
                    var authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null,userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response); // chama o próximo filtro
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
