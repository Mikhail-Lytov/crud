package com.example.crud.configuration.crud.bigserial.service;


import com.example.crud.configuration.crud.dto.AbstractResponseDTO;
import com.example.crud.configuration.crud.specification.request.SearchRequest;
import com.example.crud.web.advice.exception.FilterException;

public interface AbstractViewService {
    AbstractResponseDTO searchFilter(SearchRequest request) throws FilterException;

}
