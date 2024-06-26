package com.example.crud.configuration.crud.specification.enums;

import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Define enum of field type which is can be used to parse into data type.
 *
 */
@Slf4j
public enum FieldType {

    BOOLEAN {
        public Object parse(String value) {
            return Boolean.valueOf(value);
        }
    },

    TIMESTAMP {
        public Object parse(String value) {
            Object date = null;
            try {
                date = Timestamp.valueOf(value);
            } catch (Exception e) {
                log.info("Failed parse field type DATE {}", e.getMessage());
            }

            return date;
        }
    },
    DATE {
        public Object parse(String value) {
            Object date = null;
            try {
                date = Date.valueOf(value);
            } catch (Exception e) {
                log.info("Failed parse field type DATE {}", e.getMessage());
            }

            return date;
        }
    },
    DOUBLE {
        public Object parse(String value) {
            return Double.valueOf(value);
        }
    },

    INTEGER {
        public Object parse(String value) {
            return Integer.valueOf(value);
        }
    },

    LONG {
        public Object parse(String value) {
            return Long.valueOf(value);
        }
    },

    STRING {
        public Object parse(String value) {
            return value;
        }
    };

    public abstract Object parse(String value);

}