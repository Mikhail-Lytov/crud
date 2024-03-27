package com.example.crud.configuration.crud.string.repository;

import com.example.crud.configuration.crud.string.entity.AbstractEntityString;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AbstractRepositoryString <T extends AbstractEntityString> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {

    default List<?> getAllDistinctFields(String fieldName, String entityClassName, EntityManager entityManager) {
        return entityManager.createQuery(
                        "SELECT DISTINCT e." + fieldName + " FROM " + entityClassName + " e"
                )
                .getResultList();
    }
}
