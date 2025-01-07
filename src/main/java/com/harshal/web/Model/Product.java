package com.harshal.web.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;

//    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") // This annotation tells Jackson to format the date in the specified pattern.
    private Date releaseDate;
    private boolean productAvailable;
    private int stockQuantity;

    private String imageName;
    private String imageType;

    @Lob // This annotation tells JPA that this field is a large object.
    private byte[] imageData; // This field will store the image data directly in the database in binary format.


    public Product(String name, String description, String brand, BigDecimal price, String category, Date releaseDate, boolean productAvailable, int stockQuantity) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.releaseDate = releaseDate;
        this.productAvailable = productAvailable;
        this.stockQuantity = stockQuantity;
    }

}
