package br.com.senai.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import br.com.senai.entity.Transportadora;

@Component
@Lazy
public class ViewMotoristas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;	

	private  String nomeTransportadora; 	
	
	@Autowired
	private ViewCadastroMotorista viewCadastroMotorista;
	
	@Autowired
	private Transportadora transportadora;	


	public void pegarTransportadora(Transportadora transportadora) {
		Preconditions.checkNotNull(transportadora, "A transportadora não pode ser nula");
		this.nomeTransportadora = transportadora.getNome().toUpperCase();
		this.transportadora = transportadora;
		setTitle(nomeTransportadora);
		
		
	}



	public ViewMotoristas() {
		
	
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		System.out.println(this.nomeTransportadora);
		
		setContentPane(contentPane);
		
		JButton btnCadastrarMotorista = new JButton("Cadastrar Motorista");
		btnCadastrarMotorista.setBounds(10, 205, 157, 23);
		btnCadastrarMotorista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				viewCadastroMotorista.pegarTransportadora(transportadora);
				viewCadastroMotorista.setVisible(true);
				dispose();
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnCadastrarMotorista);
		
		JButton btnSair = new JButton("Logout");
		btnSair.setBounds(345, 0, 89, 23);
		contentPane.add(btnSair);
		
	}
}
