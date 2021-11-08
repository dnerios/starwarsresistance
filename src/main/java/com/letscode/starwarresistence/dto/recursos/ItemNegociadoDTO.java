package com.letscode.starwarresistence.dto.recursos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ItemNegociadoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer codigoAgrupamento;
	
	@JsonIgnore
	private Integer codigoItem;
}
