package com.example.crud.configuration.crud.specification.request;

import com.example.crud.configuration.crud.specification.enums.SortDirection;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SortRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 3194362295851723069L;

    private String key;

    private SortDirection direction;

    /**
     * Converts a Spring Sort object to a list of SortRequest objects.
     *
     * @param s The Spring Sort object to convert.
     * @return A list of SortRequest objects representing the sorting request.
     */
    @SuppressWarnings("unused")
    public static List<SortRequest> getSortRequests(Sort s) {
        return s.stream().map(x -> {
            SortDirection curDirection = null;
            if (x.getDirection().isAscending()) {
                curDirection = SortDirection.ASC;
            } else if (x.getDirection().isDescending()) {
                curDirection = SortDirection.DESC;
            }
            return SortRequest.builder().key(x.getProperty()).direction(curDirection).build();
        }).toList();
    }

    @Override
    public String toString() {
        return "{ key: %s, direction: %s }".formatted(key, direction);
    }
}