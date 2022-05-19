package com.imb.proyectoruleta.servicies;

import com.imb.proyectoruleta.models.entities.Apostador;
import com.imb.proyectoruleta.models.entities.Ruleta;

public interface ApostadorDAO extends GenericoDAO<Apostador>{

	public Apostador guardarApuesta(Apostador apuesta, Ruleta ruleta);
}
