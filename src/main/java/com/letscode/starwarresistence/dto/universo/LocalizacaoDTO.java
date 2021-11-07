package com.letscode.starwarresistence.dto.universo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocalizacaoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	//@NotBlank(message = "Procurando um fantasma? Informe o código do rebelde.")
	//private Integer codigoRebelde;

	@NotNull(message = "O universo é grande demais... Me da uma dica de qual galáxia estamos falando?")
	private Integer codigoGalaxia;

	@NotNull(message = "Não se esconda! Qual é a sua latitude?")
	private Double latitude;
	
	@NotNull(message = "Não se esconda! Qual é a sua longitude?")
	private Double longitude;
}
