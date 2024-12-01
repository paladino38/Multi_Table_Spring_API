package org.gm2.pdv.loombok_tst.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.gm2.pdv.loombok_tst.dto.ProductInfoDTO;
import org.gm2.pdv.loombok_tst.dto.SaleDTO;
import org.gm2.pdv.loombok_tst.dto.SaleInfoDTO;
import org.gm2.pdv.loombok_tst.entity.Product;
import org.gm2.pdv.loombok_tst.entity.ItemSale;
import org.gm2.pdv.loombok_tst.dto.ProductDTO;
import org.gm2.pdv.loombok_tst.entity.Sale;
import org.gm2.pdv.loombok_tst.entity.User;
import org.gm2.pdv.loombok_tst.exception.InvalidOperationException;
import org.gm2.pdv.loombok_tst.exception.NoItemException;
import org.gm2.pdv.loombok_tst.repository.ItemSaleRepository;
import org.gm2.pdv.loombok_tst.repository.ProductRepository;
import org.gm2.pdv.loombok_tst.repository.SaleRepository;
import org.gm2.pdv.loombok_tst.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class SaleService {

    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ItemSaleRepository itemSaleRepository;


    public List<SaleInfoDTO> findAll() {
        return saleRepository.findAll().stream().map(sale ->getSaleInfo(sale)).collect(Collectors.toList());
    }

    private SaleInfoDTO getSaleInfo(Sale sale) {
        var prodcuts = getProductInfo(sale.getItems());
        BigDecimal total = getTotal(prodcuts);
        return SaleInfoDTO.builder()
                .user(sale.getUser().getName())
                .date(sale.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .products(prodcuts)
                .total(total)
                .build();
    }

    private BigDecimal getTotal(List<ProductInfoDTO> prodcuts) {
        BigDecimal total = BigDecimal.ZERO;
        for(int i = 0; i< prodcuts.size(); i++) {
            ProductInfoDTO currentProduct = prodcuts.get(i);
            total = total.add(currentProduct.getPrice().multiply(new BigDecimal(currentProduct.getQuantity())));
        }
        return total;
    }

    private List<ProductInfoDTO> getProductInfo(List<ItemSale> items) {
        return items.stream().map(item ->{
            return ProductInfoDTO.builder()
                    .id(item.getId())
                    .price(item.getProduct().getPrice())
                    .description(item.getProduct().getDescription())
                    .quantity(item.getQuantity())

                    .build();
        }).collect(Collectors.toList());
    }


    @Transactional
    public long save(SaleDTO sale){
       Optional<User> optional = userRepository.findById(sale.getUserId());
       if (optional.isPresent()) {
           User user = optional.get();
           Sale newSale = new Sale();
           newSale.setUser(user);
           newSale.setDate(LocalDateTime.now());
           List<ItemSale> items = getItemSale(sale.getItems());

           newSale = saleRepository.save(newSale);

           saveItemSale(items, newSale);
           return newSale.getId();
       }


        return 0;
    }

    private void saveItemSale(List<ItemSale> items, Sale newSale) {
        for (ItemSale item : items) {
            item.setSale(newSale);
            itemSaleRepository.save(item);
        }
    }

    private List<ItemSale> getItemSale(List<ProductDTO> products){
        return products.stream().map(item ->{
            if (products.isEmpty()){
                throw  new NoItemException("sem item para venda");
            }
            Product product = productRepository.getReferenceById(item.getProductId());

            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);
            itemSale.setQuantity(item.getQuantity());
            if (product.getQuantity() == 0){
                try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new NoItemException("Quantidade requisitada maior que quantidade disponivel");
                }
            }else if (product.getQuantity() < item.getQuantity()){
                try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new InvalidOperationException("quantidade de items superior ao disponivel");
                }
            }
            int total = product.getQuantity()  - item.getQuantity();
            product.setQuantity(total);
            productRepository.save(product);

            return itemSale;
        }).collect(Collectors.toList());

    }

    public SaleInfoDTO getById(Long id) {
        Sale sale = saleRepository.findById(id).get();
        return getSaleInfo(sale);
    }
}
