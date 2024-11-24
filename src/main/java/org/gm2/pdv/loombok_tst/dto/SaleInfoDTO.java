package org.gm2.pdv.loombok_tst.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SaleInfoDTO {
    private String user;
    private String date;
    private List<ProductInfoDTO> products;
}
