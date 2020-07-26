package dev.hata.microshop.cart.service;

import dev.hata.microshop.cart.dto.CartItemRequest;
import dev.hata.microshop.cart.dto.CartResponse;
import reactor.core.publisher.Mono;

public interface CartService {
    Mono<CartResponse> updateCartItem(String userId, CartItemRequest request);

    Mono<CartResponse> get(String id);

    Mono<CartResponse> removeCartItem(String id, String itemId);
}
