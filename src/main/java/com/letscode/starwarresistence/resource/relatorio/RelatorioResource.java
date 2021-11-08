package com.letscode.starwarresistence.resource.relatorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letscode.starwarresistence.dto.util.BodyDefaultResponseDTO;
import com.letscode.starwarresistence.service.relatorio.iRelatorioService;
import com.letscode.starwarresistence.service.util.iHttpResponse;
import com.letscode.starwarresistence.shared.exceptions.NaoHaRebeldesException;
import com.letscode.starwarresistence.shared.exceptions.NaoHaTraidoresException;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "v1/relatorios")
public class RelatorioResource {

	@Autowired
	private iHttpResponse httpService;
	
	@Autowired
	private iRelatorioService service;
	
	@ApiOperation(value = "Consultar o percentual de traidores que estão entre nós")
	@RequestMapping(value = "traidores", method = RequestMethod.GET)
	public ResponseEntity<BodyDefaultResponseDTO> porcentagemTraidores() {
		try {
			
			return httpService.montarResposta(HttpStatus.OK, null, null, service.obterPercentualTraidores());
			
		} catch (Exception e) {
			return httpService.montarResposta(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null, null);
		}
	}
	
	@ApiOperation(value = "Consultar o percentual de rebeldes que temos lutando pela causa")
	@RequestMapping(value = "rebeldes", method = RequestMethod.GET)
	public ResponseEntity<BodyDefaultResponseDTO> porcentagemRebeldes() {
		try {
			
			return httpService.montarResposta(HttpStatus.OK, null, null, service.obterPercentualRebeldes());
			
		} catch (Exception e) {
			return httpService.montarResposta(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null, null);
		}
	}
	
	@ApiOperation(value = "Consultar a média de recursos que estão em nossas mãos")
	@RequestMapping(value = "rebeldes/media_recursos", method = RequestMethod.GET)
	public ResponseEntity<BodyDefaultResponseDTO> mediaRecursos() {
		try {
			
			return httpService.montarResposta(HttpStatus.OK, null, null, service.obterMediaRecursosRebeldes());
			
		} catch (NaoHaRebeldesException e) {
			return httpService.montarResposta(HttpStatus.OK, null, e.getMessage(), 0);
		} catch (Exception e) {
			return httpService.montarResposta(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null, null);
		}
	}
	
	@ApiOperation(value = "Consultar os pontos perdidos por traidores")
	@RequestMapping(value = "traidores/pontos_perdidos", method = RequestMethod.GET)
	public ResponseEntity<BodyDefaultResponseDTO> pontosPerdidos() {
		try {
			
			return httpService.montarResposta(HttpStatus.OK, null, null, service.obterPontosPerdidos());
			
		} catch (NaoHaTraidoresException e) {
			return httpService.montarResposta(HttpStatus.OK, null, e.getMessage(), 0);
		} catch (Exception e) {
			return httpService.montarResposta(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null, null);
		}
	}
}
