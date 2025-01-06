package com.harshal.web.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data // This annotation tells Lombok to generate getters and setters for all fields in this class.
@Component
@NoArgsConstructor // This annotation tells Lombok to generate a constructor with no arguments. This constructor will be used by Spring to create a bean of this class in ioc container.
@Entity
public class Product {

    @Id
    int productId;
    String productName;
    int productPrice;

    public Product(int productId, String productName, int productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

}
