package com.letscode.starwarresistence.dto.recursos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ItemDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer codigoAgrupamento;
	private Integer codigoItem;
	private String nome;
	private Integer pontos;
}
