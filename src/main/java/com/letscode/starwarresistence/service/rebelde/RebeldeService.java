package com.letscode.starwarresistence.service.rebelde;

import java.util.Calendar;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letscode.starwarresistence.domain.rebelde.Rebelde;
import com.letscode.starwarresistence.domain.universo.Galaxias;
import com.letscode.starwarresistence.dto.rebelde.RebeldeDTO;
import com.letscode.starwarresistence.dto.universo.MapaLocalizacaoDTO;
import com.letscode.starwarresistence.repository.rebelde.RebeldeRepository;
import com.letscode.starwarresistence.shared.exceptions.ObjectNotFoundException;

@Service
public class RebeldeService implements iRebeldeService{
	
	@Autowired
	private RebeldeRepository repository;

	@Override
	public void adicionarRebelde(RebeldeDTO rebelde) {
		ModelMapper mapper = new ModelMapper();
		Rebelde dataRebelde = mapper.map(rebelde, Rebelde.class);
		
		dataRebelde.setDataCriacao(Calendar.getInstance().getTime());
		
		repository.save(dataRebelde);
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
		
		rebelde.getLocalizacao().setGalaxia(Galaxias.builder().codigo(mapaLocalizacaoRebelde.getCoordenadas().getCodigoGalaxia()).build());
		rebelde.getLocalizacao().setLatitude(mapaLocalizacaoRebelde.getCoordenadas().getLatitude());
		rebelde.getLocalizacao().setLongitude(mapaLocalizacaoRebelde.getCoordenadas().getLongitude());
		
	}

}
