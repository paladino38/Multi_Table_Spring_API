package org.gm2.pdv.loombok_tst.security;

import org.gm2.pdv.loombok_tst.dto.LoginDTO;
import org.gm2.pdv.loombok_tst.entity.User;
import org.gm2.pdv.loombok_tst.repository.UserRepository;
import org.gm2.pdv.loombok_tst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }

    public void verifyUserCredentials(LoginDTO login){
        UserDetails user = loadUserByUsername(login.getUsername());
        boolean passwordIsTheSame = SecurityConfig.passwordEncoder().matches(login.getPassword(), user.getPassword());
        if(!passwordIsTheSame){
            throw new BadCredentialsException("Bad credentials");
        }

    }
}