package com.letscode.starwarresistence.domain.rebelde;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "TB_INVENTARIO")
@Entity
public class Inventario {

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "COD_INVENT")
	private Integer codigo;
	
	
	//RELACIONAMENTOS
	@JsonIgnore
	@OneToMany(mappedBy = "inventario", fetch = FetchType.EAGER)
	private List<InventarioAgrupamento> listaItens;
	
	@JsonIgnore
	@OneToOne(mappedBy="inventario", fetch = FetchType.LAZY)
	private Rebelde rebelde;
}
