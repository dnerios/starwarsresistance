package com.letscode.starwarresistence.domain.universo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "TB_GALAXIAS")
@Entity
public class Galaxias {

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "COD_GALX")
	private Integer codigo;

	@Column(name = "NOM_GALX", nullable = false)
	@NotNull(message = "De qual galáxia você veio?")
	private String nome;

	@Column(name = "DAT_HORA_CRIACAO")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date dataHoraCriacao;
	
	
	//RELACIONAMENTOS
	
	@JsonIgnore
	@OneToMany(mappedBy="galaxia", fetch = FetchType.LAZY)
	private List<MapaLocalizacao> rebeldes;
}
