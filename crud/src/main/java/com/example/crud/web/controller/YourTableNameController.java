package com.example.crud.web.controller;


import com.example.crud.configuration.crud.string.controller.AbstractControllerStringImpl;
import com.example.crud.model.YourTableName;
import com.example.crud.service.YourTableNameService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RequestMapping("/your-table-name")
@Tag(name = "your-table-name")
public class YourTableNameController extends AbstractControllerStringImpl<YourTableName, YourTableNameService> {
    public YourTableNameController(YourTableNameService service) {
        super(service);
    }
}
