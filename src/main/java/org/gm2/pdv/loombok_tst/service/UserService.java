package org.gm2.pdv.loombok_tst.service;

import org.gm2.pdv.loombok_tst.dto.UserDTO;
import org.gm2.pdv.loombok_tst.entity.User;
import org.gm2.pdv.loombok_tst.exception.NoItemException;
import org.gm2.pdv.loombok_tst.repository.UserRepository;
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
    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(user -> new UserDTO(user.getId(),user.getName(),user.isEnabled())).collect(Collectors.toList());

    }

    public UserDTO save(User user){
        userRepository.save(user);
        return new UserDTO(user.getId(), user.getName(), user.isEnabled());
    }

    public UserDTO findById(Long id){
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            throw new NoItemException("user n existe");
        }
        User user = optional.get();
        return new UserDTO(user.getId(), user.getName(), user.isEnabled());
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public UserDTO upodate(User user){
        Optional<User> userToEdit = userRepository.findById(user.getId());
        if (!userToEdit.isPresent()) {
            throw new NoItemException("user n existe");
        }
        userRepository.save(user);
        return new UserDTO(user.getId(), user.getName(), user.isEnabled());
    }
}
