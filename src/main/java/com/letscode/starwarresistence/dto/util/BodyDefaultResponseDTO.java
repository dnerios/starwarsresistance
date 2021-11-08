package com.letscode.starwarresistence.dto.util;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BodyDefaultResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "HTTP Status da operação")
	private Integer status;
	
	@ApiModelProperty(notes = "Mensagem de retorno do erro")
	private String error;
	
	@ApiModelProperty(notes = "Mensagem informativa sobre uma ação")
	private String message;
	
	@ApiModelProperty(notes = "Corpo onde estará a resposta da requisição, quando se aplicar")
	private Object body;
}
