package com.letscode.starwarresistence.service.universo;

import com.letscode.starwarresistence.domain.universo.MapaLocalizacao;

public interface iMapaLocalizacaoService {

	MapaLocalizacao salvarLocalizacao(Integer codigoRebelde, Integer codigoGalaxia, Double latitude, Double longitude);
	
}
