package com.douglas.helpdesk.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.helpdesk.domain.Chamado;
import com.douglas.helpdesk.domain.Cliente;
import com.douglas.helpdesk.domain.Tecnico;
import com.douglas.helpdesk.domain.dto.ChamadoDTO;
import com.douglas.helpdesk.domain.enums.Prioridade;
import com.douglas.helpdesk.domain.enums.Status;
import com.douglas.helpdesk.repository.ChamadoRepository;
import com.douglas.helpdesk.service.exception.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
		
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = chamadoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado. id: " + id));
	}

	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}

	public Chamado save(@Valid ChamadoDTO chamadoDTO) {
		return chamadoRepository.save(newChamado(chamadoDTO));
	}
	
	private Chamado newChamado(ChamadoDTO objDTO) {
		Tecnico tecnico = tecnicoService.findById(objDTO.getTecnico());
		Cliente cliente = clienteService.findById(objDTO.getCliente());
		Chamado chamado = new Chamado();
		if(objDTO.getId() != null) {
			chamado.setId(objDTO.getId());
		}
		chamado.setCliente(cliente);
		chamado.setTecnico(tecnico);
		chamado.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade()));
		chamado.setStatus(Status.toEnum(objDTO.getStatus()));
		chamado.setTitulo(objDTO.getTitulo());
		chamado.setObservacao(objDTO.getObservacao());
		return chamado;
	}
}
