package br.com.senai;

import java.awt.EventQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

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
		
	@Bean
	public CommandLineRunner commandLineRuner(ApplicationContext ctx) {
		return args -> {

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
			
			System.out.println("Subiu saep");
		};
	}
}
