package com.letscode.starwarresistence.dto.recursos;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDTO implements Serializable{
	
	private Integer codigo;
	private String nome;
	private Integer pontos;
}
