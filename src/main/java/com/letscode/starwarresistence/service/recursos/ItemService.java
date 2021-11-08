package com.letscode.starwarresistence.service.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letscode.starwarresistence.domain.rebelde.InventarioAgrupamento;
import com.letscode.starwarresistence.domain.rebelde.Rebelde;
import com.letscode.starwarresistence.domain.recursos.Item;
import com.letscode.starwarresistence.dto.rebelde.RebeldeNegocianteDTO;
import com.letscode.starwarresistence.dto.recursos.ItemNegociadoDTO;
import com.letscode.starwarresistence.repository.rebelde.InventarioAgrupamentoRepository;
import com.letscode.starwarresistence.repository.rebelde.RebeldeRepository;
import com.letscode.starwarresistence.repository.recursos.ItemRepository;
import com.letscode.starwarresistence.shared.exceptions.ObjectNotFoundException;
import com.letscode.starwarresistence.shared.exceptions.PontosInsuficientesException;
import com.letscode.starwarresistence.shared.exceptions.TraidorNegocianteException;

@Service
public class ItemService implements iItemService {

	@Autowired
	private RebeldeRepository repository;
	
	@Autowired
	private InventarioAgrupamentoRepository inventarioAgrupamentoRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public void negociar(RebeldeNegocianteDTO negociantePrimario, RebeldeNegocianteDTO negocianteSecundario) throws ObjectNotFoundException, TraidorNegocianteException, PontosInsuficientesException {
		///A negociação é feita utilizando o código do agrupamento, pois considerei que o rebelde poderia ter mais de uma quantidade do mesmo item
		///Não utilizei simplesmente um campo "quantidade" no item, pensando em uma singularidade de cada item no inventário. Ex: eu poderia ter uma arma que fosse aprimorada e outra que já estivesse quebrada, por exemplo.
		
		Rebelde dbNegPrimario = avaliarNegociante(negociantePrimario);
		Rebelde dbNegSecundario = avaliarNegociante(negocianteSecundario);
		
		avaliarRestricoes(negociantePrimario, negocianteSecundario);
		
		efetivarTransferencia(dbNegPrimario, dbNegSecundario, negociantePrimario.getItens());
		efetivarTransferencia(dbNegSecundario, dbNegPrimario, negocianteSecundario.getItens());
		
	}
	
	private Rebelde avaliarNegociante(RebeldeNegocianteDTO negociante) throws ObjectNotFoundException, TraidorNegocianteException {
		Rebelde rebelde = repository.findById(negociante.getCodigoRebelde()).orElseThrow(
				() -> new ObjectNotFoundException("Rebelde primário (Cod." + negociante.getCodigoRebelde() + ") não encontrado. Confira o código de identificação."));
		
		if(rebelde.getIndicadorTraidor()) {
			throw new TraidorNegocianteException(rebelde.getNome() + " é um traidor! Resolva o problema, ou... Eu mesmo resolverei!");
		} else {
			return rebelde;
		}
	}
	
	private RebeldeNegocianteDTO calcularPontosNegociacao(RebeldeNegocianteDTO negociante) {
		negociante.setPontosNegociacao(0);
		
		negociante.getItens().forEach(
				item -> {
					InventarioAgrupamento dbAgrupamento = inventarioAgrupamentoRepository.findById(item.getCodigoAgrupamento()).orElseThrow(
							() -> new ObjectNotFoundException("Agrupamento (Cod." + item.getCodigoAgrupamento() + ") não encontrado. Confira o código informado."));
					
					item.setCodigoAgrupamento(dbAgrupamento.getCodigo());
					
					Item dbItem = itemRepository.findById(dbAgrupamento.getItem().getCodigo()).orElseThrow(
							() -> new ObjectNotFoundException("Item (Cod." + item.getCodigoItem() + ") não encontrado. Confira o código de identificação."));
					negociante.setPontosNegociacao(negociante.getPontosNegociacao() + dbItem.getPontos());
				});
		
		return negociante;
	}
	
	private void avaliarRestricoes(RebeldeNegocianteDTO negociantePrimario, RebeldeNegocianteDTO negocianteSecundario) throws PontosInsuficientesException {
		negociantePrimario.setPontosNegociacao(calcularPontosNegociacao(negociantePrimario).getPontosNegociacao());
		negocianteSecundario.setPontosNegociacao(calcularPontosNegociacao(negocianteSecundario).getPontosNegociacao());
		
		if(negociantePrimario.getPontosNegociacao() != negocianteSecundario.getPontosNegociacao()) {
			throw new PontosInsuficientesException("Ora ora... Parece que temos um espertinho querendo tirar vantagem! As negociações só podem ser feitas com a mesma quantidade de pontos para ambas as partes.");
		}
	}

	private void efetivarTransferencia(Rebelde rebeldeOrigem, Rebelde rebeldeDestino, List<ItemNegociadoDTO> itens) {	
		
		for (ItemNegociadoDTO item : itens) {
			InventarioAgrupamento inventarioAgrupamento = inventarioAgrupamentoRepository.findById(item.getCodigoAgrupamento()).get();
			inventarioAgrupamento.setInventario(rebeldeDestino.getInventario());
			inventarioAgrupamentoRepository.save(inventarioAgrupamento);
		}
		
	}

}
