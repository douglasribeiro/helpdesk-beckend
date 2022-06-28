package com.douglas.helpdesk.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglas.helpdesk.domain.Tecnico;
import com.douglas.helpdesk.domain.dto.TecnicoDTO;
import com.douglas.helpdesk.service.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(new TecnicoDTO(tecnicoService.findById(id)));
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> listAll(){
		return ResponseEntity.ok()
				.body(tecnicoService.findAll().stream().map(x -> new TecnicoDTO(x)).collect(Collectors.toList()));
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> newTecnico(@Valid @RequestBody TecnicoDTO tecnicoDTO){
		Tecnico newTecnico = tecnicoService.save(tecnicoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTecnico.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
