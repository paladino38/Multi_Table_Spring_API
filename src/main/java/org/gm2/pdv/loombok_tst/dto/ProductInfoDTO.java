package org.gm2.pdv.loombok_tst.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInfoDTO {
    private String description;
    private int quantity;
    private Long id;
    private BigDecimal price;
}
