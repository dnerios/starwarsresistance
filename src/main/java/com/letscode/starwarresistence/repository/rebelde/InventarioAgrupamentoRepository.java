package com.letscode.starwarresistence.repository.rebelde;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.letscode.starwarresistence.domain.rebelde.InventarioAgrupamento;

public interface InventarioAgrupamentoRepository extends JpaRepository<InventarioAgrupamento, Integer> {

	@Transactional(readOnly=true)
	List<InventarioAgrupamento> findByInventario_CodigoIn(List<Integer> codigo);
}
