package com.letscode.starwarresistence.relatorio;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.letscode.starwarresistence.domain.universo.MapaLocalizacao;
import com.letscode.starwarresistence.dto.rebelde.InventarioDTO;
import com.letscode.starwarresistence.dto.rebelde.RebeldeDTO;
import com.letscode.starwarresistence.dto.recursos.ItemPostDTO;
import com.letscode.starwarresistence.dto.universo.LocalizacaoDTO;
import com.letscode.starwarresistence.repository.universo.MapaLocalizacaoRepository;
import com.letscode.starwarresistence.service.rebelde.iRebeldeService;
import com.letscode.starwarresistence.service.universo.iMapaLocalizacaoService;
import com.letscode.starwarresistence.shared.exceptions.ObjectNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
class RelatorioTest {
	
	@Autowired
	private iMapaLocalizacaoService mapaLocalizacaoService;

	@Autowired
	private MapaLocalizacaoRepository mapaLocalizacaoRepository;
	
	@Autowired
	private iRebeldeService rebeldeService;
	
	@Test
	public void salvarLocalizacaoDeRebeldeInexistente() {
		assertThrows(ObjectNotFoundException.class, () -> { 
			mapaLocalizacaoService.salvarLocalizacao(9999, 9999, 111.000, 222.1111);
		});
		
	}
	
	@Test
	public void salvarLocalizacaoDeRebeldeExistenteGalaxiaIncorreta() {
		List<ItemPostDTO> listItens = new ArrayList<ItemPostDTO>() {{
			add(ItemPostDTO.builder().codigoItem(1).build());
			add(ItemPostDTO.builder().codigoItem(2).build());
		}};
		
		rebeldeService.adicionarRebelde(RebeldeDTO.builder()
				.dataNascimento(Calendar.getInstance().getTime())
				.genero('M')
				.inventario(InventarioDTO.builder()
						.itens(listItens)
						.build())
				.localizacao(LocalizacaoDTO.builder()
						.codigoGalaxia(1)
						.latitude(111.111)
						.longitude(222.222)
						.build())
				.nome("TesteRebelde")
				.build());
		
		assertThrows(ObjectNotFoundException.class, () -> { 
			mapaLocalizacaoService.salvarLocalizacao(1, 3, 888.000, 7777.1111);
		});
		
	}
	
	@Test
	public void salvarLocalizacaoSucesso() {
		List<ItemPostDTO> listItens = new ArrayList<ItemPostDTO>() {{
			add(ItemPostDTO.builder().codigoItem(1).build());
			add(ItemPostDTO.builder().codigoItem(2).build());
		}};
		
		rebeldeService.adicionarRebelde(RebeldeDTO.builder()
				.dataNascimento(Calendar.getInstance().getTime())
				.genero('M')
				.inventario(InventarioDTO.builder()
						.itens(listItens)
						.build())
				.localizacao(LocalizacaoDTO.builder()
						.codigoGalaxia(1)
						.latitude(111.111)
						.longitude(222.222)
						.build())
				.nome("TesteRebelde")
				.build());

		mapaLocalizacaoService.salvarLocalizacao(1, 1, 888.000, 7777.1111);
		
		MapaLocalizacao localizacao = mapaLocalizacaoRepository.findById(1).get();
		
		assertTrue(localizacao.getLatitude() == 888.000);
		assertTrue(localizacao.getLongitude() == 7777.1111);
		
	}

}
