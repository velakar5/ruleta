package com.imb.proyectoruleta.mapper;

import com.imb.proyectoruleta.models.dto.ApuestaCerradaDTO;
import com.imb.proyectoruleta.models.entities.Apostador;

public class ApuestaCerradaMapper {
	public static ApuestaCerradaDTO apuestaCerradaMapper(Apostador apostador) {
		ApuestaCerradaDTO apuestaCerradaDTO = new ApuestaCerradaDTO();

		apuestaCerradaDTO.setId(apostador.getId());
		apuestaCerradaDTO.setColorApostado(apostador.getColorApostado());
		apuestaCerradaDTO.setNumeroApostado(apostador.getNumeroApostado());
		apuestaCerradaDTO.setValorApostado(apostador.getValorApostado());

		return apuestaCerradaDTO;
	}

}
