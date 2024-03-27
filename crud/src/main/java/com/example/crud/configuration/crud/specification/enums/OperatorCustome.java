package com.example.crud.configuration.crud.specification.enums;

import com.example.crud.configuration.crud.specification.request.FilterRequestByColumn;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public enum OperatorCustome {

    /**
     * SELECT * FROM table
     * where (table.column1 in (values) OR table.column2 in (values) ...)
     * and (table1.column1 is not null OR table.column2 is not null ...)
     */
    IN {
        public Predicate build(Root<?> root, CriteriaBuilder cb, FilterRequestByColumn request, Predicate predicate, List<Path<?>> paths) {
            List<Object> values = request.getValues();
            List<Predicate> valueMatches = new ArrayList<>();
            List<Predicate> predicateNotNullList = new ArrayList<>();

            for (Path<?> path : paths) {
                CriteriaBuilder.In<Object> inClause = cb.in(path.as(values.get(0).getClass()));
                for (Object value : values){
                    if(request.getFieldType() != null){
                        inClause.value(request.getFieldType().parse(value.toString()));
                    }else inClause.value(value.toString());
                }
               // Predicate pathEqualsValue = cb.equal(path, targetValue); // Проверяем, равно ли значение в столбце вашему искомому значению
                valueMatches.add(inClause);
            }
            // Predicate newPredicate = cb.or(valueMatches.toArray(new Predicate[0]));
            Predicate predicateIn = cb.or(valueMatches.toArray(new Predicate[0])); // Используем оператор OR для объединения предикатов

            for (Path<?> path : paths) {
                Predicate pathNotNull = cb.isNotNull(path); // Проверяем, равно ли значение в столбце вашему искомому значению
                predicateNotNullList.add(pathNotNull);
            }

            Predicate predicateNotNull = cb.or(predicateNotNullList.toArray(new Predicate[0]));

            return cb.and(predicateNotNull,cb.and(predicateIn, predicate));
        }

    };
    public abstract Predicate build(Root<?> root, CriteriaBuilder cb, FilterRequestByColumn request, Predicate predicate, List<Path<?>> paths);
}
