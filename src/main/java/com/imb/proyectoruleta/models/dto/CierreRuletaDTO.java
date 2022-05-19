package com.imb.proyectoruleta.models.dto;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.imb.proyectoruleta.enums.Color;
import com.imb.proyectoruleta.models.entities.Apostador;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CierreRuletaDTO {
	
	private Integer id;

	@Enumerated(EnumType.STRING)
	private Color color;

	@NotNull(message = "No puede ser nulo")
	private Integer numero;

	private Boolean estadoRuleta;
	

	private List<ApuestaCerradaDTO> apuestas;

}
