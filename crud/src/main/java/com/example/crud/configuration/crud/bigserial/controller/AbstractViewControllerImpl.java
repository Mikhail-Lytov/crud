package com.example.crud.configuration.crud.bigserial.controller;

import com.example.crud.configuration.crud.bigserial.model.AbstractViewEntity;
import com.example.crud.configuration.crud.bigserial.service.AbstractViewService;
import com.example.crud.configuration.crud.dto.AbstractResponseDTO;
import com.example.crud.configuration.crud.specification.request.SearchRequest;
import com.example.crud.web.advice.exception.FilterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
public class AbstractViewControllerImpl<E extends AbstractViewEntity, S extends AbstractViewService> implements AbstractViewController<E> {

    protected final S service;


    /**
     * Performs a search and filtering operation on entities.
     *
     * @param request The SearchRequest object containing the search criteria.
     * @return The ResponseEntity containing the search results.
     */
    @Override
//    @ToLogger(action = ActionEnum.READ, actionDomain = ActionDomainEnum.CRUD, httpMethod = HttpMethodEnum.POST)
    public ResponseEntity<AbstractResponseDTO> searchFilter(@RequestBody SearchRequest request) throws FilterException {
        return ResponseEntity.ok(service.searchFilter(request));
    }

}
