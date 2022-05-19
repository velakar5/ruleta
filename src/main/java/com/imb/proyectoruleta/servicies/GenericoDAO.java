package com.imb.proyectoruleta.servicies;

import java.util.Optional;

import com.imb.proyectoruleta.models.entities.Apostador;
import com.imb.proyectoruleta.models.entities.Ruleta;

public interface GenericoDAO<E>{

	public Optional<E> buscarPorId(Integer id);

	public Iterable<E> buscarTodos();

	public void eliminarPorId(Integer id);

	
}