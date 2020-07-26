package dev.hata.microshop.item.service.impl;

import dev.hata.microshop.item.converter.ItemConverter;
import dev.hata.microshop.item.dto.ItemRequest;
import dev.hata.microshop.item.dto.ItemResponse;
import dev.hata.microshop.item.entity.Item;
import dev.hata.microshop.item.exception.NotFoundEntityException;
import dev.hata.microshop.item.repository.ItemRepository;
import dev.hata.microshop.item.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import java.util.Collection;

@Service
@AllArgsConstructor(onConstructor_ = {@Inject})
public class ItemServiceImpl implements ItemService {
    private ItemRepository repository;

    private ItemConverter converter;

    @Override
    public Mono<ItemResponse> create(ItemRequest request) {
        Item item = converter.toEntity(request);
        val now = System.currentTimeMillis();
        item.setCreatedAt(now);
        item.setUpdatedAt(now);
        return repository.save(item).map(converter::fromEntity);
    }

    @Override
    public Mono<ItemResponse> update(String id, ItemRequest request) {
        return findById(id)
                .switchIfEmpty(Mono.error(new NotFoundEntityException(Item.class, id)))
                .map(item -> converter.toEntity(request, item))
                .map(item -> {
                    item.setUpdatedAt(System.currentTimeMillis());
                    return item;
                })
                .flatMap(repository::save)
                .map(converter::fromEntity);
    }

    @Override
    public Mono<ItemResponse> get(String id) {
        return findById(id)
                .switchIfEmpty(Mono.error(new NotFoundEntityException(Item.class, id)))
                .map(converter::fromEntity);
    }

    @Override
    public Flux<ItemResponse> search(Collection<String> ids) {
        val result = CollectionUtils.isEmpty(ids) ? repository.findAll() : repository.findAllById(ids);
        return result.map(converter::fromEntity);
    }

    @Override
    public Mono<Void> delete(String id) {
        return findById(id).switchIfEmpty(Mono.error(new NotFoundEntityException(Item.class, id)))
                .map(item -> {
                    item.setDeletedAt(System.currentTimeMillis());
                    return item;
                })
                .map(repository::save)
                .then();
    }

    private Mono<Item> findById(String id) {
        return repository.findById(id)
                .filter(item -> item.getDeletedAt() == null);
    }
}
