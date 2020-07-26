package dev.hata.microshop.item.controller;

import dev.hata.microshop.item.dto.ItemRequest;
import dev.hata.microshop.item.dto.ItemResponse;
import dev.hata.microshop.item.exception.NotFoundEntityException;
import dev.hata.microshop.item.service.ItemService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Collection;

import static dev.hata.microshop.item.PathConfig.BASE_URL;
import static dev.hata.microshop.item.PathConfig.PATH_ID;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(value = BASE_URL)
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class ItemController {
    private final ItemService itemService;

    @PostMapping()
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "create Item")})
    @ResponseStatus(CREATED)
    public Mono<ItemResponse> create(@Valid @RequestBody ItemRequest request) {
        return itemService.create(request);
    }

    @PutMapping(PATH_ID)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "update Item")})
    public Mono<ItemResponse> update(@PathVariable String id, @Valid @RequestBody ItemRequest request) {
        return itemService.update(id, request)
                .onErrorMap(
                        NotFoundEntityException.class,
                        err -> new ResponseStatusException(
                                HttpStatus.valueOf(err.getStatusCode()),
                                err.getMessage(),
                                err
                        )
                );
    }

    @GetMapping(PATH_ID)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "get item detail")})
    public Mono<ItemResponse> get(@PathVariable String id) {
        return itemService.get(id)
                .onErrorMap(
                        NotFoundEntityException.class,
                        err -> new ResponseStatusException(
                                HttpStatus.valueOf(err.getStatusCode()),
                                err.getMessage(),
                                err
                        )
                );
    }

    @GetMapping()
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "search item list")})
    public Flux<ItemResponse> search(@RequestParam(required = false) Collection<String> ids) {
        return itemService.search(ids);
    }

    @DeleteMapping(PATH_ID)
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Delete item")})
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return itemService.delete(id);
    }
}
