package com.imb.proyectoruleta.models.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.imb.proyectoruleta.enums.Color;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdRuletaDTO {

	private Integer id;

	private Boolean estadoRuleta;
}
