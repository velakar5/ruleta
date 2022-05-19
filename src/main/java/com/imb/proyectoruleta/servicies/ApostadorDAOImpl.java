package com.imb.proyectoruleta.servicies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb.proyectoruleta.models.entities.Apostador;
import com.imb.proyectoruleta.models.entities.Ruleta;
import com.imb.proyectoruleta.repositories.ApostadorRepository;

@Service
public class ApostadorDAOImpl extends GenericoDAOImpl<Apostador, ApostadorRepository> implements ApostadorDAO {

	@Autowired 
	RuletaDAO ruletaDAo;
	
	
	public ApostadorDAOImpl(ApostadorRepository repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Apostador guardarApuesta(Apostador apuesta, Ruleta ruleta) {
		Apostador apuestaGuardada = repository.save(apuesta);
		apuestaGuardada.setRuleta(ruleta);
		return repository.save(apuestaGuardada);
	}
	
	
}