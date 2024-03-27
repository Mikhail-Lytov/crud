package com.example.crud.configuration.crud.bigserial.repository;

import com.example.crud.configuration.crud.bigserial.model.AbstractViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;


@NoRepositoryBean
@Transactional(readOnly = true)
public interface AbstractReadOnlyRepository<E extends AbstractViewEntity> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {
}
