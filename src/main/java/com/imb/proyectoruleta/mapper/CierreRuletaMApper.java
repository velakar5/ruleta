package com.imb.proyectoruleta.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.imb.proyectoruleta.models.dto.ApuestaCerradaDTO;
import com.imb.proyectoruleta.models.dto.CierreRuletaDTO;
import com.imb.proyectoruleta.models.dto.RuletaDTO;
import com.imb.proyectoruleta.models.entities.Ruleta;

public class CierreRuletaMApper {

public static CierreRuletaDTO cierreRuletaMapper(Ruleta ruleta) {
		
		CierreRuletaDTO ruletaDTO = new CierreRuletaDTO();
		
		ruletaDTO.setId(ruleta.getId());
		ruletaDTO.setNumero(ruleta.getNumero());
		ruletaDTO.setColor(ruleta.getColor());
		ruletaDTO.setEstadoRuleta(ruleta.getEstadoRuleta());
		
		List<ApuestaCerradaDTO> apuestasCerradaDTO1 = ruleta.getApuestas()
				.stream()
				.map(ApuestaCerradaMapper::apuestaCerradaMapper)
				.collect(Collectors.toList());
		
		ruletaDTO.setApuestas(apuestasCerradaDTO1);
		return ruletaDTO;
	}
}
