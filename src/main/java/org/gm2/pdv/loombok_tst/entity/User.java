package org.gm2.pdv.loombok_tst.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Nome eh obrigatorio")
    private String name;

    @Column(length = 100, nullable = false, unique = true)
    @NotBlank(message = "Username é obrigatorio")
    private String username;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "password é obrigatorio")
    private String password;

    private boolean isEnabled;

    @OneToMany(mappedBy = "user")
    private List<Sale> sales;
}