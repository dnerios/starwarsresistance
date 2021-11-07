package com.letscode.starwarresistence.dto.rebelde;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.letscode.starwarresistence.dto.universo.LocalizacaoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RebeldeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//@NotBlank(message = "O nome do rebelde é essencial, soldado!")
	private String nome;
	
	//@NotBlank(message = "Parece que alguém não quer revelar a idade...")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataNascimento;
	
	//@NotBlank(message = "Não esconda seu gênero, todos são bem-vindos!")
	//@Size(max = 1, min = 1, message = "Informe apenas um caracter para o gênero.")
	private Character genero;

	private LocalizacaoDTO localizacao;

	private InventarioDTO inventario;

}
