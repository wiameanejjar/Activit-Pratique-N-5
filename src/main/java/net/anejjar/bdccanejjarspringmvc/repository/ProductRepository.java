package net.anejjar.bdccanejjarspringmvc.repository;

import net.anejjar.bdccanejjarspringmvc.entities.Product;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByNameContains(String keyword, Pageable pageable);
}
