package br.edu.ifrs.miguelzk.infrastructure.config;

import br.edu.ifrs.miguelzk.application.dto.UsuarioResponseDTO;
import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import jakarta.inject.Named;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.hibernate.collection.spi.PersistentSet;
import java.util.HashSet;
import java.util.Set;

public class ModelMapperFactory extends ModelMapper {

    public ModelMapper customMapper() {
        ModelMapper mapper = new ModelMapper();

        // Conversor para lidar com PersistentSet
        Converter<PersistentSet, Set<?>> persistentSetConverter =
                new Converter<PersistentSet, Set<?>>() {
                    @Override
                    public Set<?> convert(MappingContext<PersistentSet, Set<?>> context) {
                        return new HashSet<>(context.getSource());
                    }
                };

        mapper.addConverter(persistentSetConverter);

        return mapper;
    }

/*    @Named("createUsuarioMapper")
    public ModelMapper createUsuarioMapper() {
        ModelMapper mapper = new ModelMapper();

        // Configuração específica de mapeamento para Usuario -> UsuarioResponseDTO
        TypeMap<Usuario, UsuarioResponseDTO> typeMap = mapper.createTypeMap(Usuario.class, UsuarioResponseDTO.class);

        // Evita mapear o atributo `vinculos` diretamente
        typeMap.addMappings(new PropertyMap<Usuario, UsuarioResponseDTO>() {
            @Override
            protected void configure() {
                skip(source.getVinculos());
            }
        });
        return mapper;
    }*/
}

