package dev.hata.microshop.cart.converter;

import dev.hata.microshop.cart.converter.decorator.CartItemConverterDecorator;
import dev.hata.microshop.cart.dto.CartResponse;
import dev.hata.microshop.cart.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CartItemConverterDecorator.class})
public interface CartConverter {
    @Mapping(source = "cart.items", target = "items", qualifiedByName = "toCartItems")
    CartResponse fromEntity(Cart cart);
}
