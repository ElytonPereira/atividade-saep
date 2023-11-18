package br.com.senai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	@Query(value = 
			"SELECT u "
			+ "FROM Usuario u "
			+ "join fetch u.transportadora "
			+ "WHERE u.nome = :login "
			+ "and u.senha = :senha ")
	public Usuario buscarPor(String login, String senha);
	
}
