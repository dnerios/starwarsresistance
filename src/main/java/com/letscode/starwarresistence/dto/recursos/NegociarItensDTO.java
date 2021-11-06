package com.letscode.starwarresistence.dto.recursos;

import java.io.Serializable;

import com.letscode.starwarresistence.dto.rebelde.RebeldeNegocianteDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NegociarItensDTO implements Serializable{

	private RebeldeNegocianteDTO negociantePrimario;
	private RebeldeNegocianteDTO negocianteSecundario;
}
