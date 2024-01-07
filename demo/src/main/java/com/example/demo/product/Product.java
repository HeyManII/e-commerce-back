package com.example.demo.product;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Document(collection = "products")
@Builder
public class Product {

    @Id
    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String desc;

    @Min(value = 0, message = "Price must be larger than zero")
    private float price;

    @NotBlank
    private String img;

    @Min(0)
    @Max(5)
    private float review;

    @NotBlank
    private String category;

    public Product(@JsonProperty("id") String id,
                    @JsonProperty("title") String title,
                   @JsonProperty("desc") String desc,
                   @JsonProperty("price") float price,
                   @JsonProperty("img") String img,
                   @JsonProperty("review") float review,
                   @JsonProperty("category") String category) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.img = img;
        this.review = review;
        this.category = category;
    }
}
