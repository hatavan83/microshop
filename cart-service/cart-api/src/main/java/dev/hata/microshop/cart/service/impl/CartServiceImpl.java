package dev.hata.microshop.cart.service.impl;

import dev.hata.microshop.cart.converter.CartConverter;
import dev.hata.microshop.cart.dto.CartItemRequest;
import dev.hata.microshop.cart.dto.CartResponse;
import dev.hata.microshop.cart.entity.Cart;
import dev.hata.microshop.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
@Service
public class CartServiceImpl implements CartService {

    private final ReactiveRedisTemplate<String, Cart> template;
    private final CartConverter cartConverter;

    @Override
    public Mono<CartResponse> updateCartItem(String id, CartItemRequest request) {
        return getValue(id).defaultIfEmpty(Cart.of(id))
                .map(cart -> updateCartItem(cart, request))
                .flatMap(cart -> updateCart(cart));
    }

    @Override
    public Mono<CartResponse> get(String id) {
        return getValue(id).defaultIfEmpty(Cart.of(id))
                .map(cartConverter::fromEntity);
    }

    @Override
    public Mono<CartResponse> removeCartItem(String id, String itemId) {
        return getValue(id).defaultIfEmpty(Cart.of(id))
                .map(cart -> removeCartItem(cart, itemId))
                .flatMap(cart -> updateCart(cart));
    }

    private ReactiveValueOperations<String, Cart> opsForValue() {
        return template.opsForValue();
    }

    private Mono<Cart> getValue(String id) {
        return opsForValue().get(id);
    }

    private Cart updateCartItem(Cart cart, CartItemRequest request) {
        val existQty = cart.getItems().getOrDefault(request.getId(), 0L);
        val qty = request.isMerged() ? existQty + request.getQty() : request.getQty();
        cart.getItems().put(request.getId(), qty);
        return cart;
    }

    private Cart removeCartItem(Cart cart, String itemId){
        cart.getItems().remove(itemId);
        return cart;
    }

    private Mono<CartResponse> updateCart(Cart cart) {
        return opsForValue().set(cart.getId(), cart)
                .flatMap(result -> {
                    if (result) {
                        return Mono.just(cartConverter.fromEntity(cart));
                    }
                    return Mono.error(new RuntimeException("Cannot update shopping cart"));
                });
    }
}
