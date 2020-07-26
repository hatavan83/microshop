package dev.hata.microshop.cart.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CartItemResponse {
    private String id;
    private String name;
    private BigDecimal price;
    private Long stockQty;
    private Long qty;
    private Long deletedAt;

    public boolean isOutOfStock() {
        return stockQty < qty;
    }
}
