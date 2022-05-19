package com.imb.proyectoruleta.models.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.imb.proyectoruleta.enums.Color;
import com.imb.proyectoruleta.models.entities.Ruleta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreaApuestaDTO {

	private Integer id;


	private Color colorApostado;

	
	private Integer numeroApostado;


	private Double valorApostado;


	private IdRuletaDTO ruleta;
}
