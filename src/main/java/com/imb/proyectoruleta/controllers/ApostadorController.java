package com.imb.proyectoruleta.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb.proyectoruleta.exceptions.RuletaInexistente;
import com.imb.proyectoruleta.mapper.CreaApuestaMApper;
import com.imb.proyectoruleta.models.dto.CreaApuestaDTO;
import com.imb.proyectoruleta.models.entities.Apostador;
import com.imb.proyectoruleta.models.entities.Ruleta;
import com.imb.proyectoruleta.servicies.ApostadorDAO;
import com.imb.proyectoruleta.servicies.RuletaDAO;

@RestController
@RequestMapping("/apostador")
public class ApostadorController {

	@Autowired
	private ApostadorDAO apostadorDao;
	
	@Autowired
	private RuletaDAO ruletaDao;
	
	
	@PostMapping("/{idRuleta}")
	public ResponseEntity<?> guardarApuesta(@Valid @RequestBody Apostador apuesta, BindingResult result, @PathVariable Integer idRuleta){
		Map<String, Object> validaciones = new HashMap<>();
		List<String> listaErrores = new ArrayList<>();
		if(result.hasErrors()) {
			listaErrores = result.getFieldErrors().stream()
					.map(errores -> "Campo"+errores.getField()+" "+errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista de errores", listaErrores);
			return new ResponseEntity<Map<String,Object>>(validaciones,HttpStatus.BAD_REQUEST);
		}
			
		
		
		Optional<Ruleta> oRuleta = ruletaDao.buscarPorId(idRuleta);
		System.out.println(oRuleta.get().getId());
		if(oRuleta.isEmpty() || !oRuleta.get().getEstadoRuleta())
			throw new RuletaInexistente("No existe ruleta para la apuesta");
		Apostador apuestaRealizada = apostadorDao.guardarApuesta(apuesta, oRuleta.get());
		
		CreaApuestaDTO creaApuestaDTO = CreaApuestaMApper.creaApuestaMApper(apuestaRealizada);
		return  new ResponseEntity<CreaApuestaDTO>(creaApuestaDTO,HttpStatus.CREATED);
		
	}
	
	
	
}
