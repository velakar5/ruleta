package com.imb.proyectoruleta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imb.proyectoruleta.models.entities.Apostador;

@Repository
public interface ApostadorRepository extends JpaRepository<Apostador, Integer>{

}
