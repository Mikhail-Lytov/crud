package com.example.crud.configuration.crud.bigserial.service;


import com.example.crud.configuration.crud.bigserial.model.AbstractEntity;
import com.example.crud.configuration.crud.dto.AbstractResponseDTO;
import com.example.crud.configuration.crud.specification.request.SearchRequest;
import com.example.crud.web.advice.exception.SaveException;
import com.example.crud.web.advice.exception.UpdateException;
import com.example.crud.web.advice.exception.DeleteException;
import com.example.crud.web.advice.exception.FindException;

import java.util.List;

/**
 * The AbstractServiceString interface defines the contract for service classes that handle CRUD operations on entities.
 * It provides methods for saving, updating, deleting, and retrieving entities, as well as performing search and pagination.
 *
 * @param <E> The type of entity that the service operates on, must extend AbstractEntity.
 */
public interface AbstractService<E extends AbstractEntity> {
    E save(E entity) throws SaveException;

    E update(E entity) throws UpdateException, FindException;

    void delete(Long id) throws DeleteException;

    E findById(Long id) throws FindException;

    List<E> findAll() throws FindException;

    AbstractResponseDTO findAll(Integer page, Integer size) throws FindException;

    AbstractResponseDTO searchFilter(SearchRequest request) throws FindException;

    List<?> getAllUniqueValuesFromField(String fieldName) throws FindException;

}