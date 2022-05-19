package com.imb.proyectoruleta.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
@Table(name = "apostadores", schema = "ruleta")
public class Apostador implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "No puede ser nulo")
	@Column(name = "color_apostado", nullable = false)
	private Color colorApostado;

	@NotNull(message = "No puede ser nulo")
	@Max(value = 36,message = "No puede ser mayor a 36")
	@Min(value = 0,message = "no puede ser menor a 0")
	@Column(name = "numero_apostado", nullable = false)
	private Integer numeroApostado;

	@NotNull(message = "No puede ser nulo")
	@Column(name = "valorApostado", nullable = false)
	@Max(value = 10000,message = "No puede ser mayor a 10000")
	@Min(value = 0,message = "no puede ser menor a 0")
	private Double valorApostado;

	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ruleta_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "apuestas" })
	private Ruleta ruleta;

	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;

	public Apostador(Integer id, Color color, Integer numeroApostado, Double valorApostado) {
		this.id = id;
		this.colorApostado = color;
		this.numeroApostado = numeroApostado;
		this.valorApostado = valorApostado;
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
