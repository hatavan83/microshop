package dev.hata.microshop.item.converter;

import dev.hata.microshop.item.dto.ItemRequest;
import dev.hata.microshop.item.dto.ItemResponse;
import dev.hata.microshop.item.entity.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemConverter {
    ItemResponse fromEntity(Item entity);
    Item toEntity(ItemRequest request);
}
