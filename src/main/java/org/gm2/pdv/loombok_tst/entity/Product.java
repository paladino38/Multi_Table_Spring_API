package org.gm2.pdv.loombok_tst.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Descrição é obrigatoria")
    private String description;

    @Column(length = 30, precision = 20, scale = 2)
    @NotNull(message = "price é obrigatoria")
    private BigDecimal price;


    @Column( nullable = false)
    @NotNull(message = "quantity é obrigatoria")
    private int quantity;
}

