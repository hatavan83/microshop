package dev.hata.microshop.item.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document
@Data
public class Item {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
    Long quantity;
    private String brand;
    private List<String> tags;
    private String description;
    private Long createdAt;
    private Long updatedAt;
    private Long deletedAt;
    private String createdBy;
    private String updatedBy;
}
