package org.gm2.pdv.loombok_tst.service;

import org.gm2.pdv.loombok_tst.dto.UserDTO;
import org.gm2.pdv.loombok_tst.entity.User;
import org.gm2.pdv.loombok_tst.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(user -> new UserDTO(user.getId(),user.getName(),user.isEnabled())).collect(Collectors.toList());

    }

    public User save(User user){
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id){
        return Optional.ofNullable(userRepository.findById(id).orElse(null));

    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
