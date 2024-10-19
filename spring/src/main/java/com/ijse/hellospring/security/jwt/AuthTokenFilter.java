package com.ijse.hellospring.security.jwt;

import ch.qos.logback.core.util.StringUtil;
import com.ijse.hellospring.security.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailServiceImpl userDetailService;


    private String parseJwtFromHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        } else {
            return null;
        }
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = parseJwtFromHeader(request);         //meken krnne req eken token eka vena krla  ganna eka.
            if (jwtToken != null && jwtUtils.validateJwtToken(jwtToken)) {        // meken krnne eq eken gttu token eka validate da kiyla blna eka.
                String username = jwtUtils.getUsernameFromJwtToken(jwtToken);        //tokene ekene usernmae eka ven krla gnnva.me serma kalin create krpu method.
                UserDetails userDetails = userDetailService.loadUserByUsername(username);   //userva load krgnnva userDetails kiyna variable ekata
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());  //meken krnne user log unahama authentication object ekk hdgnnva userDetails use krla .
                authentication.setDetails(new WebAuthenticationDetails(request));    //req ekata add krnva authnentication details tika.
                SecurityContextHolder.getContext().setAuthentication(authentication);   //security context eka update krnva.ethkota systema eka dnnva me req eka  evnne authentication user kenek kiyla
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request,response);
    }
}


