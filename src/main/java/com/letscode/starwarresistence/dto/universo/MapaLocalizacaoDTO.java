package com.letscode.starwarresistence.dto.universo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MapaLocalizacaoDTO implements Serializable{

	@NotBlank(message = "Procurando um fantasma? Informe o código do rebelde!")
	private Integer codigoRebelde;

	private LocalizacaoDTO coordenadas;
}
