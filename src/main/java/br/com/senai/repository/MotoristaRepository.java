package br.com.senai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.entity.Motorista;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Integer>{

	@Query(value = "select m "
			+ "from Motorista m "
			+ "join fetch m.transportadora "
			+ "where m.transportadora.id = :id_transportadora "
			+ "order by m.nome "
			)
			
	public List<Motorista> listarPor(Integer id_transportadora);
	
	@Query(value = "select m "
			+ "from Motorista m "
			+ "where m.id = :id "
			+ "order by m.nome")
	public Motorista buscarPor(Integer id);
	
}
