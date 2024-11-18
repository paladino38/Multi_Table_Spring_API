package org.gm2.pdv.pdv.repository;

import org.gm2.pdv.pdv.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
