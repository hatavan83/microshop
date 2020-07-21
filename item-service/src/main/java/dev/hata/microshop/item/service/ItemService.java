package dev.hata.microshop.item.service;

import dev.hata.microshop.item.dto.ItemRequest;
import dev.hata.microshop.item.dto.ItemResponse;
import reactor.core.publisher.Mono;

public interface ItemService {
    Mono<ItemResponse> create(ItemRequest request);
    Mono<ItemResponse> get(String id);

}
