package org.gm2.pdv.loombok_tst.service;

import org.gm2.pdv.loombok_tst.dto.UserDTO;
import org.gm2.pdv.loombok_tst.dto.UserResponseDTO;
import org.gm2.pdv.loombok_tst.entity.User;
import org.gm2.pdv.loombok_tst.exception.NoItemException;
import org.gm2.pdv.loombok_tst.repository.UserRepository;
import org.gm2.pdv.loombok_tst.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<UserResponseDTO> findAll(){
        return userRepository.findAll().stream().map(user -> new UserResponseDTO(user.getId(),user.getName(),user.isEnabled(), user.getUsername())).collect(Collectors.toList());

    }

    public UserDTO save(User user){
        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return new UserDTO(user.getId(), user.getName(), user.isEnabled(), user.getUsername(), user.getPassword());
    }

    public UserDTO findById(Long id){
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            throw new NoItemException("user n existe");
        }
        User user = optional.get();
        return new UserDTO(user.getId(), user.getName(), user.isEnabled(), user.getUsername(), user.getPassword());
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public UserDTO upodate(User user){
        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        Optional<User> userToEdit = userRepository.findById(user.getId());
        if (!userToEdit.isPresent()) {
            throw new NoItemException("user n existe");
        }
        userRepository.save(user);
        return new UserDTO(user.getId(), user.getName(), user.isEnabled(), user.getUsername(), user.getPassword());
    }

    public User getByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
}
