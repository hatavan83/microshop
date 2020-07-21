package dev.hata.microshop.item.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Document
@Getter
@Setter
public class Item {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
    Long quantity;
    private String brand;
    private List<String> tags;
    private String description;
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;
    private String created_by;
    private String updated_by;
}
