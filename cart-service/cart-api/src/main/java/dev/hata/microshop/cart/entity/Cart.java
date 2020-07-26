package dev.hata.microshop.cart.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Cart {
    private String id;
    private Map<String, Long> items;

    public static Cart of(String id) {
        return new Cart(id, new HashMap<>());
    }
}
