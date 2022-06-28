package com.douglas.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.helpdesk.domain.Tecnico;
import com.douglas.helpdesk.domain.dto.TecnicoDTO;
import com.douglas.helpdesk.repository.TecnicoRepository;
import com.douglas.helpdesk.service.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado!"));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico save(TecnicoDTO tecnicoDTO) {
		Tecnico obj = new Tecnico(tecnicoDTO);
		obj.setId(null);
		return tecnicoRepository.save(obj);
	}
}
