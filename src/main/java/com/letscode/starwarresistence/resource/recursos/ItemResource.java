package com.letscode.starwarresistence.resource.recursos;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letscode.starwarresistence.dto.recursos.NegociarItensDTO;
import com.letscode.starwarresistence.dto.util.BodyDefaultResponseDTO;
import com.letscode.starwarresistence.service.recursos.iItemService;
import com.letscode.starwarresistence.service.util.iHttpResponse;

@RestController
@RequestMapping(value = "v1/recursos/itens")
public class ItemResource {
	
	@Autowired
	private iItemService service;
	
	@Autowired
	private iHttpResponse httpService;
	
	@RequestMapping(name = "negociar", method = RequestMethod.POST)
	public ResponseEntity<BodyDefaultResponseDTO> adicionarRebelde(@Valid NegociarItensDTO negociacao) {
		try {
			
			service.negociar(negociacao.getNegociantePrimario(), negociacao.getNegocianteSecundario());
			
			return httpService.montarResposta(HttpStatus.OK, null, null, null);
			
		} catch (Exception e) {
			return httpService.montarResposta(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null, null);
		}
	}

}
