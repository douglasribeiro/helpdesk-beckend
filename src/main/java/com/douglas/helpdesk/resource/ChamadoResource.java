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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglas.helpdesk.domain.Chamado;
import com.douglas.helpdesk.domain.Tecnico;
import com.douglas.helpdesk.domain.dto.ChamadoDTO;
import com.douglas.helpdesk.service.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {
	
	@Autowired
	private ChamadoService chamadoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(new ChamadoDTO(chamadoService.findById(id)));
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll(){
		return ResponseEntity.ok()
				.body(chamadoService.findAll().stream().map(x -> new ChamadoDTO(x)).collect(Collectors.toList()));
	}
	
	@PostMapping
	public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO chamadoDTO){
		Chamado newChamado = chamadoService.save(chamadoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newChamado.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
