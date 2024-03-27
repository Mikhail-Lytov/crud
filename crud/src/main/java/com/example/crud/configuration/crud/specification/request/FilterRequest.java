package com.example.crud.configuration.crud.specification.request;

import com.example.crud.configuration.crud.specification.enums.FieldType;
import com.example.crud.configuration.crud.specification.enums.Operator;
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

/**
 * A data contract for filter request there should be a key, operator, value and fieldType.
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilterRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6293344849078612450L;

    private String key;

    private Operator operator;

    private FieldType fieldType;

    private transient Object value;

    private transient Object valueTo;

    private transient List<Object> values;

    @Override
    public String toString() {
        return "{ key: %s, operator: %s, fieldType: %s, value: %s, valueTo: %s, values: %s, }"
                .formatted(key, operator, fieldType, value, valueTo, values);
    }

}