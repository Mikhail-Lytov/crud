package com.example.crud.configuration.crud.bigserial.controller;

import com.example.crud.configuration.crud.dto.AbstractResponseDTO;
import com.example.crud.configuration.crud.bigserial.model.AbstractEntity;
import com.example.crud.configuration.crud.specification.request.SearchRequest;
import com.example.crud.web.advice.exception.DeleteException;
import com.example.crud.web.advice.exception.FindException;
import com.example.crud.web.advice.exception.SaveException;
import com.example.crud.web.advice.exception.UpdateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The AbstractControllerString interface defines the contract for controllers that handle HTTP requests related to CRUD operations on entities.
 *
 * @param <E> The type of entity that the controller operates on, must extend AbstractEntity.
 */
public interface AbstractController<E extends AbstractEntity> {
    @Operation(summary = "Получить ВСЕ", security = @SecurityRequirement(name = "JWT"))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("all")
    ResponseEntity<List<E>> getAll() throws FindException;

    @Operation(summary = "Получить постранично", security = @SecurityRequirement(name = "JWT"))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    ResponseEntity<AbstractResponseDTO> getPage(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) throws FindException;

    @Operation(summary = "Получить все уникальные значение по названию поля", security = @SecurityRequirement(name = "JWT"))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("distinct")
    ResponseEntity<List<?>> findAllUniqueByFieldName(@RequestParam String fieldName) throws FindException;

    @Operation(summary = "Поиск по фильтрам", security = @SecurityRequirement(name = "JWT"))
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("filter")
    ResponseEntity<AbstractResponseDTO> searchFilter(@RequestBody SearchRequest request) throws FindException;

    @Operation(summary = "Получить по id", security = @SecurityRequirement(name = "JWT"))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    ResponseEntity<E> getOne(@PathVariable Long id) throws FindException;

    @Operation(summary = "Обновить данные", security = @SecurityRequirement(name = "JWT"))
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping
    ResponseEntity<E> update(@RequestBody E update) throws FindException, UpdateException, CloneNotSupportedException;

    @Operation(summary = "Создать сущность", security = @SecurityRequirement(name = "JWT"))
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    ResponseEntity<E> create(@RequestBody E create) throws SaveException;

    @Operation(summary = "Удалить сущность по id (HARD)", security = @SecurityRequirement(name = "JWT"))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) throws DeleteException;
}
