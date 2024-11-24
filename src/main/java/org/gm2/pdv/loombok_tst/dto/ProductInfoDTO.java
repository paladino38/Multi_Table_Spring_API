package org.gm2.pdv.loombok_tst.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoDTO {
    private String description;
    private int quantity;
}
