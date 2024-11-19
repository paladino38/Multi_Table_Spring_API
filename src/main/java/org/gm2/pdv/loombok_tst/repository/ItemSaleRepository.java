package org.gm2.pdv.loombok_tst.repository;

import org.gm2.pdv.loombok_tst.entity.ItemSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemSaleRepository extends JpaRepository<ItemSale, Long> {
}

