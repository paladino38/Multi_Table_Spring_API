package org.gm2.pdv.loombok_tst.repository;

import org.gm2.pdv.loombok_tst.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
