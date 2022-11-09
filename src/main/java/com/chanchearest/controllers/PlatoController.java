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

import com.chanchearest.entities.CategoriaPlato;
import com.chanchearest.entities.Plato;
import com.chanchearest.repo.CategoriaPlatoRepo;
import com.chanchearest.repo.PlatoRepo;

@RestController
@RequestMapping("/api/plato")
public class PlatoController {
	
	@Autowired
	private PlatoRepo platoRepo;
	
	@Autowired
	private CategoriaPlatoRepo categoriaPlatoRepo;
	
	@GetMapping
	public ResponseEntity<Page<Plato>> listarPlatos(Pageable pageable) {
		return ResponseEntity.ok(platoRepo.findAll(pageable));
	}
	
	
	@PostMapping
	public ResponseEntity<Plato> guardarPlato(@Valid @RequestBody Plato plato) {
		Optional<CategoriaPlato> categoriaPlatoOptional = categoriaPlatoRepo.findById(plato.getCategoriaPlato().getIdCategoriaPlato());

		if(!categoriaPlatoOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		plato.setCategoriaPlato(categoriaPlatoOptional.get());
		Plato platoGuardado = platoRepo.save(plato);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCategoriaPlato}")
				.buildAndExpand(platoGuardado.getCategoriaPlato()).toUri();
		
		return ResponseEntity.created(ubicacion).body(platoGuardado);
	}
	
	@PutMapping("/{idPlato}")
	public ResponseEntity<Plato> actualizarPlato(@Valid @RequestBody Plato plato, @PathVariable Integer idPlato) {
		
		Optional<CategoriaPlato> categoriaPlatoOptional = categoriaPlatoRepo.findById(plato.getCategoriaPlato().getIdCategoriaPlato());
		if(!categoriaPlatoOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		Optional<Plato> platoOptional = platoRepo.findById(idPlato);
		if(!platoOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		
		plato.setCategoriaPlato(categoriaPlatoOptional.get());
		plato.setIdPlato(platoOptional.get().getIdPlato());
		platoRepo.save(plato);

		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{idPlato}")
	public ResponseEntity<Plato> eliminarPlato(@PathVariable Integer idPlato) {
		
		Optional<Plato> platoOptional = platoRepo.findById(idPlato);
		if(!platoOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		platoRepo.delete(platoOptional.get());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{idPlato}")
	public ResponseEntity<Plato> obtenerPlatoId(@PathVariable Integer idPlato) {
		
		Optional<Plato> platoOptional = platoRepo.findById(idPlato);
		if(!platoOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.ok(platoOptional.get());
	}
	

}
