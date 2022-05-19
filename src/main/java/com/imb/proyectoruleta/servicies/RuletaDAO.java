package com.imb.proyectoruleta.servicies;



import com.imb.proyectoruleta.models.entities.Ruleta;


public interface RuletaDAO extends GenericoDAO<Ruleta>{

	public Integer guardarRuleta(Ruleta ruleta);
	public Ruleta activarRuleta(Ruleta ruletaObtenida);
	public Ruleta cerrarRuleta(Ruleta ruletaObtenida);
}
