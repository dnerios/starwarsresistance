package com.letscode.starwarresistence.domain.rebelde;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.letscode.starwarresistence.domain.universo.MapaLocalizacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "TB_REBELDES")
@Entity
public class Rebelde {

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "COD_REBL")
	private Integer codigo;

	@Column(name = "NOM_REBL", nullable = false)
	@NotNull(message = "O nome do rebelde é essencial, soldado!")
	private String nome;
	
	@Column(name = "DAT_NASC_REBL", nullable = false)
	@NotNull(message = "Parece que alguém não quer revelar a idade...")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataNascimento;
	
	@Column(name = "GEN_REBL", nullable = false)
	@NotNull(message = "Não esconda seu gênero, todos são bem-vindos!")
	private Character genero;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COD_REBL", nullable=false)
	private MapaLocalizacao localizacao;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COD_INVENT", nullable = true)
	private Inventario inventario;
	
	@Column(name = "IND_TRAID", nullable = false)
	private Boolean indicadorTraidor;

	@Column(name = "QTD_TRAID_REPORT", nullable = false)
	private Integer quantidadeTraicoesReportadas;

	@Column(name = "DAT_CRIA", nullable = false)
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date dataCriacao;
}
