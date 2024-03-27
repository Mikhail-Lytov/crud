package com.example.crud.configuration.crud.specification.request;

import com.example.crud.configuration.crud.specification.enums.FieldType;
import com.example.crud.configuration.crud.specification.enums.OperatorCustome;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilterRequestByColumn implements Serializable {

    @Serial
    private static final long serialVersionUID = 6293344849078612450L;

    private List<String> key;

    private OperatorCustome operator;

    private FieldType fieldType;

    private transient List<Object> values;

    @Override
    public String toString() {
        return "{ key: %s, operator: %s, fieldType: %s, values: %s }"
                .formatted(key, operator, fieldType, values);
    }
}
