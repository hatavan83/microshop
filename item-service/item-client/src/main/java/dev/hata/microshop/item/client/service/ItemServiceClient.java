package dev.hata.microshop.item.client.service;

import dev.hata.microshop.item.dto.ItemRequest;
import dev.hata.microshop.item.dto.ItemResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface ItemServiceClient {

    Mono<ItemResponse> create(ItemRequest request);

    Mono<ItemResponse> update(String id, ItemRequest request);

    Mono<ItemResponse> get(String id);

    Flux<ItemResponse> search(Collection<String> ids);

    Mono<Void> delete(String id);
}
