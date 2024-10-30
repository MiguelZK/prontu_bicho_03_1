package br.edu.ifrs.miguelzk.infrastructure.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.hibernate.collection.spi.PersistentSet;
import java.util.HashSet;
import java.util.Set;

public class CustomModelMapper extends ModelMapper {

    public CustomModelMapper() {
        // Custom converter to handle PersistentSet
        Converter<PersistentSet, Set<?>> persistentSetConverter =
                new Converter<PersistentSet, Set<?>>() {
                    @Override
                    public Set<?> convert(MappingContext<PersistentSet, Set<?>> context) {
                        return new HashSet<>(context.getSource()); // Convert PersistentSet to HashSet
                    }
                };

        // Register the custom converter
        this.addConverter(persistentSetConverter);
    }
}

