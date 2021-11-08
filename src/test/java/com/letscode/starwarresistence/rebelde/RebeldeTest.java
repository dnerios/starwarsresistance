package com.letscode.starwarresistence.rebelde;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.letscode.starwarresistence.dto.rebelde.InventarioDTO;
import com.letscode.starwarresistence.dto.rebelde.RebeldeDTO;
import com.letscode.starwarresistence.dto.recursos.ItemPostDTO;
import com.letscode.starwarresistence.dto.universo.LocalizacaoDTO;
import com.letscode.starwarresistence.repository.rebelde.RebeldeRepository;
import com.letscode.starwarresistence.service.rebelde.iRebeldeService;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
class RebeldeTest {
	
	@Autowired
	private iRebeldeService rebeldeService;
	
	@Autowired
	private RebeldeRepository rebeldeRepository;
	
	@Test
	public void registrarRebeldeSucesso() {
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

		
		
		assertTrue(rebeldeRepository.count() == 1);
		
	}
	
	@Test
	public void registrarRebeldeSemNomeFalha() {
		List<ItemPostDTO> listItens = new ArrayList<ItemPostDTO>() {{
			add(ItemPostDTO.builder().codigoItem(1).build());
			add(ItemPostDTO.builder().codigoItem(2).build());
		}};
		
		assertThrows(ConstraintViolationException.class, () -> {
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
					.nome(null)
					.build());
		});
		
	}
	
	@Test
	public void reportarTraidorSucesso() {
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

		rebeldeService.reportarTraidor(1);
		rebeldeService.reportarTraidor(1);
		rebeldeService.reportarTraidor(1);
		
		assertTrue(rebeldeRepository.findByIndicadorTraidor(true).size() == 1);
		
	}

}
