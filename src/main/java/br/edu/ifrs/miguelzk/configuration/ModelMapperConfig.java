package br.edu.ifrs.miguelzk.configuration;

import org.modelmapper.ModelMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ModelMapperConfig {

  @Produces
  @ApplicationScoped
//  public ModelMapper modelMapper() {
//    return new ModelMapper();
//  }
  public ModelMapper modelMapper() {
    return new CustomModelMapper();
  }

}