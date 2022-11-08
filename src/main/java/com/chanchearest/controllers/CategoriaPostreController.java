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

import com.chanchearest.entities.CategoriaPostre;
import com.chanchearest.repo.CategoriaPostreRepo;

@RestController
@RequestMapping("/api/categoriapostre")
public class CategoriaPostreController {
	
	@Autowired
	private CategoriaPostreRepo categoriaPostreRepo;
	
	@GetMapping
	public ResponseEntity<Page<CategoriaPostre>> listarCategoriaPostre(Pageable pageable){
		return ResponseEntity.ok(categoriaPostreRepo.findAll(pageable));
	}
	
	@PostMapping
	public ResponseEntity<CategoriaPostre> guardarCategoriaPostre(@Valid @RequestBody CategoriaPostre categoriaPostre) {
		
		CategoriaPostre categoriaPostreGuardada = categoriaPostreRepo.save(categoriaPostre);
		
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCategoriaPlato}")
				.buildAndExpand(categoriaPostreGuardada.getIdCategoriaPostre()).toUri();
		
		return ResponseEntity.created(ubicacion).body(categoriaPostreGuardada);
	}
	
	@PutMapping("/{idCategoriaPostre}") 
	public ResponseEntity<CategoriaPostre> actualizarCategoriaPostre(@Valid @RequestBody CategoriaPostre categoriaPostre, @PathVariable Integer idCategoriaPostre) {
		
		Optional<CategoriaPostre> categoriaPostreOptional = categoriaPostreRepo.findById(idCategoriaPostre);
		
		if(!categoriaPostreOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		categoriaPostre.setIdCategoriaPostre(categoriaPostreOptional.get().getIdCategoriaPostre());
		categoriaPostreRepo.save(categoriaPostre);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{idCategoriaPostre}")
	public ResponseEntity<CategoriaPostre> eliminarCategoriaPostre(@PathVariable Integer idCategoriaPostre) {
		
		Optional<CategoriaPostre> categoriaPostreOptional = categoriaPostreRepo.findById(idCategoriaPostre);
		
		if(!categoriaPostreOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		categoriaPostreRepo.delete(categoriaPostreOptional.get());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{idCategoriaPostre}")
	public ResponseEntity<CategoriaPostre> obtenerCategoriaId(@PathVariable Integer idCategoriaPostre) {
		Optional<CategoriaPostre> categoriaPostreOptional = categoriaPostreRepo.findById(idCategoriaPostre);
		
		if(!categoriaPostreOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok(categoriaPostreOptional.get());
	}
}
