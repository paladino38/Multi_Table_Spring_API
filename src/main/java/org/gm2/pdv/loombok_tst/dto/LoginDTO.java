package org.gm2.pdv.loombok_tst.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @NotBlank(message = "login obrigatorio ")
    private String username;
    @NotBlank(message = "senha obrigatorio ")
    private String password;
}
