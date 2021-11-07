package com.letscode.starwarresistence.service.rebelde;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letscode.starwarresistence.domain.rebelde.Inventario;
import com.letscode.starwarresistence.domain.rebelde.InventarioAgrupamento;
import com.letscode.starwarresistence.domain.rebelde.Rebelde;
import com.letscode.starwarresistence.domain.recursos.Item;
import com.letscode.starwarresistence.dto.rebelde.RebeldeDTO;
import com.letscode.starwarresistence.dto.universo.MapaLocalizacaoDTO;
import com.letscode.starwarresistence.repository.rebelde.InventarioAgrupamentoRepository;
import com.letscode.starwarresistence.repository.rebelde.InventarioRepository;
import com.letscode.starwarresistence.repository.rebelde.RebeldeRepository;
import com.letscode.starwarresistence.service.universo.iMapaLocalizacaoService;
import com.letscode.starwarresistence.shared.exceptions.ObjectNotFoundException;

@Service
public class RebeldeService implements iRebeldeService{
	
	@Autowired
	private RebeldeRepository repository;

	@Autowired
	private InventarioRepository inventarioRepository;

	@Autowired
	private InventarioAgrupamentoRepository inventarioAgrupamentoRepository;
	
	@Autowired
	private iMapaLocalizacaoService localizacaoService;

	@Override
	public void adicionarRebelde(RebeldeDTO rebelde) {
		//ModelMapper mapper = new ModelMapper();
		//Rebelde dataRebelde = mapper.map(rebelde, Rebelde.class);
		
		Inventario inventario = inventarioRepository.save(Inventario.builder()
				.codigo(null)
				.build());
		
		Rebelde dataRebelde = Rebelde.builder()
				.codigo(null)
				.dataNascimento(rebelde.getDataNascimento())
				.genero(rebelde.getGenero())
				.indicadorTraidor(false)
				.nome(rebelde.getNome())
				.quantidadeTraicoesReportadas(0)
				.inventario(inventario)
				.localizacao(null)
				.dataCriacao(Calendar.getInstance().getTime())
				.build();
		
		dataRebelde = repository.save(dataRebelde);	
	
		
		List<Item> itens = rebelde.getInventario().getItens().stream()
				.map(item -> Item.builder().codigo(item.getCodigoItem()).build())
				.collect(Collectors.toList());

		List<InventarioAgrupamento> agrupamentos = itens.stream().map(
				item -> InventarioAgrupamento.builder()
					.codigo(null)
					.inventario(inventario)
					.item(item)
					.dataHoraCriacao(Calendar.getInstance().getTime())
					.build())
				.collect(Collectors.toList());
		
		agrupamentos = inventarioAgrupamentoRepository.saveAll(agrupamentos);
		
		repository.save(dataRebelde);
		
		localizacaoService.salvarLocalizacao(
				dataRebelde.getCodigo(),
				rebelde.getLocalizacao().getCodigoGalaxia(),
				rebelde.getLocalizacao().getLatitude(),
				rebelde.getLocalizacao().getLongitude());
	}

	@Override
	public void reportarTraidor(Integer codigoRebelde) throws ObjectNotFoundException {
		Rebelde rebelde = repository.findById(codigoRebelde).orElseThrow(
				() -> new ObjectNotFoundException("Rebelde não encontrado. Confira o código de identificação."));
		
		rebelde.setQuantidadeTraicoesReportadas(rebelde.getQuantidadeTraicoesReportadas() + 1);
				
		if(rebelde.getQuantidadeTraicoesReportadas() >= 3) {
			rebelde.setIndicadorTraidor(true);
		}
		
		repository.save(rebelde);
	}

	@Override
	public void atualizarLocalizacao(MapaLocalizacaoDTO mapaLocalizacaoRebelde) throws ObjectNotFoundException {
		Rebelde rebelde = repository.findById(mapaLocalizacaoRebelde.getCodigoRebelde()).orElseThrow(
				() -> new ObjectNotFoundException("Rebelde não encontrado. Confira o código de identificação."));
		
		
		localizacaoService.salvarLocalizacao(
				mapaLocalizacaoRebelde.getCodigoRebelde(), 
				mapaLocalizacaoRebelde.getCoordenadas().getCodigoGalaxia(),
				mapaLocalizacaoRebelde.getCoordenadas().getLatitude(),
				mapaLocalizacaoRebelde.getCoordenadas().getLongitude());
	}

}
