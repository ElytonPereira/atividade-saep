package br.com.senai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senai.entity.Transportadora;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Integer>{

}
