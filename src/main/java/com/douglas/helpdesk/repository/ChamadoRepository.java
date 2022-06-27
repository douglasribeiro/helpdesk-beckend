package com.douglas.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
