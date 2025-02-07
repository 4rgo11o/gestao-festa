package com.algaworks.convidados.repository;

import com.algaworks.convidados.model.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Convidados extends JpaRepository<Convidado, Long> { }