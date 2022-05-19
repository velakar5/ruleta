package com.imb.proyectoruleta.mapper;

import com.imb.proyectoruleta.models.dto.CreaApuestaDTO;
import com.imb.proyectoruleta.models.dto.IdRuletaDTO;
import com.imb.proyectoruleta.models.entities.Apostador;

public class MuestraAuestasCerradas {

public static CreaApuestaDTO creaApuestaMApper(Apostador apostador) {
		
		CreaApuestaDTO creaApuestaDTO = new CreaApuestaDTO();
		creaApuestaDTO.setId(apostador.getId());
		creaApuestaDTO.setColorApostado(apostador.getColorApostado());
		creaApuestaDTO.setNumeroApostado(apostador.getNumeroApostado());
		creaApuestaDTO.setValorApostado(apostador.getValorApostado());
		IdRuletaDTO idRuletaDTO = new IdRuletaDTO();
		idRuletaDTO.setId(apostador.getRuleta().getId());
		idRuletaDTO.setEstadoRuleta(apostador.getRuleta().getEstadoRuleta());
		creaApuestaDTO.setRuleta(idRuletaDTO);
		
		
		return creaApuestaDTO;
	}
}
