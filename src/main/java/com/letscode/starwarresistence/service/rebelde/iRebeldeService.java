package com.letscode.starwarresistence.service.rebelde;

import com.letscode.starwarresistence.dto.rebelde.RebeldeDTO;
import com.letscode.starwarresistence.dto.universo.MapaLocalizacaoDTO;
import com.letscode.starwarresistence.shared.exceptions.ObjectNotFoundException;

public interface iRebeldeService {

	void adicionarRebelde(RebeldeDTO rebelde) throws ObjectNotFoundException;
	
	void atualizarLocalizacao(MapaLocalizacaoDTO mapaLocalizacaoRebelde) throws ObjectNotFoundException;

	void reportarTraidor(Integer codigoRebelde) throws ObjectNotFoundException;
}
