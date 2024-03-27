package com.example.crud.configuration.crud.bigserial.service;

import com.example.crud.configuration.crud.bigserial.model.AbstractViewEntity;
import com.example.crud.configuration.crud.bigserial.repository.AbstractReadOnlyRepository;
import com.example.crud.configuration.crud.dto.AbstractResponseDTO;
import com.example.crud.configuration.crud.specification.SearchSpecification;
import com.example.crud.configuration.crud.specification.request.SearchRequest;
import com.example.crud.configuration.crud.util.Reflection;
import com.example.crud.web.advice.exception.FilterException;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Data
@RequiredArgsConstructor
public class AbstractViewServiceImpl<E extends AbstractViewEntity, R extends AbstractReadOnlyRepository<E>> implements AbstractViewService {
    @Autowired
    protected ModelMapper patchingMapper;

    protected final R repository;

    private String entityClassName;

    private Logger log;

    @PostConstruct
    public void init() {
        entityClassName = Reflection.getClassGeneric(this).getCanonicalName() + "Service";
        log = LoggerFactory.getLogger(entityClassName);
    }

    /**
     * Performs a search query with filters based on the given SearchRequest.
     *
     * @param request The SearchRequest containing the search criteria.
     * @return An AbstractResponseDTO object containing the search results and pagination information.
     * @throws FilterException if an error occurs during the search query execution.
     */
    @Override
    @Transactional(readOnly = true)
    public AbstractResponseDTO searchFilter(SearchRequest request) throws FilterException {
        try {
            log.info("Search with filters: {}", request);
            SearchSpecification<E> specification = new SearchSpecification<>(request);
            Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
            var page = repository.findAll(specification, pageable);
            return new AbstractResponseDTO(page.getContent(), page.getTotalElements(), page.getTotalPages());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FilterException(e.getClass().getSimpleName() + " Filter exception: " + e.getMessage());
        }
    }

}
