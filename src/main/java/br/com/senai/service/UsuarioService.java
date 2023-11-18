package br.com.senai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.senai.entity.Usuario;
import br.com.senai.repository.UsuarioRepository;
import jakarta.validation.constraints.NotBlank;

@Service
@Validated
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;

	public Usuario buscarUsuarioPor(
			@NotBlank(message = "Nome é obrigatório")
			String nome,
			@NotBlank(message = "senha é obrigatória")
			String senha
			) {
		Usuario usuarioEncontrado = repository.buscarPor(nome, senha);			
		Preconditions.checkNotNull(usuarioEncontrado, "Usuario não encontrado, ou informações incorretas");
		return usuarioEncontrado;
	}
	
}
