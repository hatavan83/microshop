package dev.hata.microshop.item;

public interface PathConfig {
    String BASE_URL = "/api/items";
    String PATH_ID = "/{id}";
    String ID_URL = BASE_URL + PATH_ID;
}
