package org.gm2.pdv.loombok_tst.security;

import org.gm2.pdv.loombok_tst.entity.User;
import org.gm2.pdv.loombok_tst.repository.UserRepository;
import org.gm2.pdv.loombok_tst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
import java.nio.file.attribute.UserPrincipal;
@Service
public class CustomUserDetailService  implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
   // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     //  User user = userService.getByUsername(username);
      // return UserPrincipal.create(user);

   // }
}
*/