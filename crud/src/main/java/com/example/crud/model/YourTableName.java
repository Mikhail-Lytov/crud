package com.example.crud.model;

import com.example.crud.configuration.crud.string.entity.AbstractEntityString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "your_table_name")
public class YourTableName extends AbstractEntityString {

    @Column(name = "column1", length = Integer.MAX_VALUE)
    private String column1;

    @Column(name = "column2", length = Integer.MAX_VALUE)
    private String column2;

}