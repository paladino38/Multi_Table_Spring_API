package org.gm2.pdv.pdv;

import org.gm2.pdv.pdv.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PdvApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdvApplication.class, args);

        User user = new User();

    }

}
