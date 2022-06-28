package com.douglas.helpdesk.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.helpdesk.domain.Chamado;
import com.douglas.helpdesk.domain.Cliente;
import com.douglas.helpdesk.domain.Tecnico;
import com.douglas.helpdesk.domain.enums.Perfil;
import com.douglas.helpdesk.domain.enums.Prioridade;
import com.douglas.helpdesk.domain.enums.Status;
import com.douglas.helpdesk.repository.ChamadoRepository;
import com.douglas.helpdesk.repository.ClienteRepository;
import com.douglas.helpdesk.repository.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "Valdir Cesar", "11111111111", "valdir@email.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Tecnico tec2 = new Tecnico(null, "José Lima", "11111111112", "jose@email.com", "123");
		tec2.addPerfil(Perfil.TECNICO);
		 
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "22222222222", "torvalds@email.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1, tec2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
}
