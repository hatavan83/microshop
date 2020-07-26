package dev.hata.microshop.cart.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CartItemRequest {
    @NotBlank
    private String id;
    @Positive
    @NotNull
    private Long qty;

    private boolean isMerged;
}
