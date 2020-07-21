package dev.hata.microshop.item.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ItemRequest {
    private String name;
    private BigDecimal price;
    Long quantity;
    private String brand;
    private List<String> tags;
    private String description;
}
