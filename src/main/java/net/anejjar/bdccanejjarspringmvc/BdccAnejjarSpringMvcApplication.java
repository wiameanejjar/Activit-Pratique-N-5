package net.anejjar.bdccanejjarspringmvc;

import net.anejjar.bdccanejjarspringmvc.entities.Product;
import net.anejjar.bdccanejjarspringmvc.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;

@SpringBootApplication
public class BdccAnejjarSpringMvcApplication {

    public static void main(String[] args) {

        SpringApplication.run(BdccAnejjarSpringMvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                            .name("Computer")
                            .price(5400)
                            .quantity(12)
                    .build());
            productRepository.save(Product.builder()
                    .name("Printer")
                    .price(1200)
                    .quantity(11)
                    .build());
            productRepository.save(Product.builder()
                    .name("Smart Phone")
                    .price(120000)
                    .quantity(33)
                    .build());
            productRepository.findAll().forEach(
                    product -> {
                        System.out.println(product.toString());
                    }
            );

        };
    }

}
