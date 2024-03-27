package com.example.crud.configuration.crud.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;

@Data
@NoArgsConstructor
@Slf4j
public class Reflection {
    public static Class<?> getClassGeneric(Object obj) {
        ParameterizedType genericSuperclass = (ParameterizedType) obj.getClass().getGenericSuperclass();
        return (Class<?>) genericSuperclass.getActualTypeArguments()[0];
    }
}
