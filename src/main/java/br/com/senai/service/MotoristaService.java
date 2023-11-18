package br.com.senai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.senai.entity.Motorista;
import br.com.senai.repository.EntregaRepository;
import br.com.senai.repository.MotoristaRepository;
import jakarta.validation.constraints.NotNull;

@Service
@Validated
public class MotoristaService {
	
	@Autowired
	private MotoristaRepository repository;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	public Motorista salvar(@NotNull(message = "O motorista é obrigatório") Motorista motorista) {			
		
		return this.repository.save(motorista);				
	}
	/*
	public Page<Motorista> listarPor(@NotNull(message = "O id da transportadora é obrigatório para listar o motorista") Integer id){
		
		return repository.listarPor(id);
	}*/
	
	public Motorista excluirPor(Integer id) {
		Motorista motoristaEncontrado = repository.buscarPor(id);
		Long qtdeDeEntregaVinculadas =entregaRepository.contarPor(id);
		Preconditions.checkArgument(qtdeDeEntregaVinculadas == 0, 
				"O motorista não pode ser removido por existirem entregas vinculados ao mesmo");
		this.repository.deleteById(id);
		return motoristaEncontrado;
	}
	
}
