package dev.hata.microshop.item.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemRequest {
    @NotBlank
    private String name;
    @PositiveOrZero
    @NotNull
    private BigDecimal price;
    @PositiveOrZero
    Long quantity;
    @NotBlank
    private String brand;
    private List<String> tags;
    @NotBlank
    private String description;
}
