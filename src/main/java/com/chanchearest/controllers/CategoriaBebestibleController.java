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

import com.chanchearest.entities.CategoriaBebestible;
import com.chanchearest.repo.CategoriaBebestibleRepo;

@RestController
@RequestMapping("/api/categoriabebestible")
public class CategoriaBebestibleController {
	
	@Autowired
	private CategoriaBebestibleRepo categoriaBebestibleRepo;
	
	@GetMapping
	public ResponseEntity<Page<CategoriaBebestible>> listarCategoriaPlato(Pageable pageable){
		return ResponseEntity.ok(categoriaBebestibleRepo.findAll(pageable));
	}
	
	@PostMapping
	public ResponseEntity<CategoriaBebestible> guardarCategoriaBebestible(@Valid @RequestBody CategoriaBebestible categoriaBebestible) {
		
		CategoriaBebestible categoriaBebestibleGuardada = categoriaBebestibleRepo.save(categoriaBebestible);
		
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/idCategoriaBebestible")
				.buildAndExpand(categoriaBebestibleGuardada.getIdCategoriaBebestible()).toUri();
		
		return ResponseEntity.created(ubicacion).body(categoriaBebestibleGuardada);
	}
	
	@PutMapping("/{idCategoriaBebestible}")
	public ResponseEntity<CategoriaBebestible> actualizarCategoriaPlato(@Valid @RequestBody CategoriaBebestible categoriaBebestible, @PathVariable Integer idCategoriaBebestible) {
		Optional<CategoriaBebestible> categoriaBebestibleOptional = categoriaBebestibleRepo.findById(idCategoriaBebestible);
		
		if(!categoriaBebestibleOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		categoriaBebestible.setIdCategoriaBebestible(categoriaBebestibleOptional.get().getIdCategoriaBebestible());
		categoriaBebestibleRepo.save(categoriaBebestible);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{idCategoriaBebestible}")
	public ResponseEntity<CategoriaBebestible> eliminarCategoriaBebestible(@PathVariable Integer idCategoriaBebestible) {
		
		Optional<CategoriaBebestible> categoriaBebestibleOptional = categoriaBebestibleRepo.findById(idCategoriaBebestible);
		
		if(!categoriaBebestibleOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		categoriaBebestibleRepo.delete(categoriaBebestibleOptional.get());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{idCategoriaBebestible}")
	public ResponseEntity<CategoriaBebestible> obtenerCategoriaId(@PathVariable Integer idCategoriaBebestible) {
		Optional<CategoriaBebestible> categoriaBebestibleOptional = categoriaBebestibleRepo.findById(idCategoriaBebestible);
		
		if(!categoriaBebestibleOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok(categoriaBebestibleOptional.get());
	}

}
