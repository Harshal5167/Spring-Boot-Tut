package com.harshal.web.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data; //lombok dependency simplifies the work of creating extra useful methods for Product class by creating them itself.
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Data // This annotation tells Lombok to generate getters and setters for all fields in this class.
@Component
@NoArgsConstructor // This annotation tells Lombok to generate a constructor with no arguments. This constructor will be used by Spring to create a bean of this class in ioc container.
@Entity // This annotation tells JPA that this class is an entity class, and it will be mapped to a table in the database.
public class Product {

    @Id // This annotation tells JPA that this field is the primary key of the table.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This annotation tells JPA that this field is auto-generated.
    private int id;
    private String name;
    private String desc;
    private String brand;
    private BigDecimal price;
    private String category;
    private Date releaseDate;
    private boolean available;
    private int quantity;

    public Product(String name, String desc, String brand, BigDecimal price, String category, Date releaseDate, boolean available, int quantity) {
        this.name = name;
        this.desc = desc;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.releaseDate = releaseDate;
        this.available = available;
        this.quantity = quantity;
    }

}
