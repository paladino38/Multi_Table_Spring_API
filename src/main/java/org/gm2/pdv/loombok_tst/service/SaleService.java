package org.gm2.pdv.loombok_tst.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.gm2.pdv.loombok_tst.dto.SaleDTO;
import org.gm2.pdv.loombok_tst.entity.Product;
import org.gm2.pdv.loombok_tst.entity.ItemSale;
import org.gm2.pdv.loombok_tst.dto.ProductDTO;
import org.gm2.pdv.loombok_tst.entity.Sale;
import org.gm2.pdv.loombok_tst.entity.User;
import org.gm2.pdv.loombok_tst.repository.ItemSaleRepository;
import org.gm2.pdv.loombok_tst.repository.ProductRepository;
import org.gm2.pdv.loombok_tst.repository.SaleRepository;
import org.gm2.pdv.loombok_tst.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class SaleService {

    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ItemSaleRepository itemSaleRepository;

    @Transactional
    public long save(SaleDTO sale){
        User user = userRepository.findById(sale.getUserId()).get();
        Sale newSale = new Sale();
        newSale.setUser(user);
        newSale.setDate(LocalDateTime.now());
        List<ItemSale> items = getItemSale(sale.getItems());

        newSale = saleRepository.save(newSale);

        saveItemSale(items, newSale);

        return newSale.getId();
    }

    private void saveItemSale(List<ItemSale> items, Sale newSale) {
        for (ItemSale item : items) {
            item.setSale(newSale);
            itemSaleRepository.save(item);
        }
    }

    private List<ItemSale> getItemSale(List<ProductDTO> products){
        return products.stream().map(item ->{
            Product product = productRepository.getReferenceById(item.getProductId());

            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);
            itemSale.setQuantity(item.getQuantity());
            return itemSale;
        }).collect(Collectors.toList());

    }
}
