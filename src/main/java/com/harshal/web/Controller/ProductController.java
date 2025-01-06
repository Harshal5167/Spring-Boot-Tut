package com.harshal.web.Controller;

import com.harshal.web.Model.Product;
import com.harshal.web.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{productId}") //another way to write the get request
    public Product getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return "Product added successfully";
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return "Product deleted successfully";
    }

    @PutMapping("/updateProduct")
    public String updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
        return "Product updated successfully";
    }
}

