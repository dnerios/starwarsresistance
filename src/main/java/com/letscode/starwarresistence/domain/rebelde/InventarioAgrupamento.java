package com.letscode.starwarresistence.domain.rebelde;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.letscode.starwarresistence.domain.recursos.Item;

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
@Table(name = "TB_AGRUP_INVENTARIO")
@Entity
public class InventarioAgrupamento {

	private static final long serialVersionUID = 1L;
	
	///Criando um novo índice para permitir que o rebelde tenha itens repetidos
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "COD_AGRUP_INVNT")
	private Integer codigo;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="COD_INVENT", nullable=true)
	private Inventario inventario;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COD_ITEM", nullable=false)
	private Item item;
	
	@Column(name = "DAT_HORA_CRIACAO")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date dataHoraCriacao;
	
}
