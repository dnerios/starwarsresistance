package com.letscode.starwarresistence.service.recursos;

import com.letscode.starwarresistence.dto.rebelde.RebeldeNegocianteDTO;
import com.letscode.starwarresistence.shared.exceptions.ObjectNotFoundException;
import com.letscode.starwarresistence.shared.exceptions.PontosInsuficientesException;
import com.letscode.starwarresistence.shared.exceptions.TraidorNegocianteException;

public interface iItemService {
	
	void negociar(RebeldeNegocianteDTO negociantePrimario, RebeldeNegocianteDTO negocianteSecundario) throws ObjectNotFoundException, TraidorNegocianteException, PontosInsuficientesException;

}
