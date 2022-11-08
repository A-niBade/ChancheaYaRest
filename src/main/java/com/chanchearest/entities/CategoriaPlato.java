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
@Table(name = "categoriaPlato")
public class CategoriaPlato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategoriaPlato;
	
	@NotNull
	private String nombreCategoriaPlato;

	@OneToMany(mappedBy = "idPlato", cascade = CascadeType.ALL)
	private Set<Plato> platos = new HashSet<>();

	public int getIdCategoriaPlato() {
		return idCategoriaPlato;
	}

	public void setIdCategoriaPlato(int idCategoriaPlato) {
		this.idCategoriaPlato = idCategoriaPlato;
	}

	public String getNombreCategoriaPlato() {
		return nombreCategoriaPlato;
	}

	public void setNombreCategoriaPlato(String nombreCategoriaPlato) {
		this.nombreCategoriaPlato = nombreCategoriaPlato;
	}

	public Set<Plato> getPlatos() {
		return platos;
	}

	public void setPlatos(Set<Plato> platos) {
		this.platos = platos;
		for(Plato plato: platos) {
			plato.setCategoriaPlato(this);
		}
	}
	
}
