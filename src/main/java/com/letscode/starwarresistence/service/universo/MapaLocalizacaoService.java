package com.letscode.starwarresistence.service.universo;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letscode.starwarresistence.domain.rebelde.Rebelde;
import com.letscode.starwarresistence.domain.universo.Galaxias;
import com.letscode.starwarresistence.domain.universo.MapaLocalizacao;
import com.letscode.starwarresistence.repository.rebelde.RebeldeRepository;
import com.letscode.starwarresistence.repository.universo.GalaxiasRepository;
import com.letscode.starwarresistence.repository.universo.MapaLocalizacaoRepository;
import com.letscode.starwarresistence.shared.exceptions.ObjectNotFoundException;

@Service
public class MapaLocalizacaoService implements iMapaLocalizacaoService {

	@Autowired
	private RebeldeRepository repositoryRebelde;
	
	@Autowired
	private MapaLocalizacaoRepository repository;

	@Autowired
	private GalaxiasRepository repositoryGalaxias;
	
	@Override
	public MapaLocalizacao salvarLocalizacao(Integer codigoRebelde, Integer codigoGalaxia, Double latitude, Double longitude) throws ObjectNotFoundException {
		Rebelde rebelde = repositoryRebelde.findById(codigoRebelde).orElseThrow(
				() -> new ObjectNotFoundException("Rebelde não encontrado. Confira o código de identificação."));
		
		Galaxias galaxia = repositoryGalaxias.findById(codigoGalaxia).orElseThrow(
				() -> new ObjectNotFoundException("Acho que alguém está perdido... Galáxia não encontrada!"));
		
		if(rebelde.getLocalizacao() != null) {
			rebelde.getLocalizacao().setGalaxia(galaxia);
			rebelde.getLocalizacao().setLatitude(latitude);
			rebelde.getLocalizacao().setLongitude(longitude);
			rebelde.getLocalizacao().setDataHoraMovimentacao(Calendar.getInstance().getTime());

			return repository.save(rebelde.getLocalizacao());
		} else {
			return repository.save(MapaLocalizacao.builder()
					.codigoRebelde(rebelde.getCodigo())
					.galaxia(galaxia)
					.latitude(latitude)
					.longitude(longitude)
					.dataHoraMovimentacao(Calendar.getInstance().getTime())
					.build());
		}
		

	}

}
