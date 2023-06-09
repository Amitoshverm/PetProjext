package com.blogapis.bloggingappllication.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. get token
        String requestToken = request.getHeader("Authorization");
        //Bearer 23456asdfag
        System.out.println(requestToken);

        String username = null;
        String token = null;

        if (requestToken != null && requestToken.startsWith("Bearer"))
        {
            token =  requestToken.substring(7);
            try{
                username = this.jwtTokenHelper.getUsernameFromToken(token);
            }catch (IllegalArgumentException e) {
                System.out.println("unable to get jwt token");
            }catch (ExpiredJwtException e) {
                System.out.println("jwt token has expired");
            }catch (MalformedJwtException e) {
                System.out.println("invalid jwt");
            }
        }
        else
        {
            System.out.println("Jwt token does not begin with Bearer");
        }

        // Once we have the token, Now validate
        if (username != null && SecurityContextHolder.getContext().getAuthentication()==null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtTokenHelper.validateToken(token, userDetails))
            {
                // every thing going good so far
                // now authenticate
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
            else
            {
                System.out.println("jwt is invalid");
            }
        }
        else {
            System.out.println("username is null or context is not null");
        }
        filterChain.doFilter(request, response);
    }
}
