package dev.hata.microshop.cart.converter.decorator;

import dev.hata.microshop.cart.converter.CartItemConverter;
import dev.hata.microshop.cart.dto.CartItemResponse;
import dev.hata.microshop.item.client.service.ItemServiceClient;
import lombok.val;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public abstract class CartItemConverterDecorator implements CartItemConverter {
    @Inject
    private ItemServiceClient itemServiceClient;

    @Named("toCartItems")
    public Collection<CartItemResponse> toCartItems(Map<String, Long> items) {
        val ids = items.keySet();
        return CollectionUtils.isEmpty(ids) ? Collections.EMPTY_LIST : itemServiceClient.search(ids)
                .map(item -> this.toCartItem(item, items.get(item.getId())))
                .collectList()
                .block();
    }
}
