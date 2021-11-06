package com.letscode.starwarresistence.dto.rebelde;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.letscode.starwarresistence.dto.universo.LocalizacaoDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RebeldeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "O nome do rebelde é essencial, soldado!")
	private String nome;
	
	@NotBlank(message = "Parece que alguém não quer revelar a idade...")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataNascimento;
	
	@NotBlank(message = "Não esconda seu gênero, todos são bem-vindos!")
	private Character genero;

	private LocalizacaoDTO localizacao;

	private InventarioDTO inventario;

}
