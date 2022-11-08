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
@Table(name = "postre", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombrePostre" }) })
public class Postre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPostre;

	@NotNull
	private String nombrePostre;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idCategoriaPostre")
	@JsonProperty(access = Access.WRITE_ONLY)
	private CategoriaPostre categoriaPostre;

	public int getIdPostre() {
		return idPostre;
	}

	public void setIdPostre(int idPostre) {
		this.idPostre = idPostre;
	}

	public String getNombrePostre() {
		return nombrePostre;
	}

	public void setNombrePostre(String nombrePostre) {
		this.nombrePostre = nombrePostre;
	}

	public CategoriaPostre getCategoriaPostre() {
		return categoriaPostre;
	}

	public void setCategoriaPostre(CategoriaPostre categoriaPostre) {
		this.categoriaPostre = categoriaPostre;
	}

}
