package com.harshal.web.Service;

import com.harshal.web.Model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Component
public class ProductService {

    List<Product> products= new ArrayList<>(Arrays.asList( //adding the new keyword makes the arraylist mutable and new data can be added in it.
            new Product(1,"Iphone",1000),
            new Product(2,"Samsung",800),
            new Product(3,"OnePlus",700),
            new Product(4,"Google",600),
            new Product(5,"Xiaomi",500)
    ));

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int productId) {
        return products.stream()
                .filter(product -> product.getProductId() == productId)
                .findFirst().orElse(new Product(0, "No Item", 0));
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(int productId) {
        products.removeIf(product -> product.getProductId() == productId);
    }

    public void updateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == product.getProductId()) {
                products.set(i, product);
                return;
            }
        }
    }
}
