package dev.hata.microshop.cart.controller;

import dev.hata.microshop.cart.dto.CartItemRequest;
import dev.hata.microshop.cart.dto.CartResponse;
import dev.hata.microshop.cart.service.CartService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class CartController {

    private final CartService service;

    @PutMapping("/{id}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "update shopping cart item")})
    public Mono<CartResponse> updateItem(@PathVariable String id, @Valid @RequestBody CartItemRequest request) {
        return service.updateCartItem(id, request);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "get all items in shopping cart")})
    public Mono<CartResponse> get(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}/{itemId}")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "update shopping cart item")})
    public Mono<CartResponse> removeItem(@PathVariable String id, @PathVariable String itemId) {
        return service.removeCartItem(id, itemId);
    }
}
