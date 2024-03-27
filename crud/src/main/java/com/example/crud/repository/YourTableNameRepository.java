package com.example.crud.repository;

import com.example.crud.model.YourTableName;
import com.example.crud.configuration.crud.string.repository.AbstractRepositoryString;
import org.springframework.stereotype.Repository;

@Repository
public interface YourTableNameRepository extends AbstractRepositoryString<YourTableName> {
}