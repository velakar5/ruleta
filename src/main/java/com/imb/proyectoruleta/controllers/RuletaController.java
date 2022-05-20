package com.imb.proyectoruleta.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.plaf.basic.BasicTreeUI.TreeHomeAction;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb.proyectoruleta.enums.Color;
import com.imb.proyectoruleta.exceptions.RuletaInexistente;
import com.imb.proyectoruleta.mapper.CierreRuletaMApper;
import com.imb.proyectoruleta.mapper.RuletaMapper;
import com.imb.proyectoruleta.models.dto.CierreRuletaDTO;
import com.imb.proyectoruleta.models.dto.RuletaDTO;
import com.imb.proyectoruleta.models.entities.Ruleta;
import com.imb.proyectoruleta.servicies.RuletaDAO;

@RestController
@RequestMapping("ruleta")
public class RuletaController {
	
	Logger logger = LoggerFactory.getLogger(RuletaController.class);

	@Autowired
	private RuletaDAO ruletaDao;
	
	/**
	 * EndPoint para devolver todas las ruletas
	 * @return List de objetos json de ruleta
	 * @author carlos de jesus velazquez lopez
	 */
	@GetMapping("/todos")
	public ResponseEntity<?> obtenerTodo(){
		List<Ruleta> ruletas = (List<Ruleta>) ruletaDao.buscarTodos();
		
		if(ruletas.isEmpty())
			throw new RuletaInexistente("No existen ruletas.");
		
		return new ResponseEntity<List<Ruleta>>(ruletas,HttpStatus.OK);
	}
	
	/**
	 * EndPint para devolver una ruleta en especifico por medio de su id
	 * @param idRuletparametro Integer para detectar una ruleta
	 * @return objeto json de ruleta
	 * @author carlos de jesus velazquez lopez
	 */
	@GetMapping("/{idRuleta}")
	public ResponseEntity<?> buscarPorId(@PathVariable Integer idRuleta){
		Optional<Ruleta> oRuleta = ruletaDao.buscarPorId(idRuleta);
		if(oRuleta.isEmpty())
			throw new RuletaInexistente("No existe ruleta con el id "+idRuleta);
		return new ResponseEntity<Ruleta>(oRuleta.get(),HttpStatus.OK);
	}
	
	/**
	 * EndPoint para crear una nueva ruleta
	 * @param ruleta objeto de tipo Ruleta con los datos de la nueva ruleta a guardar
	 * @param result parametro para guardar las validaciones que se activaron
	 * @return string con mensaje de confirmacion o denegacion segun sea el caso
	 * @author carlos de jesus velazquez lopez
	 */
	@PostMapping
	public ResponseEntity<?> creaRuleta(@Valid @RequestBody Ruleta ruleta, BindingResult result){
		
		Color color = null;
		Map<String, Object> validaciones = new HashMap<>();
		List<String> listaErrores = new ArrayList<>();
		if(ruleta.getNumero()==null || ruleta.getColor()==null) {
			listaErrores.add("No puede haber campos nulos");
			validaciones.put("Lista de errores", listaErrores);
			return new ResponseEntity<Map<String,Object>>(validaciones,HttpStatus.BAD_REQUEST);
		}
		if(result.hasErrors()) {
			listaErrores = result.getFieldErrors().stream()
					.map(errores -> "Campo"+errores.getField()+" "+errores.getDefaultMessage())
					.collect(Collectors.toList());
		}
		if(ruleta.getNumero()>36)
			listaErrores.add("Campo getNumeroApostado debe ser menor o igual a 36");
		if(!listaErrores.isEmpty()) {
			validaciones.put("Lista de errores", listaErrores);
			return new ResponseEntity<Map<String,Object>>(validaciones,HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Id de ruleta creada es : "+ruletaDao.guardarRuleta(ruleta),HttpStatus.CREATED);
		
	}
	
	/**
	 * EndPoint para activar una ruleta
	 * @param idRuleta parametro Integer para identificar la ruleta a activar
	 * @return String con mensaje exitoso  o de falla segun sea el caso
	 * @author carlos de jesus velazquez lopez
	 */
	@PutMapping("/abrirRuleta/{idRuleta}")
	public ResponseEntity<String> activarRuleta(@PathVariable Integer idRuleta){
		Optional<Ruleta> oRuleta = ruletaDao.buscarPorId(idRuleta);
		if(oRuleta.isEmpty())
			throw new RuletaInexistente("Operacion denegada, la ruleta no existe");
		if(oRuleta.get().getEstadoRuleta())
			throw new RuletaInexistente("Operacion denegada, la ruleta ya esta activa");
		Ruleta ruleta = oRuleta.get();
		ruletaDao.activarRuleta(ruleta);
		return new ResponseEntity<String>("Operacion exitosa",HttpStatus.OK);
	}
	
	/**
	 * EndPoint para cerrar una ruleta ya activa previamente
	 * @param idRuleta parametro Integer con el id de la ruleta a identificar
	 * @return Objeto Ruleta con todas las apuestas y datos de la ruleta realizados
	 * @author carlos de jesus velazquez lopez
	 */
	@PutMapping("/cerrarRuleta/{idRuleta}")
	public ResponseEntity<?> cerrarRuleta(@PathVariable Integer idRuleta){
		Optional<Ruleta> oRuleta = ruletaDao.buscarPorId(idRuleta);
		if(oRuleta.isEmpty())
			throw new RuletaInexistente("Operacion denegada, la ruleta no existe");
		if(!oRuleta.get().getEstadoRuleta())
			throw new RuletaInexistente("Operacion denegada, la ruleta ya esta inactiva");
		Ruleta ruleta = oRuleta.get();
		ruleta = ruletaDao.cerrarRuleta(ruleta);
		return new ResponseEntity<Ruleta>(ruleta,HttpStatus.OK);
	}
	
	/**
	 * EndPoint para devolver todas las ruletas
	 * @return List de objetos json de ruleta
	 * @author carlos de jesus velazquez lopez
	 */
	@GetMapping("/ruletasDTO")
	public ResponseEntity<?> obtenerRuletasDTO(){
		logger.info("Entrando a obtener resultados de Ruletas");
		List<Ruleta> ruletas = (List<Ruleta>) ruletaDao.buscarTodos();
		
		if(ruletas.isEmpty())
			throw new RuletaInexistente("No existen ruletas");
		
		List<RuletaDTO> ruletasDTO = ruletas
				.stream()
				.map(RuletaMapper::mapRuleta)
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<RuletaDTO>>(ruletasDTO,HttpStatus.OK);
	}
	
	/**
	 * EndPoint para cerrar una ruleta ya activa previamente
	 * @param idRuleta parametro Integer con el id de la ruleta a identificar
	 * @return Objeto Ruleta con todas las apuestas y datos de la ruleta realizados
	 * @author carlos de jesus velazquez lopez
	 */
	@PutMapping("/cerrarRuletaDTO/{idRuleta}")
	public ResponseEntity<?> cerrarRuletaDTO(@PathVariable Integer idRuleta){
		Optional<Ruleta> oRuleta = ruletaDao.buscarPorId(idRuleta);
		if(oRuleta.isEmpty())
			throw new RuletaInexistente("Operacion denegada, la ruleta no existe");
		if(!oRuleta.get().getEstadoRuleta())
			throw new RuletaInexistente("Operacion denegada, la ruleta ya esta inactiva");
		Ruleta ruleta = oRuleta.get();
		ruleta = ruletaDao.cerrarRuleta(ruleta);
		CierreRuletaDTO cierreRuletasDTO = CierreRuletaMApper.cierreRuletaMapper(ruleta);
				
		return new ResponseEntity<CierreRuletaDTO>(cierreRuletasDTO,HttpStatus.OK);
	}
	
}
