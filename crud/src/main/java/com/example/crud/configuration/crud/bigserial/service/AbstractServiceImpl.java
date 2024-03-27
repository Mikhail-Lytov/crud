package com.example.crud.configuration.crud.bigserial.service;

import com.example.crud.configuration.crud.bigserial.model.AbstractEntity;
import com.example.crud.configuration.crud.bigserial.repository.AbstractRepository;
import com.example.crud.configuration.crud.dto.AbstractResponseDTO;
import com.example.crud.configuration.crud.specification.SearchSpecification;
import com.example.crud.configuration.crud.specification.request.SearchRequest;
import com.example.crud.configuration.crud.util.Reflection;
import com.example.crud.web.advice.exception.DeleteException;
import com.example.crud.web.advice.exception.FindException;
import com.example.crud.web.advice.exception.SaveException;
import com.example.crud.web.advice.exception.UpdateException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * An abstract implementation of the AbstractServiceString interface.
 *
 * @param <E> The entity type.
 * @param <R> The repository type.
 */
@Data
@RequiredArgsConstructor
public abstract class AbstractServiceImpl<E extends AbstractEntity, R extends AbstractRepository<E>> implements AbstractService<E> {

    @Autowired
    protected ModelMapper patchingMapper;

    @Autowired
    protected ObjectWriter objectWriter;

    protected final R repository;

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    protected ObjectMapper objectMapper;

    private String entityClassName;
    private Logger log;

    /**
     * Initializes the function.
     */
    @PostConstruct
    public void init() {
        entityClassName = Reflection.getClassGeneric(this).getCanonicalName() + "Service";
        log = LoggerFactory.getLogger(entityClassName);
    }


    /**
     * Saves the given entity to the database.
     *
     * @param entity The entity to save.
     * @return The saved entity.
     * @throws SaveException if an error occurs during the save operation.
     */
    @Override
    @Transactional
    public E save(E entity) throws SaveException {
        log.info("Saving entity: {}", entity);
        try {
            return repository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SaveException(e.getClass().getSimpleName() + " Can't save entity: " + e.getMessage());
        }
    }

    /**
     * Updates the given entity in the database.
     *
     * @param entity The entity to update.
     * @return The updated entity.
     * @throws UpdateException if an error occurs during the update operation.
     */
    @Override
    @Transactional
    public E update(E entity) throws UpdateException, FindException {
        E entityFromBd = repository.findById(entity.getId()).orElseThrow(() -> new FindException("Entity not found " + entity));
        log.info("Updating entity: from {} to {}", entity, entityFromBd);
        E entityToSave = (E) entityFromBd.cloneEntity();
        patchingMapper.map(entity, entityToSave);
        try {
            return repository.saveAndFlush(entityToSave);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UpdateException(e.getClass().getSimpleName() + " Can't update entity: " + e.getMessage());
        }
    }

    /**
     * Deletes the entity with the given ID from the database.
     *
     * @param id The ID of the entity to delete.
     * @throws DeleteException if an error occurs during the delete operation.
     */
    @Override
    @Transactional
    public void delete(Long id) throws DeleteException {
        try {
            E entity = repository.findById(id).orElseThrow(() -> new FindException("Entity not found with id: " + id));
            log.info("Deleting entity by id {}: {}", id, entity);
            repository.delete(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeleteException(e.getClass().getSimpleName() + " Can't delete with id: " + id + " " + e.getMessage());
        }
    }

    /**
     * Retrieves the entity with the given ID from the database.
     *
     * @param id The ID of the entity to retrieve.
     * @return The retrieved entity.
     * @throws FindException if the entity is not found.
     */
    @Override
    @Transactional(readOnly = true)
    public E findById(Long id) throws FindException {
        log.info("Finding entity by id: {}", id);
        return repository.findById(id).orElseThrow(() -> new FindException(" Entity not found with id " + id));
    }

    /**
     * Retrieves all entities from the database with pagination support.
     *
     * @param reqPage The reqPage number (0-based index).
     * @param reqSize The number of items per reqPage.
     * @return An AbstractResponseDTO object containing the list of entities and pagination information.
     * @throws FindException if an error occurs during the pagination operation.
     */
    @Override
    @Transactional(readOnly = true)
    public AbstractResponseDTO findAll(Integer reqPage, Integer reqSize) throws FindException {
        log.info("Finding all request, page: {}, size: {}", reqPage, reqSize);
        try {
            Pageable pageable = SearchSpecification.getPageable(reqPage, reqSize);
            Page<E> pageResponse = repository.findAll(pageable);
            return new AbstractResponseDTO(pageResponse.getContent(), pageResponse.getTotalElements(), pageResponse.getTotalPages());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FindException(e.getClass().getSimpleName() + " Find Exception: " + e.getMessage());
        }
    }

    /**
     * Performs a search query with filters based on the given SearchRequest.
     *
     * @param request The SearchRequest containing the search criteria.
     * @return An AbstractResponseDTO object containing the search results and pagination information.
     * @throws FindException if an error occurs during the search query execution.
     */
    @Override
    @Transactional(readOnly = true)
    public AbstractResponseDTO searchFilter(SearchRequest request) throws FindException {
        log.info("Search with filters: {}", request);
        Pageable pageable;
        try {
            SearchSpecification<E> specification = new SearchSpecification<>(request);
            if (request.getSize() != null) {
                pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
            } else {
                long countEntity = repository.count(specification);
                pageable = SearchSpecification.getPageable(request.getPage(), (int) countEntity);
            }
            var page = repository.findAll(specification, pageable);
            return new AbstractResponseDTO(page.getContent(), page.getTotalElements(), page.getTotalPages());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FindException(e.getClass().getSimpleName() + " Filter find exception: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> findAll() throws FindException {
        log.info("Finding all request");
        try {
            return repository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FindException(e.getClass().getSimpleName() + " Find Exception: " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<?> getAllUniqueValuesFromField(String fieldName) throws FindException {
        log.info("Get all unique values from field: {}", fieldName);
        try {
            Class<?> originalEntity = Class.forName(
                    Reflection.getClassGeneric(this).getName()
            );

            if (!fieldName.equalsIgnoreCase("id")) {
                try {
                    originalEntity.getDeclaredField(fieldName);
                } catch (Exception e) {
                    throw new FindException("Invalid field name: " + fieldName);
                }
            }

            return repository.getAllDistinctFields(fieldName, originalEntity.getSimpleName(), entityManager);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FindException(e);
        }
    }
}
