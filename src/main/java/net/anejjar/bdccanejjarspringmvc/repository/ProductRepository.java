package net.anejjar.bdccanejjarspringmvc.repository;

import net.anejjar.bdccanejjarspringmvc.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
