package com.douglas.helpdesk.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
