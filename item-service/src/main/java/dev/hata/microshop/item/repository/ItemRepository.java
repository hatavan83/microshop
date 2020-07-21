package dev.hata.microshop.item.repository;

import dev.hata.microshop.item.entity.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ReactiveCrudRepository<Item, String> {
}