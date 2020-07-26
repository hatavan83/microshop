package dev.hata.microshop.cart.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class CartResponse {
    private String id;
    private Collection<CartItemResponse> items;
}
