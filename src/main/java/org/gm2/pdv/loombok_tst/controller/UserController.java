package org.gm2.pdv.loombok_tst.controller;

import org.gm2.pdv.loombok_tst.entity.User;
import org.gm2.pdv.loombok_tst.repository.UserRepository;
import org.gm2.pdv.loombok_tst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity getAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody User user ){
        try{
            user.setEnabled(true);
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        }catch(Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity put(@RequestBody User user ){

            Optional<User> userToEdit = userService.findById(user.getId());
            if (userToEdit.isPresent()) {
                userService.save(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            return  ResponseEntity.notFound().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        try{
            userService.deleteById(id);
            return new ResponseEntity<>("User removed",HttpStatus.OK);
        }catch (Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

