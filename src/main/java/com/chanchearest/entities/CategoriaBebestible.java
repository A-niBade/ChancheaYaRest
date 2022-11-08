package com.chanchearest.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categoriaBebestible")
public class CategoriaBebestible {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategoriaBebestible;

	@NotNull
	private String nombreCategoriaBebestible;

	@OneToMany(mappedBy = "idBebestible", cascade = CascadeType.ALL)
	private Set<Bebestible> bebestibles = new HashSet<>();

	public int getIdCategoriaBebestible() {
		return idCategoriaBebestible;
	}

	public void setIdCategoriaBebestible(int idCategoriaBebestible) {
		this.idCategoriaBebestible = idCategoriaBebestible;
	}

	public String getNombreCategoriaBebestible() {
		return nombreCategoriaBebestible;
	}

	public void setNombreCategoriaBebestible(String nombreCategoriaBebestible) {
		this.nombreCategoriaBebestible = nombreCategoriaBebestible;
	}

	public Set<Bebestible> getBebestibles() {
		return bebestibles;
	}

	public void setBebestibles(Set<Bebestible> bebestibles) {
		this.bebestibles = bebestibles;
		for (Bebestible bebestible : bebestibles) {
			bebestible.setCategoriaBebestible(this);
		}
	}

}
