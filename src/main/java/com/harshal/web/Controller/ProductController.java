package com.harshal.web.Controller;

import com.harshal.web.Model.Product;
import com.harshal.web.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/product/{productId}") //another way to write the get request
    public ResponseEntity<Product> getProductById(@PathVariable int productId) { //ResponseEntity is a generic class that can fit and return any type of object
        Product product = productService.getProductById(productId);
        if (product != null) return new ResponseEntity<>(product, HttpStatusCode.valueOf(200));
        return new ResponseEntity<>(HttpStatusCode.valueOf(404));
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) { //the ? in RequestEntity object type is a wildcard that can be used to represent any type of object
        //@RequestPart is used to partition the data into two parts, one for the product json object and the other for the image file data.
        try {
            productService.addProduct(product, imageFile);
            return new ResponseEntity<>(HttpStatusCode.valueOf(201));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping("product/{productId}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable int productId) {
        byte[] image = productService.getProductImage(productId);
        if (image != null) return new ResponseEntity<>(image, HttpStatusCode.valueOf(200));
        return new ResponseEntity<>(HttpStatusCode.valueOf(404));
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int productId) {
        try{
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable int productId, @RequestPart Product product, @RequestPart MultipartFile imageFile) {
        try{
            productService.updateProduct(productId, product, imageFile);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }
}

