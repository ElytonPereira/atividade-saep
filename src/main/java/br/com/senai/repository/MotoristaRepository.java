package br.com.senai.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.entity.Motorista;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Integer>{

	@Query("select m "
			+ "from Motorista m "
			+ "join fetch m.transportadora "
			+ "where m.transportadora.id = :id_transportadora "
			+ "order by m.nome")
	public Page<Motorista> listarPor(Integer id_transportadora);
	
	@Query("select m "
			+ "from Motorista m "
			+ "where m.id = :id "
			+ "order by m.nome")
	public Motorista buscarPor(Integer id);
	
}
