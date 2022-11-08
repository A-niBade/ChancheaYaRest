package com.chanchearest.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.chanchearest.repo.CategoriaPlatoRepo;
import com.chanchearest.entities.CategoriaPlato;

@RestController
@RequestMapping("/api/categoriaplato")
public class CategoriaPlatoController {
	
	@Autowired
	private CategoriaPlatoRepo categoriaPlatoRepo;
	
	@GetMapping
	public ResponseEntity<Page<CategoriaPlato>> listarCategoriaPlato(Pageable pageable){
		return ResponseEntity.ok(categoriaPlatoRepo.findAll(pageable));
	}
	
	@PostMapping
	public ResponseEntity<CategoriaPlato> guardarCategoriaPlato(@Valid @RequestBody CategoriaPlato categoriaPlato) {
		
		CategoriaPlato categoriaPlatoGuardada = categoriaPlatoRepo.save(categoriaPlato);
		
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCategoriaPlato}")
				.buildAndExpand(categoriaPlatoGuardada.getIdCategoriaPlato()).toUri();
		
		return ResponseEntity.created(ubicacion).body(categoriaPlatoGuardada);
	}
	
	@PutMapping("/{idCategoriaPlato}") 
	public ResponseEntity<CategoriaPlato> actualizarCategoriaPlato(@Valid @RequestBody CategoriaPlato categoriaPlato, @PathVariable Integer idCategoriaPlato) {
		
		Optional<CategoriaPlato> categoriaPlatoOptional = categoriaPlatoRepo.findById(idCategoriaPlato);
		
		if(!categoriaPlatoOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		categoriaPlato.setIdCategoriaPlato(categoriaPlatoOptional.get().getIdCategoriaPlato());
		categoriaPlatoRepo.save(categoriaPlato);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{idCategoriaPlato}")
	public ResponseEntity<CategoriaPlato> eliminarCategoriaPlato(@PathVariable Integer idCategoriaPlato) {
		
		Optional<CategoriaPlato> categoriaPlatoOptional = categoriaPlatoRepo.findById(idCategoriaPlato);
		
		if(!categoriaPlatoOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		categoriaPlatoRepo.delete(categoriaPlatoOptional.get());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{idCategoriaPlato}")
	public ResponseEntity<CategoriaPlato> obtenerCategoriaId(@PathVariable Integer idCategoriaPlato) {
		Optional<CategoriaPlato> categoriaPlatoOptional = categoriaPlatoRepo.findById(idCategoriaPlato);
		
		if(!categoriaPlatoOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok(categoriaPlatoOptional.get());
	}
}
