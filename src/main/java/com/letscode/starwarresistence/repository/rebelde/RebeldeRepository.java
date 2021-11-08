package com.letscode.starwarresistence.repository.rebelde;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.letscode.starwarresistence.domain.rebelde.Rebelde;

public interface RebeldeRepository extends JpaRepository<Rebelde, Integer> {

	@Transactional(readOnly=true)
	Integer countByIndicadorTraidor(Boolean indicadorTraidor);
	
	@Transactional(readOnly=true)
	List<Rebelde> findByIndicadorTraidor(Boolean indicadorTraidor);
}
