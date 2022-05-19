package com.imb.proyectoruleta.mapper;

import com.imb.proyectoruleta.models.dto.RuletaDTO;
import com.imb.proyectoruleta.models.entities.Ruleta;

public class RuletaMapper {

	public static RuletaDTO mapRuleta(Ruleta ruleta) {
		
		RuletaDTO ruletaDTO = new RuletaDTO();
		ruletaDTO.setId(ruleta.getId());
		ruletaDTO.setNumero(ruleta.getNumero());
		ruletaDTO.setColor(ruleta.getColor());
		ruletaDTO.setEstadoRuleta(ruleta.getEstadoRuleta());
		
		
		return ruletaDTO;
	}
}
