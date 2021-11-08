package com.letscode.starwarresistence.recursos;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.letscode.starwarresistence.domain.rebelde.InventarioAgrupamento;
import com.letscode.starwarresistence.dto.rebelde.InventarioDTO;
import com.letscode.starwarresistence.dto.rebelde.RebeldeDTO;
import com.letscode.starwarresistence.dto.rebelde.RebeldeNegocianteDTO;
import com.letscode.starwarresistence.dto.recursos.ItemNegociadoDTO;
import com.letscode.starwarresistence.dto.recursos.ItemPostDTO;
import com.letscode.starwarresistence.dto.universo.LocalizacaoDTO;
import com.letscode.starwarresistence.repository.rebelde.InventarioAgrupamentoRepository;
import com.letscode.starwarresistence.service.rebelde.iRebeldeService;
import com.letscode.starwarresistence.service.recursos.iItemService;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
class ItemTest {
	
	@Autowired
	private iRebeldeService rebeldeService;
	
	@Autowired
	private iItemService itemService;
	
	@Autowired
	private InventarioAgrupamentoRepository inventarioAgrupamentoRepository;
	
	@Test
	void negociacaoSucesso() {
		List<ItemPostDTO> listItensA = new ArrayList<ItemPostDTO>() {{
			add(ItemPostDTO.builder().codigoItem(1).build());
			add(ItemPostDTO.builder().codigoItem(2).build());
		}};
		
		rebeldeService.adicionarRebelde(RebeldeDTO.builder()
				.dataNascimento(Calendar.getInstance().getTime())
				.genero('M')
				.inventario(InventarioDTO.builder()
						.itens(listItensA)
						.build())
				.localizacao(LocalizacaoDTO.builder()
						.codigoGalaxia(1)
						.latitude(-99.5029904)
						.longitude(222.222)
						.build())
				.nome("TesteRebelde")
				.build());
		
		List<ItemPostDTO> listItensB = new ArrayList<ItemPostDTO>() {{
			add(ItemPostDTO.builder().codigoItem(3).build());
			add(ItemPostDTO.builder().codigoItem(4).build());
		}};
		
		rebeldeService.adicionarRebelde(RebeldeDTO.builder()
				.dataNascimento(Calendar.getInstance().getTime())
				.genero('M')
				.inventario(InventarioDTO.builder()
						.itens(listItensB)
						.build())
				.localizacao(LocalizacaoDTO.builder()
						.codigoGalaxia(1)
						.latitude(111.111)
						.longitude(-99.5029904)
						.build())
				.nome("TesteRebelde2")
				.build());
		
		List<ItemNegociadoDTO> listItensNegociadoA = new ArrayList<ItemNegociadoDTO>() {{
			add(ItemNegociadoDTO.builder().codigoAgrupamento(2).build());
		}};

		List<ItemNegociadoDTO> listItensNegociadoB = new ArrayList<ItemNegociadoDTO>() {{
			add(ItemNegociadoDTO.builder().codigoAgrupamento(3).build());
			add(ItemNegociadoDTO.builder().codigoAgrupamento(4).build());
		}};
		
		
		RebeldeNegocianteDTO negociantePrimario = RebeldeNegocianteDTO.builder()
				.codigoRebelde(1)
				.itens(listItensNegociadoA)
				.build();
		
		RebeldeNegocianteDTO negocianteSecundario = RebeldeNegocianteDTO.builder()
				.codigoRebelde(2)
				.itens(listItensNegociadoB)
				.build();
		
		itemService.negociar(negociantePrimario, negocianteSecundario);
		
		InventarioAgrupamento agrupamento = inventarioAgrupamentoRepository.findById(2).get();
		
		assertTrue(agrupamento.getInventario().getCodigo() == 2);
		
	}

}
