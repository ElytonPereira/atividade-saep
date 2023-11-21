package br.com.senai;

import java.awt.EventQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.senai.entity.Motorista;
import br.com.senai.entity.Transportadora;
import br.com.senai.repository.MotoristaRepository;
import br.com.senai.service.MotoristaService;
import br.com.senai.service.UsuarioService;
import br.com.senai.view.ViewCadastroMotorista;
import br.com.senai.view.ViewLogin;
import br.com.senai.view.ViewPrincipal;


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
	
	@Autowired
	ViewCadastroMotorista viewCadastroMotorista;
	
	@Autowired
	ViewPrincipal viewPrincipal;
	
	
	
	
	
	
	@Autowired
	ViewLogin telaLogin;
	
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(InitApp.class);
		builder.headless(false);
		builder.run(args);
	}
	
	@Bean
	public CommandLineRunner commandLineRuner(ApplicationContext ctx) {
		return args -> {
			/*Usuario usuarioEncontrado = new Usuario();
			usuarioEncontrado = service.buscarUsuarioPor("adm", "1234");
			*/
			EventQueue.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					try {
						
						telaLogin.setVisible(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			});
			/*
			transportadora = usuarioEncontrado.getTransportadora();
			
			
			motorista.setNome("elyton");
			motorista.setCnh(12345678);
			motorista.setTransportadora(transportadora);
			
			motoristaService.salvar(motorista);
			/*
			motoristaService.excluirPor(2);
			*/
			
			//System.out.println(usuarioEncontrado.getNome() + " - " + usuarioEncontrado.getTransportadora());
			System.out.println("Subiu saep");
		};
	}

}
