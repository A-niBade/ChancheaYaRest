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

import com.chanchearest.entities.Bebestible;
import com.chanchearest.entities.CategoriaBebestible;
import com.chanchearest.repo.BebestibleRepo;
import com.chanchearest.repo.CategoriaBebestibleRepo;

@RestController
@RequestMapping("/api/bebestible")
public class BebestibleController {

	@Autowired
	private BebestibleRepo bebestibleRepo;

	@Autowired
	private CategoriaBebestibleRepo categoriaBebestibleRepo;

	@GetMapping
	public ResponseEntity<Page<Bebestible>> listarBebestibles(Pageable pageable) {
		return ResponseEntity.ok(bebestibleRepo.findAll(pageable));
	}

	@PostMapping
	public ResponseEntity<Bebestible> guardarBebestible(@Valid @RequestBody Bebestible bebestible) {
		Optional<CategoriaBebestible> categoriaBebestibleOptional = categoriaBebestibleRepo
				.findById(bebestible.getCategoriaBebestible().getIdCategoriaBebestible());

		if (!categoriaBebestibleOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		bebestible.setCategoriaBebestible(categoriaBebestibleOptional.get());
		Bebestible bebestibleGuardado = bebestibleRepo.save(bebestible);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCategoriaPlato}")
				.buildAndExpand(bebestibleGuardado.getCategoriaBebestible()).toUri();

		return ResponseEntity.created(ubicacion).body(bebestibleGuardado);
	}

	@PutMapping("/{idBebestible}")
	public ResponseEntity<Bebestible> actualizarBebestible(@Valid @RequestBody Bebestible bebestible,
			@PathVariable Integer idBebestible) {

		Optional<CategoriaBebestible> categoriaBebestibleOptional = categoriaBebestibleRepo
				.findById(bebestible.getCategoriaBebestible().getIdCategoriaBebestible());
		if (!categoriaBebestibleOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		Optional<Bebestible> bebestibleOptional = bebestibleRepo.findById(idBebestible);
		if (!bebestibleOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		bebestible.setCategoriaBebestible(categoriaBebestibleOptional.get());
		bebestible.setIdBebestible(bebestibleOptional.get().getIdBebestible());
		bebestibleRepo.save(bebestible);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{idBebestible}")
	public ResponseEntity<Bebestible> eliminarBebestible(@PathVariable Integer idBebestible) {

		Optional<Bebestible> bebestibleOptional = bebestibleRepo.findById(idBebestible);
		if (!bebestibleOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		bebestibleRepo.delete(bebestibleOptional.get());
		return ResponseEntity.noContent().build();
	}

}
