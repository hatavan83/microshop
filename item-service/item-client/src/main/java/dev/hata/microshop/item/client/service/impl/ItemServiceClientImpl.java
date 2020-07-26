package dev.hata.microshop.item.client.service.impl;

import dev.hata.microshop.item.client.service.ItemServiceClient;
import dev.hata.microshop.item.dto.ItemRequest;
import dev.hata.microshop.item.dto.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

import static dev.hata.microshop.item.PathConfig.BASE_URL;
import static dev.hata.microshop.item.PathConfig.ID_URL;

@RequiredArgsConstructor
public class ItemServiceClientImpl implements ItemServiceClient {
    private final WebClient client;

    @Override
    public Mono<ItemResponse> create(ItemRequest request) {
        return client.post()
                .uri(BASE_URL)
                .body(Mono.just(request), ItemRequest.class)
                .retrieve()
                .bodyToMono(ItemResponse.class);
    }

    @Override
    public Mono<ItemResponse> update(String id, ItemRequest request) {
        return client.put()
                .uri(ID_URL, id)
                .body(Mono.just(request), ItemRequest.class)
                .retrieve()
                .bodyToMono(ItemResponse.class);
    }

    @Override
    public Mono<ItemResponse> get(String id) {
        return client.get()
                .uri(ID_URL, id)
                .retrieve()
                .bodyToMono(ItemResponse.class);
    }

    @Override
    public Flux<ItemResponse> search(Collection<String> ids) {
        return client.get()
                .uri(builder -> builder.path(BASE_URL)
                        .queryParam("ids", ids)
                        .build())
                .retrieve()
                .bodyToFlux(ItemResponse.class);
    }

    @Override
    public Mono<Void> delete(String id) {
        return client.delete()
                .uri(ID_URL, id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
