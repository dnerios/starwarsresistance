package com.letscode.starwarresistence.domain.recursos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "TB_ITENS")
@Entity
public class Item {

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "COD_ITEM")
	private Integer codigo;

	@Column(name = "NOM_ITEM", nullable = false)
	@NotNull(message = "Como vai encontrar um item sem nome, soldado?!")
	private String nome;

	@Column(name = "PTS_ITEM", nullable = false)
	@NotNull(message = "Um item sem pontos, é um item inútil...")
	private Integer pontos;

	@Column(name = "DAT_CRIA")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date dataCriacao;

}
