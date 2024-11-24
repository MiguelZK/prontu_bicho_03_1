package br.edu.ifrs.miguelzk.infrastructure.config;

import jakarta.enterprise.inject.Default;
import jakarta.inject.Named;
import org.modelmapper.ModelMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ModelMapperConfig {

    private final ModelMapperFactory factory;

    public ModelMapperConfig() {
        this.factory = new ModelMapperFactory();
    }

    @Default
    @Produces
    @ApplicationScoped
    public ModelMapper modelMapper() {
        return factory.customMapper();
    }

/*    @Produces
    @ApplicationScoped
    @Named("customUsuarioMapper")
    public ModelMapper usuarioMapper() {
        return factory.createUsuarioMapper(); // Bean para casos específicos de Usuario
    }*/
}