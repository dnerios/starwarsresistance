package com.letscode.starwarresistence.dto.rebelde;

import java.io.Serializable;
import java.util.List;

import com.letscode.starwarresistence.dto.recursos.ItemDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventarioDTO implements Serializable {

	private List<ItemDTO> itens;
}
