package org.gm2.pdv.loombok_tst.service;

import lombok.RequiredArgsConstructor;
import org.gm2.pdv.loombok_tst.dto.SaleDTO;
import org.gm2.pdv.loombok_tst.entity.Product;
import org.gm2.pdv.loombok_tst.entity.ItemSale;
import org.gm2.pdv.loombok_tst.dto.ProductDTO;
import org.gm2.pdv.loombok_tst.entity.Sale;
import org.gm2.pdv.loombok_tst.entity.User;
import org.gm2.pdv.loombok_tst.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final UserRepository userRepository;


    public long save(SaleDTO sale){
        User user = userRepository.findById(sale.getUserId()).get();
        Sale newSale = new Sale();
        newSale.setUser(user);
        newSale.s
        newSale.setDate(LocalDateTime.now());
    }

    private List<ItemSale> getItemSale(List<ProductDTO> products>){

    }
}
