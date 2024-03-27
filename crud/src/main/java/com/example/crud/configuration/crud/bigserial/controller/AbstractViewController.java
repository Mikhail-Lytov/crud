package com.example.crud.configuration.crud.bigserial.controller;

import com.example.crud.configuration.crud.bigserial.model.AbstractViewEntity;
import com.example.crud.configuration.crud.dto.AbstractResponseDTO;
import com.example.crud.configuration.crud.specification.request.SearchRequest;
import com.example.crud.web.advice.exception.FilterException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface AbstractViewController<E extends AbstractViewEntity> {

    @Operation(summary = "Поиск по фильтрам", security = @SecurityRequirement(name = "JWT"))
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("filter")
    ResponseEntity<AbstractResponseDTO> searchFilter(@RequestBody SearchRequest request) throws FilterException;


}
