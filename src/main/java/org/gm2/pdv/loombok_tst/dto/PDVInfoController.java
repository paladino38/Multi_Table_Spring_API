package org.gm2.pdv.loombok_tst.dto;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class PDVInfoController {
    public ResponseEntity get(){
        return ResponseEntity.ok().build();
    }
}
