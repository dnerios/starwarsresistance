package com.letscode.starwarresistence.service.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.letscode.starwarresistence.dto.util.BodyDefaultResponseDTO;

public interface iHttpResponse {
	
	ResponseEntity<BodyDefaultResponseDTO> montarResposta(HttpStatus status, String error, String message, Object body);

}
