package com.example.crud.service;

import com.example.crud.configuration.crud.string.service.AbstractServiceImpl;
import com.example.crud.model.YourTableName;
import com.example.crud.repository.YourTableNameRepository;
import org.springframework.stereotype.Service;

@Service
public class YourTableNameService extends AbstractServiceImpl<YourTableName, YourTableNameRepository> {
    public YourTableNameService(YourTableNameRepository repository) {
        super(repository);
    }
}
