package com.harshal.web.Service;

import com.harshal.web.Model.Product;
import com.harshal.web.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Component
public class ProductService {

//    List<Product> products= new ArrayList<>(Arrays.asList( //adding the new keyword makes the arraylist mutable and new data can be added in it.
//            new Product(1,"Iphone",1000),
//            new Product(2,"Samsung",800),
//            new Product(3,"OnePlus",700),
//            new Product(4,"Google",600),
//            new Product(5,"Xiaomi",500)
//    ));

    @Autowired
    ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int productId) {
        return productRepo.findById(productId).orElse(new Product("No Item", "-", "-", new BigDecimal("0.00"), "-", new Date(2024, Calendar.JANUARY, 1), false, 0));
    }

    public void addProduct(Product product) {
        productRepo.save(product);
    }

    public void deleteProduct(int productId) {
        productRepo.deleteById(productId);
    }

    public void updateProduct(Product product) {
        productRepo.save(product);
    }
}
