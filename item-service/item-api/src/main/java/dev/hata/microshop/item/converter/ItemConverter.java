package dev.hata.microshop.item.converter;

import dev.hata.microshop.item.dto.ItemRequest;
import dev.hata.microshop.item.dto.ItemResponse;
import dev.hata.microshop.item.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemConverter {
    ItemResponse fromEntity(Item entity);

    Item toEntity(ItemRequest request);

    Item toEntity(ItemRequest request, @MappingTarget Item item);
}
