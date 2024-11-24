package br.edu.ifrs.miguelzk.application.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

//import org.hibernate.type.descriptor.java.DateJavaType;

import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.entities.MedVet;
import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoResponseDTO {

  private Long id;
  private Date dataAtendimento;
  private String relatoTutor;
  private String exameClinico;
  private String avaliacaoExames;
  private String diagnPresuntivo;
  private String diagnConclusivo;
  private String procedimentoRealizado;
  private String tratamentoInstituido;
  private String observarProxConsulta;
//  private List<DateJavaType> validacaoPelosTutores;

  private AnimalResponseDTO animal;
  private Set<UsuarioResponseDTO> usuariosDTO; // nomes de usuários
  private Set<MedVetResponseDTO> medVetsDTO; // nomes de medVets

}
