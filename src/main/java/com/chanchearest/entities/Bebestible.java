package com.chanchearest.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "bebestible", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombreBebestible"})})
public class Bebestible {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBebestible;
	
	@NotNull
	private String nombreBebestible;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idCategoriaBebestible")
	@JsonProperty(access = Access.WRITE_ONLY)
	private CategoriaBebestible categoriaBebestible;

	public int getIdBebestible() {
		return idBebestible;
	}

	public void setIdBebestible(int idBebestible) {
		this.idBebestible = idBebestible;
	}

	public String getNombreBebestible() {
		return nombreBebestible;
	}

	public void setNombreBebestible(String nombreBebestible) {
		this.nombreBebestible = nombreBebestible;
	}

	public CategoriaBebestible getCategoriaBebestible() {
		return categoriaBebestible;
	}

	public void setCategoriaBebestible(CategoriaBebestible categoriaBebestible) {
		this.categoriaBebestible = categoriaBebestible;
	}
	
}
