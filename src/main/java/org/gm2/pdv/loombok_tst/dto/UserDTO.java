package org.gm2.pdv.loombok_tst.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private Boolean isEnabled;
    private String username;
    private String password;

}
