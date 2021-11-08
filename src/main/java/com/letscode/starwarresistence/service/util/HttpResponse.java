package com.letscode.starwarresistence.service.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.letscode.starwarresistence.dto.util.BodyDefaultResponseDTO;

@Service
public class HttpResponse implements iHttpResponse {

	@Override
	public ResponseEntity<BodyDefaultResponseDTO> montarResposta(HttpStatus status, String error, String message,
			Object body) {
		
		return ResponseEntity.status(status).body(BodyDefaultResponseDTO.builder()
				.status(status.value())
				.error(error)
				.message(message)
				.body(body)
				.build());
	}

}
