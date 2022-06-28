package com.douglas.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.helpdesk.domain.Pessoa;
import com.douglas.helpdesk.domain.Tecnico;
import com.douglas.helpdesk.domain.dto.TecnicoDTO;
import com.douglas.helpdesk.repository.PessoaRepository;
import com.douglas.helpdesk.repository.TecnicoRepository;
import com.douglas.helpdesk.service.exception.DataIntegrityViolationException;
import com.douglas.helpdesk.service.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado!"));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico save(TecnicoDTO tecnicoDTO) {
		Tecnico obj = new Tecnico(tecnicoDTO);
		validaCpfEmail(tecnicoDTO);
		obj.setId(null);
		return tecnicoRepository.save(obj);
	}

	private void validaCpfEmail(TecnicoDTO tecnicoDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("C.P.F ja cadastrado.");
		}
		
		 obj = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail ja cadastrado.");
		}
	}
}
