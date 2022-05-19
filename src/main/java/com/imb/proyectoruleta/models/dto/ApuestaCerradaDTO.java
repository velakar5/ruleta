package com.imb.proyectoruleta.models.dto;

import com.imb.proyectoruleta.enums.Color;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApuestaCerradaDTO {

	private Integer id;


	private Color colorApostado;

	
	private Integer numeroApostado;


	private Double valorApostado;

}
