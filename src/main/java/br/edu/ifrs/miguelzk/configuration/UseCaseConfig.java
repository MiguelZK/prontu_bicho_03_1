package br.edu.ifrs.miguelzk.configuration;


import br.edu.ifrs.miguelzk.application.usecase.*;
import br.edu.ifrs.miguelzk.application.usecase.impl.*;
import org.modelmapper.ModelMapper;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;
import br.edu.ifrs.miguelzk.domain.repository.AtendimentoRepository;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class UseCaseConfig {

    @Produces
    public CreateAnimalUseCase createAnimalUseCase(AnimalRepository animalRepository, ModelMapper modelMapper) {
        return new CreateAnimalUseCaseImpl(animalRepository, modelMapper);
    }

    @Produces
    public CreateUsuarioUseCase createUsuarioUseCase(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        return new CreateUsuarioUseCaseImpl(usuarioRepository, modelMapper);
    }

    @Produces
    public CreateAtendimentoUseCase createAtendimentoUseCase(AtendimentoRepository atendimentoRepository, UsuarioRepository usuarioRepository, AnimalRepository animalRepository, ModelMapper modelMapper) {
        return new CreateAtendimentoUseCaseImpl(atendimentoRepository, usuarioRepository, animalRepository, modelMapper);
    }

    @Produces
    public FindAnimalUseCase findAnimalUseCase(AnimalRepository animalRepository, ModelMapper modelMapper) {
        return new FindAnimalUseCaseImpl(animalRepository, modelMapper);
    }

    @Produces
    public DeleteAnimalUseCase deleteAnimalUseCase(AnimalRepository animalRepository) {
        return new DeleteAnimalUseCaseImpl(animalRepository);
    }

    @Produces
    public FindUsuarioUseCase findUsuarioUseCase(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        return new FindUsuarioUseCaseImpl(usuarioRepository, modelMapper);
    }

    @Produces
    public DeleteUsuarioUseCase deleteUsuarioUseCase(UsuarioRepository usuarioRepository) {
        return new DeleteUsuarioUseCaseImpl(usuarioRepository);
    }

    @Produces
    public FindAtendimentoUseCase findAtendimentoUseCase(AtendimentoRepository atendimentoRepository, ModelMapper modelMapper) {
        return new FindAtendimentoUseCaseImpl(atendimentoRepository, modelMapper);
    }

    @Produces
    public DeleteAtendimentoUseCase deleteAtendimentoUseCase(AtendimentoRepository atendimentoRepository) {
        return new DeleteAtendimentoUseCaseImpl(atendimentoRepository);
    }

    @Produces
    public UpdateAnimalUseCase UpdateAnimalUseCase(AnimalRepository animalRepository, ModelMapper modelMapper) {
        return new UpdateAnimalUseCaseImpl(animalRepository, modelMapper);
    }

    @Produces
    public UpdateUsuarioUseCase UpdateUsuarioUseCase(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        return new UpdateUsuarioUseCaseImpl(usuarioRepository, modelMapper);
    }

}