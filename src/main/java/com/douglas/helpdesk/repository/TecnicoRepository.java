package com.douglas.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
