package com.imb.proyectoruleta.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.imb.proyectoruleta.enums.Color;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="ruletas", schema = "ruleta")
public class Ruleta implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "No puede ser nulo")
	@Column(name = "color_ganador", nullable = false)
	@Enumerated(EnumType.STRING)
	private Color color;
	
	@Column(name = "numero_ganador", nullable = false)
	@NotNull(message = "No puede ser nulo")
	@Positive(message = "No puede ser un numero negativo")
	private Integer numero;
	
	@Column(name = "estado_ruleta")
	private Boolean estadoRuleta = false;
	
	
	@OneToMany(mappedBy = "ruleta", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","ruleta"})
	private List<Apostador> apuestas;
	
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;
	
	public Ruleta(Integer id, Color color, Integer numero, Boolean estadoRuleta) {
		this.id = id;
		this.color = color;
		this.numero = numero;
		this.estadoRuleta = estadoRuleta;
	}
	
	@JsonCreator
    public void DataList(List<Apostador> apuestas) {
        this.apuestas = apuestas; 
    }
	
	@PrePersist
	private void prePersist() {
		this.fechaCreacion = new Date();
	}
	
	@PreUpdate
	private void preUpdate() {
		this.fechaModificacion = new Date();
	}
	
	private static final long serialVersionUID = 1L;
	
}
