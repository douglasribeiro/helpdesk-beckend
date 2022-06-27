package com.douglas.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
