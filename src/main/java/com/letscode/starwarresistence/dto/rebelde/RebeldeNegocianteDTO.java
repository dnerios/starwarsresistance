package com.letscode.starwarresistence.dto.rebelde;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.letscode.starwarresistence.dto.recursos.ItemDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RebeldeNegocianteDTO implements Serializable{

	@NotBlank(message = "Procurando um fantasma? Informe o código do rebelde!")
	Integer codigoRebelde;
	
	@NotBlank(message = "Não me parece um bom negócio trocar uma lista de itens vazia...")
	List<ItemDTO> itens;
}
