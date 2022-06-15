package com.torra.tudo.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.torra.commons.data.model.ApplicationUser;
import com.torra.commons.data.property.JwtConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class JwtUserNameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtConfiguration jwtConfiguration;

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("Attempting authentication . . .");
        ApplicationUser applicationUserReceived = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);
        if(Objects.isNull(applicationUserReceived)) throw new UsernameNotFoundException("User Application not found");

        log.info("creating the authentication Object for user [{}] UserDetailService", applicationUserReceived.getUserName() );
        return super.attemptAuthentication(request, response);
    }
}
