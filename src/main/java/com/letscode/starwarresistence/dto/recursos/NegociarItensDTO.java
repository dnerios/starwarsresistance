package com.letscode.starwarresistence.dto.recursos;

import java.io.Serializable;

import com.letscode.starwarresistence.dto.rebelde.RebeldeNegocianteDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NegociarItensDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private RebeldeNegocianteDTO negociantePrimario;
	private RebeldeNegocianteDTO negocianteSecundario;
}
