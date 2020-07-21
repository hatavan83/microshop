package dev.hata.microshop.item.service.impl;

import dev.hata.microshop.item.converter.ItemConverter;
import dev.hata.microshop.item.dto.ItemRequest;
import dev.hata.microshop.item.dto.ItemResponse;
import dev.hata.microshop.item.entity.Item;
import dev.hata.microshop.item.repository.ItemRepository;
import dev.hata.microshop.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository repository;

    @Autowired
    private ItemConverter converter;

    @Override
    public Mono<ItemResponse> create(ItemRequest request) {
        Item item = converter.toEntity(request);
        return repository.save(item).map(converter::fromEntity);
    }

    @Override
    public Mono<ItemResponse> get(String id) {
        return repository.findById(id).map(converter::fromEntity);
    }
}
