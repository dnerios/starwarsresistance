package com.letscode.starwarresistence.dto.rebelde;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letscode.starwarresistence.dto.recursos.ItemPostDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private Integer codigo;
	
	private List<ItemPostDTO> itens;
}
