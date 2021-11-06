package com.letscode.starwarresistence.dto.util;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BodyDefaultResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer status;
	
	private String error;
	
	private String message;
	
	private Object body;
}
