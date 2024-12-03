package org.gm2.pdv.loombok_tst.controller;

import jakarta.validation.Valid;
import org.gm2.pdv.loombok_tst.dto.LoginDTO;
import org.gm2.pdv.loombok_tst.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import java.util.Collections;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final View error;

    public LoginController(View error) {
        this.error = error;
    }

    @PostMapping
    public ResponseEntity post(@Valid @RequestBody LoginDTO loginData){
        try {

        }catch(Exception e) {
            return new ResponseEntity<>(new ResponseDTO(Collections.singletonList("deu erro")), HttpStatus.UNAUTHORIZED);
        }
    }
}
