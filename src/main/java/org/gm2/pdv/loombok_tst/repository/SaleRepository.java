package org.gm2.pdv.loombok_tst.repository;


import org.gm2.pdv.loombok_tst.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}