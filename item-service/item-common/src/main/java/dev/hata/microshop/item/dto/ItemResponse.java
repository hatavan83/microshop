package dev.hata.microshop.item.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ItemResponse extends ItemRequest {
    private String id;
    private Long createdAt;
    private Long updatedAt;
    private Long deletedAt;
    private String createdBy;
    private String updatedBy;
}
