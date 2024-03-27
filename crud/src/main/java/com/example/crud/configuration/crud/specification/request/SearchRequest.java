package com.example.crud.configuration.crud.specification.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This is the main request class used in REST API.
 * The class represents a search request with filters, sorts, page, and size parameters.
 * It includes annotations for serialization, deserialization, and naming strategies.
 * The class also uses SLF4J for logging.
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Slf4j
public class SearchRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 8514625832019794838L;

    protected List<FilterRequest> filters;

    @JsonProperty(value = "filtersByColumn")
    protected List<FilterRequestByColumn> filtersByColumn;

    protected List<SortRequest> sorts;

    protected Integer page;

    protected Integer size;

    public List<FilterRequest> getFilters() {
        if (Objects.isNull(this.filters)) this.filters = new ArrayList<>();
        return this.filters;
    }

    public List<SortRequest> getSorts() {
        if (Objects.isNull(this.sorts)) this.sorts = new ArrayList<>();
        return this.sorts;
    }

    public List<FilterRequestByColumn> getFiltersByColumn(){
        if(Objects.isNull(this.filtersByColumn)) this.filtersByColumn = new ArrayList<>();
        return this.filtersByColumn;
    }
}