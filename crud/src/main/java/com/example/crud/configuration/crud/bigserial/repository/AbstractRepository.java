package com.example.crud.configuration.crud.bigserial.repository;

import com.example.crud.configuration.crud.bigserial.model.AbstractEntity;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * An abstract repository interface that extends JpaRepository and JpaSpecificationExecutor.
 *
 * @param <T> The entity type.
 */
@NoRepositoryBean
public interface AbstractRepository<T extends AbstractEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    default List<?> getAllDistinctFields(String fieldName, String entityClassName, EntityManager entityManager) {
        return entityManager.createQuery(
                        "SELECT DISTINCT e." + fieldName + " FROM " + entityClassName + " e"
                )
                .getResultList();
    }
}
