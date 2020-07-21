package dev.hata.microshop.item.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ItemResponse extends ItemRequest{
    private String id;
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;
    private String created_by;
    private String updated_by;
}
