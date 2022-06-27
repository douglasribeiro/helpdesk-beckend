package com.douglas.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
