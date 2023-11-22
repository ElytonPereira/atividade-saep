package br.com.senai;

import java.awt.EventQueue;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.senai.entity.Motorista;
import br.com.senai.service.MotoristaService;
import br.com.senai.view.ViewLogin;



@SpringBootApplication
public class InitApp {

	
	
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(InitApp.class);
		builder.headless(false);
		builder.run(args);
	}
	
	@Autowired
	ViewLogin telaLogin;
	
	@Autowired
	MotoristaService service;
	
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
						List<Motorista> motoristas = service.listarPor(1);
						System.out.println(motoristas);
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
