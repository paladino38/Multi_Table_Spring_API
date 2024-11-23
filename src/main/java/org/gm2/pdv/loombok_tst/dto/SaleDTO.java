package org.gm2.pdv.loombok_tst.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gm2.pdv.loombok_tst.entity.Product;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private long userId;

    List<ProductDTO> items;
}
