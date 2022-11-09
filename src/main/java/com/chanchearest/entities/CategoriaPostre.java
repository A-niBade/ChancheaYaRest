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
@Table(name = "categoriaPostre")
public class CategoriaPostre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategoriaPostre;
	
	@NotNull
	private String nombreCategoriaPostre;

	@OneToMany(mappedBy = "idPostre", cascade = CascadeType.ALL)
	private Set<Postre> postres = new HashSet<>();

	public int getIdCategoriaPostre() {
		return idCategoriaPostre;
	}

	public void setIdCategoriaPostre(int idCategoriaPostre) {
		this.idCategoriaPostre = idCategoriaPostre;
	}

	public String getNombreCategoriaPostre() {
		return nombreCategoriaPostre;
	}

	public void setNombreCategoriaPostre(String nombreCategoriaPostre) {
		this.nombreCategoriaPostre = nombreCategoriaPostre;
	}

	public Set<Postre> getPostres() {
		return postres;
	}

	public void setPostres(Set<Postre> postres) {
		this.postres = postres;
		for(Postre postre: postres) {
			postre.setCategoriaPostre(this);
		}
	}
	
}
