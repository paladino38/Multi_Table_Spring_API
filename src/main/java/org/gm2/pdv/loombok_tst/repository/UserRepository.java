package org.gm2.pdv.loombok_tst.repository;

import org.gm2.pdv.loombok_tst.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

