package com.letscode.starwarresistence.domain.rebelde;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COD_INVENT", nullable=false)
	private InventarioAgrupamento agrupamento;
	
	
	//RELACIONAMENTOS
	
	@JsonIgnore
	@OneToOne(mappedBy="inventario", fetch = FetchType.LAZY)
	private Rebelde rebelde;
}
