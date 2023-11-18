package br.com.senai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.senai.entity.Motorista;
import br.com.senai.entity.Transportadora;
import br.com.senai.entity.Usuario;
import br.com.senai.service.MotoristaService;
import br.com.senai.service.UsuarioService;


@SpringBootApplication
public class InitApp {
	
	@Autowired
	UsuarioService service;
	
	@Autowired
	MotoristaService motoristaService;
	
	@Autowired
	Motorista motorista;
	
	@Autowired
	Transportadora transportadora;
	
	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);	
		
	}
	
	@Bean
	public CommandLineRunner commandLineRuner(ApplicationContext ctx) {
		return args -> {
			Usuario usuarioEncontrado = new Usuario();
			usuarioEncontrado = service.buscarUsuarioPor("adm", "1234");
			
			
			
			transportadora = usuarioEncontrado.getTransportadora();
			
			
			motorista.setNome("elyton");
			motorista.setCnh(12345678);
			motorista.setTransportadora(transportadora);
			
			motoristaService.salvar(motorista);
			
			motoristaService.excluirPor(2);
			
			
			System.out.println(usuarioEncontrado.getNome() + " - " + usuarioEncontrado.getTransportadora());
			System.out.println("Subiu saep");
		};
	}

}
