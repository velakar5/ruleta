package com.imb.proyectoruleta.servicies;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.imb.proyectoruleta.exceptions.RuletaInexistente;
import com.imb.proyectoruleta.models.entities.Ruleta;
import com.imb.proyectoruleta.repositories.RuletaRepository;

@Service
public class RuletaDAOImpl extends GenericoDAOImpl<Ruleta, RuletaRepository> implements RuletaDAO {

	public RuletaDAOImpl(RuletaRepository repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer guardarRuleta(Ruleta ruleta) {
		return this.repository.save(ruleta).getId();
	}

	@Override
	public Ruleta activarRuleta(Ruleta ruletaObtenida) {
		
		ruletaObtenida.setEstadoRuleta(true);
		return repository.save(ruletaObtenida);
	}

	@Override
	public Ruleta cerrarRuleta(Ruleta ruletaObtenida) {
		ruletaObtenida.setEstadoRuleta(false);
		repository.save(ruletaObtenida);
		return repository.getById(ruletaObtenida.getId());
	}
	
	

}
