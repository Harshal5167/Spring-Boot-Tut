package com.harshal.web.Service;

import com.harshal.web.Model.Product;
import com.harshal.web.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        return productRepo.findById(productId).orElse(null);
    }

    public void addProduct(Product product, MultipartFile ImageFile) throws IOException {
        product.setImageName(ImageFile.getOriginalFilename());
        product.setImageType(ImageFile.getContentType());
        product.setImageData(ImageFile.getBytes());
        productRepo.save(product);
    }

    public void deleteProduct(int productId) {
        productRepo.deleteById(productId);
    }

    public void updateProduct(int productId, Product product, MultipartFile imageFile) throws IOException {
        product.setImageData(imageFile.getBytes());
        product.setImageType(imageFile.getContentType());
        product.setImageName(imageFile.getOriginalFilename());
        productRepo.save(product);
    }

    public byte[] getProductImage(int productId) {
        Product product = productRepo.findById(productId).orElse(null);
        if (product != null) return product.getImageData();
        return null;
    }

    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
