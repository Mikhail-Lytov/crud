package com.example.crud.configuration.crud.specification.enums;

import com.example.crud.configuration.crud.specification.request.SortRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This is used when need to sort result query. It can be ascending or descending direction.
 *
 */
@Slf4j
public enum SortDirection {
    /**
     * ASC	SELECT * FROM table ORDER BY field ASC
     */
    ASC {
        public List<Order> build(Root<?> root, CriteriaBuilder cb, SortRequest request, Path<?> path) {
            return Collections.singletonList(cb.asc(path));
        }
    },
   /* /**
     * DESC	SELECT * FROM table ORDER BY field DESC
     */
    /*DESC {
        public Order build(Root<?> root, CriteriaBuilder cb, SortRequest request, Path<?> path) {
            return cb.desc(path);
        }
    },*/

    /**
     * DESC	SELECT * FROM table ORDER BY CASE WHEN field IS NULL THEN 1 ELSE 0 END, field DESC
     */
    DESC {
        public List<Order> build(Root<?> root, CriteriaBuilder cb, SortRequest request, Path<?> path) {
            List<Order> orders = new ArrayList<>();
            Order orderNullDesc = cb.desc(cb.<Integer>selectCase()
                    .when(cb.isNull(path), 0)
                    .otherwise(1));
            orders.add(orderNullDesc);
            orders.add(cb.desc(path));
            return orders;
        }
    };

    public abstract List<Order> build(Root<?> root, CriteriaBuilder cb, SortRequest request, Path<?> path);

}
