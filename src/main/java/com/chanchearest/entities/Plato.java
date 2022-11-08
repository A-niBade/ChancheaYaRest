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
@Table(name = "plato", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombrePlato"})})
public class Plato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPlato;
	
	@NotNull
	private String nombrePlato;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idCategoriaPlato")
	@JsonProperty(access = Access.WRITE_ONLY)
	private CategoriaPlato categoriaPlato;

	public int getIdPlato() {
		return idPlato;
	}

	public void setIdPlato(int idPlato) {
		this.idPlato = idPlato;
	}

	public String getNombrePlato() {
		return nombrePlato;
	}

	public void setNombrePlato(String nombrePlato) {
		this.nombrePlato = nombrePlato;
	}

	public CategoriaPlato getCategoriaPlato() {
		return categoriaPlato;
	}

	public void setCategoriaPlato(CategoriaPlato categoriaPlato) {
		this.categoriaPlato = categoriaPlato;
	}
	
}
