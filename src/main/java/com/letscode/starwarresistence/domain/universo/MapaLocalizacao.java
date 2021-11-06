package com.letscode.starwarresistence.domain.universo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letscode.starwarresistence.domain.rebelde.Rebelde;

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
@Table(name = "TB_MAPA_LOCALIZACAO")
@Entity
public class MapaLocalizacao {

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "COD_REBL")
	private Integer codigoRebelde;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COD_GALX", nullable=false)
	private Galaxias galaxia;

	@Column(name = "LAT", nullable = false)
	@NotNull(message = "Não se esconda! Qual é a sua latitude?")
	private Double latitude;
	
	@Column(name = "LONG", nullable = false)
	@NotNull(message = "Não se esconda! Qual é a sua longitude?")
	private Double longitude;

	@Column(name = "DAT_HORA_MOVTO")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date dataHoraMovimentacao;
	
	
	//RELACIONAMENTOS
	
	@JsonIgnore
	@OneToOne(mappedBy="localizacao", fetch = FetchType.LAZY)
	private Rebelde rebelde;
}
