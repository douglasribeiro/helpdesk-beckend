package com.douglas.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.helpdesk.domain.Pessoa;
import com.douglas.helpdesk.domain.dto.ClienteDTO;
import com.douglas.helpdesk.domain.Cliente;
import com.douglas.helpdesk.repository.ClienteRepository;
import com.douglas.helpdesk.repository.PessoaRepository;
import com.douglas.helpdesk.service.exception.DataIntegrityViolationException;
import com.douglas.helpdesk.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado!"));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente save(ClienteDTO clienteDTO) {
		Cliente obj = new Cliente(clienteDTO);
		validaCpfEmail(clienteDTO);
		obj.setId(null);
		return clienteRepository.save(obj);
	}

	private void validaCpfEmail(ClienteDTO clienteDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(clienteDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != clienteDTO.getId()) {
			throw new DataIntegrityViolationException("C.P.F ja cadastrado.");
		}
		
		 obj = pessoaRepository.findByEmail(clienteDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != clienteDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail ja cadastrado.");
		}
	}
}
