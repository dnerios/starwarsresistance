package com.letscode.starwarresistence.service.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.letscode.starwarresistence.domain.rebelde.Rebelde;
import com.letscode.starwarresistence.dto.rebelde.RebeldeNegocianteDTO;
import com.letscode.starwarresistence.dto.recursos.ItemDTO;
import com.letscode.starwarresistence.repository.rebelde.RebeldeRepository;
import com.letscode.starwarresistence.shared.exceptions.ObjectNotFoundException;
import com.letscode.starwarresistence.shared.exceptions.PontosInsuficientesException;
import com.letscode.starwarresistence.shared.exceptions.TraidorNegocianteException;

public class ItemService implements iItemService {

	@Autowired
	private RebeldeRepository repository;
	
	@Override
	public void negociar(RebeldeNegocianteDTO negociantePrimario, RebeldeNegocianteDTO negocianteSecundario) throws ObjectNotFoundException, TraidorNegocianteException, PontosInsuficientesException {
		Rebelde dbNegPrimario = repository.findById(negociantePrimario.getCodigoRebelde()).orElseThrow(
				() -> new ObjectNotFoundException("Rebelde primário (Cod." + negociantePrimario.getCodigoRebelde() + ") não encontrado. Confira o código de identificação."));

		Rebelde dbNegSecundario = repository.findById(negocianteSecundario.getCodigoRebelde()).orElseThrow(
				() -> new ObjectNotFoundException("Rebelde primário (Cod." + negocianteSecundario.getCodigoRebelde() + ") não encontrado. Confira o código de identificação."));

		if(dbNegPrimario.getIndicadorTraidor() || dbNegSecundario.getIndicadorTraidor()) {
			throw new TraidorNegocianteException("Há um traidor entre nós! Resolva o problema, ou... Eu mesmo resolverei!");
		}
		
		negociantePrimario.setPontosNegociacao(calcularPontosNegociacao(negociantePrimario));
		negocianteSecundario.setPontosNegociacao(calcularPontosNegociacao(negocianteSecundario));
		
		if(negociantePrimario.getPontosNegociacao() != negocianteSecundario.getPontosNegociacao()) {
			throw new PontosInsuficientesException("Ora ora... Parece que temos um espertinho querendo tirar vantagem! As negociações só podem ser feitas com a mesma quantidade de pontos para ambas as partes.");
		}
		
	}
	
	
	private Integer calcularPontosNegociacao(RebeldeNegocianteDTO negociante) {
		negociante.getItens().forEach(
				item -> {
					negociante.setPontosNegociacao(negociante.getPontosNegociacao() + item.getPontos());
				});
		
		return negociante.getPontosNegociacao();
	}

	private List<ItemDTO> efetivarTransferencia(Rebelde rebeldeOrigem, Rebelde rebeldeDestino, List<ItemDTO> itens) {
		//Método fará a troca dos itens e devolverá a lista de itens que o jogador destino tinha antes da troca.
	
		rebeldeOrigem.getInventario().getAgrupamento().getItens().stream().findFirst(x -> x.codigo = 5).get();
		rebeldeOrigem.getInventario().getAgrupamento().getItens().stream().filter(x -> x.getCodigo() = )
		
		itens.forEach(item -> {
			rebeldeOrigem.getInventario().getAgrupamento().getItens()
				.stream()
				.filter(x -> x.getCodigo() = item.getCodigo())
				.findFirst()
			
			
		});
	}

}
