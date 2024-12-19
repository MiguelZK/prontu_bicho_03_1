package br.edu.ifrs.miguelzk.infrastructure.persistence;

import br.edu.ifrs.miguelzk.application.dto.*;
import br.edu.ifrs.miguelzk.application.service.*;
import br.edu.ifrs.miguelzk.domain.enums.PorteCachorro;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.jboss.logging.Logger;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import static br.edu.ifrs.miguelzk.domain.enums.TipoImunizante.*;

@ApplicationScoped
public class DataLoaderVinculos {

    private static final Logger LOG = Logger.getLogger(DataLoaderVinculos.class);
    private final UsuarioService usuarioService;
    private final AnimalService animalService;
    private final VinculoService vinculoService;
    private final UsuarioRepository usuarioRepository;
    private final AnimalRepository animalRepository;
    private final AtendimentoService atendimentoService;
    private final ImunizanteService imunizanteService;
    private MedVetService medVetService;

    @jakarta.inject.Inject
    public DataLoaderVinculos(UsuarioService usuarioService, AnimalService animalService
            , VinculoService vinculoService, UsuarioRepository usuarioRepository, AnimalRepository animalRepository
            , MedVetService medVetService, AtendimentoService atendimentoService, ImunizanteService imunizanteService) {
        this.usuarioService = usuarioService;
        this.animalService = animalService;
        this.vinculoService = vinculoService;
        this.usuarioRepository = usuarioRepository;
        this.animalRepository = animalRepository;
        this.medVetService = medVetService;
        this.atendimentoService = atendimentoService;
        this.imunizanteService = imunizanteService;
    }

    //    @PostConstruct
    public void onStart(@Observes StartupEvent ev) {
//    public void loadData() {
        // VERIFICAR SE OS DADOS JÁ FORAM INSERIDOS PARA EVITAR DUPLICIDADE
        if (usuarioRepository.contaUsuarios() == 0 && animalRepository.contaAnimais() == 0) {
            LOG.info("Carregando os dados iniciais no Dataloader...");

            // INSERIR USUÁRIOS
            UsuarioRequestDTO usuario1 = new UsuarioRequestDTO();
            usuario1.setRole("ADM, TUTOR");
            usuario1.setPassword("senha123");
            usuario1.setNomeCompleto("Miguel Zanona Krasner");
            usuario1.setUserName("miguel");

            UsuarioRequestDTO usuario2 = new UsuarioRequestDTO();
            usuario2.setRole("TUTOR");
            usuario2.setPassword("senha456");
            usuario2.setNomeCompleto("Laura I Marcaccio Arce");
            usuario2.setUserName("laura");

            UsuarioRequestDTO usuario3 = new UsuarioRequestDTO();
            usuario3.setRole("TUTOR");
            usuario3.setPassword("senha789");
            usuario3.setNomeCompleto("Caroline Teixeira Reinoso");
            usuario3.setUserName("carol");

            usuarioService.create(usuario1);
            usuarioService.create(usuario2);
            usuarioService.create(usuario3);

            // INSERIR ANIMAIS
            AnimalRequestDTO animal1 = new AnimalRequestDTO();
            animal1.setNomeAnimal("Shenka");
            animal1.setPorteCachorro(PorteCachorro.PEQUENO);

            AnimalRequestDTO animal2 = new AnimalRequestDTO();
            animal2.setNomeAnimal("Agnes");
            animal2.setPorteCachorro(PorteCachorro.PEQUENO);

            AnimalRequestDTO animal3 = new AnimalRequestDTO();
            animal3.setNomeAnimal("Bidu");
            animal3.setPorteCachorro(PorteCachorro.PEQUENO);

            AnimalRequestDTO animal4 = new AnimalRequestDTO();
            animal4.setNomeAnimal("Floquinho");
            animal4.setPorteCachorro(PorteCachorro.GIGANTE);

            animalService.create(animal1);
            animalService.create(animal2);
            animalService.create(animal3);
            animalService.create(animal4);

            // INSERIR MEDVETS
            MedVetRequestDTO medVet1 = new MedVetRequestDTO();
            medVet1.setRole("MEDVET, TUTOR");
            medVet1.setPassword("cantinho");
            medVet1.setNomeCompleto("Leticia Britto");
            medVet1.setUserName("leticia01");
            medVet1.setCrmv(1234L);
            medVet1.setEspecialidade("Geral");

            MedVetRequestDTO medVet2 = new MedVetRequestDTO();
            medVet2.setRole("MEDVET, TUTOR");
            medVet2.setPassword("pancrezyme");
            medVet2.setNomeCompleto("Guilherme Nutrólogo");
            medVet2.setUserName("guilherme01");
            medVet2.setCrmv(4567L);
            medVet2.setEspecialidade("Nutrólogo");

            MedVetRequestDTO medVet3 = new MedVetRequestDTO();
            medVet3.setRole("MEDVET, TUTOR");
            medVet3.setPassword("ecografia");
            medVet3.setNomeCompleto("Adriane Ilha");
            medVet3.setUserName("adrianeilha01");
            medVet3.setCrmv(7890L);
            medVet3.setEspecialidade("Especialista em imagem");

            medVetService.create(medVet1);
            medVetService.create(medVet2);
            medVetService.create(medVet3);

            // RELACIONAR USUÁRIOS E ANIMAIS PROGRAMATICAMENTE
            VinculoRequestDTO vinculo1 = new VinculoRequestDTO();
            vinculo1.setNomeAnimal("Shenka");
            vinculo1.setIdAnimal(1L);
            vinculo1.setIdUsuario(1L);

            VinculoRequestDTO vinculo2 = new VinculoRequestDTO();
            vinculo2.setNomeAnimal("Shenka");
            vinculo2.setIdAnimal(1L);
            vinculo2.setIdUsuario(2L);

            VinculoRequestDTO vinculo3 = new VinculoRequestDTO();
            vinculo3.setNomeAnimal("Agnes");
            vinculo3.setIdAnimal(2L);
            vinculo3.setIdUsuario(3L);

            VinculoRequestDTO vinculo4 = new VinculoRequestDTO();
            vinculo4.setNomeAnimal("Bidu");
            vinculo4.setIdAnimal(3L);
            vinculo4.setIdUsuario(3L);

            VinculoRequestDTO vinculo5 = new VinculoRequestDTO();
            vinculo5.setNomeAnimal("Floquinho");
            vinculo5.setIdAnimal(4L);
            vinculo5.setIdUsuario(4L);

            vinculoService.create(vinculo1);
            vinculoService.create(vinculo2);
            vinculoService.create(vinculo3);
            vinculoService.create(vinculo4);

            // INSERIR ATENDIMENTOS
            AtendimentoRequestDTO atendimento1 = new AtendimentoRequestDTO();
            atendimento1.setDataAtendimento(new Date((2024-1900), 11,  12));
            atendimento1.setRelatoTutor("Ele não come há uns dias");
            atendimento1.setExameClinico("Relato do exame clínico 1");
            atendimento1.setAvaliacaoExames("Avaliação dos exames 1");
            atendimento1.setDiagnPresuntivo("Diagnóstico presuntivo 1");
            atendimento1.setDiagnConclusivo("Diagnóstico conclusivo 1");
            atendimento1.setProcedimentoRealizado("Procedimento realizado 1");
            atendimento1.setTratamentoInstituido("Tratamento instituído 1");
            atendimento1.setObservarProxConsulta("Observar que ele pareceu desanimado nessa consulta");
            atendimento1.setIdAnimal(3L); // Bidu
            atendimento1.setIdUsuarios(new HashSet<Long>(Arrays.asList(1L, 3L))); // Miguel e Carol
            atendimento1.setCrmvMedVets(new HashSet<Long>(Arrays.asList(1234L))); // Letícia

            AtendimentoRequestDTO atendimento2 = new AtendimentoRequestDTO();
            atendimento2.setDataAtendimento(new Date((2024-1900), 11,  15));
            atendimento2.setRelatoTutor("Se coça muito");
            atendimento2.setExameClinico("Exame clínico 2");
            atendimento2.setAvaliacaoExames("Avaliação exames 2");
            atendimento2.setDiagnPresuntivo("Diagnóstico presuntivo 2");
            atendimento2.setDiagnConclusivo("Diagnóstico conclusivo 2");
            atendimento2.setProcedimentoRealizado("Procedimento realizado 2");
            atendimento2.setTratamentoInstituido("Tratamento instituído 2");
            atendimento2.setObservarProxConsulta("Observar manchinhas na barriga");
            atendimento2.setIdAnimal(1L); // Shenka
            atendimento2.setIdUsuarios(new HashSet<Long>(Arrays.asList(1L, 2L))); // Miguel e Laura
            atendimento2.setCrmvMedVets(new HashSet<Long>(Arrays.asList(1234L, 4567L))); // Letícia e Guilherme

            AtendimentoRequestDTO atendimento3 = new AtendimentoRequestDTO();
            atendimento3.setDataAtendimento(new Date((2024-1900), 11,  20));
            atendimento3.setRelatoTutor("Ele não come há uns dias");
            atendimento3.setExameClinico("Relato do exame clínico aqui");
            atendimento3.setAvaliacaoExames("Avaliação dos exames aqui");
            atendimento3.setDiagnPresuntivo("Diagnóstico presuntivo aqui ");
            atendimento3.setDiagnConclusivo("Diagnóstico conclusivo aqui ");
            atendimento3.setProcedimentoRealizado("Procedimento realizado aqui");
            atendimento3.setTratamentoInstituido("Tratamento instituído aqui");
            atendimento3.setObservarProxConsulta("Observar que ele pareceu desanimado nessa consulta");
            atendimento3.setIdAnimal(2L); // Agnes
            atendimento3.setIdUsuarios(new HashSet<Long>(Arrays.asList(3L))); // Carol
            atendimento3.setCrmvMedVets(new HashSet<Long>(Arrays.asList(7890L))); // Adriane

            atendimentoService.createAtendimento(atendimento1);
            atendimentoService.createAtendimento(atendimento2);
            atendimentoService.createAtendimento(atendimento3);

            // INSERIR IMUNIZANTES
            ImunizanteRequestDTO imunizante1 = new ImunizanteRequestDTO();
            imunizante1.setNome("Antirrábica 1 Shenka");
            imunizante1.setTipoImunizante(VACINA);
            imunizante1.setMarca("PetVacinas");
            imunizante1.setLote("123asd");
            imunizante1.setImportada(true);
            imunizante1.setDataFabricacao(new Date((2024-1900), 03, 10));
            imunizante1.setDataValidade(new Date((2025-1900), 12, 10));
            imunizante1.setDataAplicacao(new Date((2024-1900), 11, 15));
            imunizante1.setIdAnimal(1L); // Shenka
            imunizante1.setIdAtendimento(2L);
            
            ImunizanteRequestDTO imunizante2 = new ImunizanteRequestDTO();
            imunizante2.setNome("Antirrábica 1 Bidu");
            imunizante2.setTipoImunizante(VACINA);
            imunizante2.setMarca("PetVacinas");
            imunizante2.setLote("123asd");
            imunizante2.setImportada(true);
            imunizante2.setDataFabricacao(new Date((2024-1900), 03, 10));
            imunizante2.setDataValidade(new Date((2025-1900), 12, 10));
            imunizante2.setDataAplicacao(new Date((2024-1900), 11, 12));
            imunizante2.setIdAnimal(3L); // Bidu
            imunizante2.setIdAtendimento(1L);
            
            ImunizanteRequestDTO imunizante3 = new ImunizanteRequestDTO();
            imunizante3.setNome("Vermígugo 1 Shenka");
            imunizante3.setTipoImunizante(VERMIFUGO);
            imunizante3.setMarca("Canex Plus");
            imunizante3.setLote("456a32");
            imunizante3.setImportada(false);
            imunizante3.setDataFabricacao(new Date((2024-1900), 03, 10));
            imunizante3.setDataValidade(new Date((2025-1900), 12, 10));
            imunizante3.setDataAplicacao(new Date((2024-1900), 12, 15));
            imunizante3.setIdAnimal(1L);
            imunizante3.setIdAtendimento(null);
            
            ImunizanteRequestDTO imunizante4 = new ImunizanteRequestDTO();
            imunizante4.setNome("Antipulgas e carrapatos 1 Shenka");
            imunizante4.setTipoImunizante(ANTIPULGASECARRAPATOS);
            imunizante4.setMarca("Nexgard");
            imunizante4.setLote("654s4");
            imunizante4.setImportada(false);
            imunizante4.setDataFabricacao(new Date((2024-1900), 03, 10));
            imunizante4.setDataValidade(new Date((2025-1900), 12, 10));
            imunizante4.setDataAplicacao(new Date((2024-1900), 11, 12));
            imunizante4.setIdAnimal(1L);
            imunizante4.setIdAtendimento(2L);

            imunizanteService.create(imunizante1);
            imunizanteService.create(imunizante2);
            imunizanteService.create(imunizante3);
            imunizanteService.create(imunizante4);

//            LOG.info("Usuario 1: " + usuario1);

            LOG.info("Dados carregados com sucesso.");
        }
    }
}
