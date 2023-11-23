package br.com.senai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.entity.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer>{

	
	//public void listarPor(Integer idMotorista);
	
	@Query(value = 
			"SELECT e "
			+ "FROM Entrega e "
			+ "WHERE e.id = :id ")
	public Entrega buscarPor(Integer id);
	
	@Query(value = 
			"SELECT Count(e) "
			+ "FROM Entrega e "
			+ "WHERE e.motorista.id = :idDoMotorista ")
	public Long contarPor(Integer idDoMotorista);
	
}
