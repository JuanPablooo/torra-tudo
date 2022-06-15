package com.torra.tudo.auth.security.user;

import com.torra.commons.data.model.ApplicationUser;
import com.torra.commons.data.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ApplicationUserRepository applicationUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Searching user by userName [{}]", username);
        ApplicationUser userFound = applicationUserRepository.findByUserName(username);

        if(Objects.isNull(userFound)) throw new UsernameNotFoundException(String.format("Application user '%s' not found ", username));
        return new CustomUserDetails(userFound);
    }

}
