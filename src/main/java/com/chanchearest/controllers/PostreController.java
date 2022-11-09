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
import com.chanchearest.entities.Postre;
import com.chanchearest.repo.CategoriaPostreRepo;
import com.chanchearest.repo.PostreRepo;

@RestController
@RequestMapping("/api/postre")
public class PostreController {

	@Autowired
	private PostreRepo postreRepo;
	
	@Autowired
	private CategoriaPostreRepo categoriaPostreRepo;
	
	@GetMapping
	public ResponseEntity<Page<Postre>> listarPostre(Pageable pageable) {
		return ResponseEntity.ok(postreRepo.findAll(pageable));
	}
	
	@PostMapping
	public ResponseEntity<Postre> guardarPostre(@Valid @RequestBody Postre postre) {
		Optional <CategoriaPostre> categoriaPostreOptional = categoriaPostreRepo.findById(postre.getCategoriaPostre().getIdCategoriaPostre());
		
		if(!categoriaPostreOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		postre.setCategoriaPostre(categoriaPostreOptional.get());
		Postre postreGuardado = postreRepo.save(postre);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCategoriaPostre}")
				.buildAndExpand(postreGuardado.getCategoriaPostre()).toUri();
		
		return ResponseEntity.created(ubicacion).body(postreGuardado);
	}
	
	@PutMapping("/{idPostre}")
	public ResponseEntity<Postre> actualizarPostre(@Valid @RequestBody Postre postre, @PathVariable Integer idPostre) {
		
		Optional<CategoriaPostre> categoriaPostreOptional = categoriaPostreRepo.findById(postre.getCategoriaPostre().getIdCategoriaPostre());
		if(!categoriaPostreOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		Optional<Postre> postreOptional = postreRepo.findById(idPostre);
		if(!postreOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		postre.setCategoriaPostre(categoriaPostreOptional.get());
		postre.setIdPostre(postreOptional.get().getIdPostre());
		postreRepo.save(postre);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{idPostre}")
	public ResponseEntity<Postre> eliminarPostre(@PathVariable Integer idPostre) {
		
		Optional<Postre> postreOptional = postreRepo.findById(idPostre);
		if(!postreOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		postreRepo.delete(postreOptional.get());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{idPostre}")
	public ResponseEntity<Postre> obtenerPostreId(@PathVariable Integer idPlato) {
		Optional<Postre> postreOptional = postreRepo.findById(idPlato);
		if(!postreOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok(postreOptional.get());
	}
	
}
