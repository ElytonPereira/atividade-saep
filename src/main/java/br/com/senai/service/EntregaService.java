package br.com.senai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.senai.entity.Entrega;
import br.com.senai.repository.EntregaRepository;
import jakarta.validation.constraints.NotNull;

@Service
@Validated
public class EntregaService {

	@Autowired
	private EntregaRepository repository;
	
	
	public Entrega salvar(@NotNull(message = "O motorista é obrigatório") Entrega entrega) {			
		Preconditions.checkNotNull(entrega, "O motorista não pode ser nulo");
		return repository.save(entrega);				
	}
	
	public List<Entrega> listarPor(@NotNull(message = "O id da transportadora é obrigatório para listar o motorista") Integer id){
		
		return repository.listarPor(id);
	}
	
	public Entrega excluirPor(Integer id) {
		Entrega entregaEncontrada = repository.buscarPor(id);
		
		this.repository.deleteById(id);
		return entregaEncontrada;
	}
	
}
