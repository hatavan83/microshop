package dev.hata.microshop.cart.converter;

import dev.hata.microshop.cart.converter.decorator.CartItemConverterDecorator;
import dev.hata.microshop.cart.dto.CartItemResponse;
import dev.hata.microshop.item.dto.ItemResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(CartItemConverterDecorator.class)
public interface CartItemConverter {
    @Mapping(source = "item.quantity", target = "stockQty")
    CartItemResponse toCartItem(ItemResponse item, Long qty);
}
