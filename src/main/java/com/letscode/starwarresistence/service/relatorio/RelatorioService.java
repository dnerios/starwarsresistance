package com.letscode.starwarresistence.service.relatorio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letscode.starwarresistence.domain.rebelde.InventarioAgrupamento;
import com.letscode.starwarresistence.domain.rebelde.Rebelde;
import com.letscode.starwarresistence.repository.rebelde.InventarioAgrupamentoRepository;
import com.letscode.starwarresistence.repository.rebelde.RebeldeRepository;
import com.letscode.starwarresistence.shared.exceptions.NaoHaRebeldesException;
import com.letscode.starwarresistence.shared.exceptions.NaoHaTraidoresException;

@Service
public class RelatorioService implements iRelatorioService {

	@Autowired
	private RebeldeRepository rebeldeRepository;
	
	@Autowired
	private InventarioAgrupamentoRepository inventarioAgrupamentoRepository;
	
	@Override
	public double obterPercentualTraidores() {
		Integer qtdTraidores = rebeldeRepository.countByIndicadorTraidor(true);
		
		if (qtdTraidores == 0) {
			return 0;
		}
		
		return ((float)qtdTraidores / (float)rebeldeRepository.count()) * 100;
	}

	@Override
	public double obterPercentualRebeldes() {
		Integer qtdRebeldes = rebeldeRepository.countByIndicadorTraidor(false);
		
		if (qtdRebeldes == 0) {
			return 0;
		}
		
		return ((float)qtdRebeldes / (float)rebeldeRepository.count()) * 100;
	}

	@Override
	public double obterMediaRecursosRebeldes() throws NaoHaRebeldesException{
		
		List<Rebelde> listaRebeldes = rebeldeRepository.findByIndicadorTraidor(false);
		
		if(listaRebeldes.isEmpty()) {
			throw new NaoHaRebeldesException("A resistência sucumbiu... Não existem mais rebeldes!");
		}
		
		List<Integer> listaInventarios = listaRebeldes.stream()
			.map(rebelde -> rebelde.getInventario().getCodigo())
			.collect(Collectors.toList());
		
		List<InventarioAgrupamento> listaRecursos = inventarioAgrupamentoRepository.findByInventario_CodigoIn(listaInventarios);
		
		return (float)listaRecursos.size() / (float)listaRebeldes.size();
	}

	@Override
	public double obterPontosPerdidos() {
		List<Rebelde> listaTraidores = rebeldeRepository.findByIndicadorTraidor(true);
		
		if(listaTraidores.isEmpty()) {
			throw new NaoHaTraidoresException("Aqui não há espaço para traidores! Nenhum ponto foi perdido para eles!");
		}
		
		List<Integer> listaInventarios = listaTraidores.stream()
				.map(rebelde -> rebelde.getInventario().getCodigo())
				.collect(Collectors.toList());
		
		List<InventarioAgrupamento> listaRecursos = inventarioAgrupamentoRepository.findByInventario_CodigoIn(listaInventarios);
		
		Integer pontosPerdidos = 0;
		
		for (InventarioAgrupamento item : listaRecursos) {
			pontosPerdidos = pontosPerdidos + item.getItem().getPontos();
		}
		
		return pontosPerdidos;
	}

}
