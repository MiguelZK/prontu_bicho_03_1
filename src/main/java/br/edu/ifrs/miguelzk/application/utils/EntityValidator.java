package br.edu.ifrs.miguelzk.application.utils;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@ApplicationScoped
public class EntityValidator {

    public static <T> void validateUniqueness(Map<String, Function<String, Optional<T>>> validations) {
        validations.forEach((value, findFunction) -> {
            findFunction.apply(value)
                    .ifPresent(entity -> {
                        throw new IllegalArgumentException("Valor já existe: " + value);
                    });
        });
    }
}
