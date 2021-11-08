package com.letscode.starwarresistence.resource.rebelde;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letscode.starwarresistence.dto.rebelde.RebeldeDTO;
import com.letscode.starwarresistence.dto.universo.MapaLocalizacaoDTO;
import com.letscode.starwarresistence.dto.util.BodyDefaultResponseDTO;
import com.letscode.starwarresistence.service.rebelde.iRebeldeService;
import com.letscode.starwarresistence.service.util.iHttpResponse;
import com.letscode.starwarresistence.shared.exceptions.ObjectNotFoundException;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "v1/rebeldes")
public class RebeldeResource {

	@Autowired
	private iRebeldeService service;

	@Autowired
	private iHttpResponse httpService;
	
	@ApiOperation(value = "Recrutar novos rebeldes para nossa causa")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<BodyDefaultResponseDTO> adicionarRebelde(@Valid @RequestBody RebeldeDTO rebelde) {
		try {
			
			service.adicionarRebelde(rebelde);
			
			return httpService.montarResposta(HttpStatus.CREATED, null, null, null);
			
		} catch (ObjectNotFoundException e) {
			return httpService.montarResposta(HttpStatus.NOT_FOUND, e.getMessage(), null, null);
		} catch (Exception e) {
			return httpService.montarResposta(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null, null);
		}
	}
	
	@ApiOperation(value = "Atualizar a localização do rebelde")
	@RequestMapping(value = "localizacao", method = RequestMethod.PUT)
	public ResponseEntity<BodyDefaultResponseDTO> atualizarLocalizacao(@Valid @RequestBody MapaLocalizacaoDTO localizacaoRebelde) {
		try {
			
			service.atualizarLocalizacao(localizacaoRebelde);
			
			return httpService.montarResposta(HttpStatus.OK, null, null, null);
			
		} catch (ObjectNotFoundException e) {
			return httpService.montarResposta(HttpStatus.NOT_FOUND, e.getMessage(), null, null);
		} catch (Exception e) {
			return httpService.montarResposta(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null, null);
		}
	}
	
	@ApiOperation(value = "Encontrou um traidor? Reporte por aqui!")
	@RequestMapping(value = "traidor", method = RequestMethod.POST)
	public ResponseEntity<BodyDefaultResponseDTO> reportarTraidor(@RequestParam Integer codigo) {
		try {
			
			service.reportarTraidor(codigo);
			
			return httpService.montarResposta(HttpStatus.OK, null, null, null);
			
		} catch (ObjectNotFoundException e) {
			return httpService.montarResposta(HttpStatus.NOT_FOUND, e.getMessage(), null, null);
		} catch (Exception e) {
			return httpService.montarResposta(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null, null);
		}
	}
}
