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
public class ViewEntregas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private  String nomeTransportadora; 	
	
	@Autowired @Lazy
	private ViewCadastroEntregas viewCadastroEntregas;
		
	@Autowired
	private Transportadora transportadora;
	
	@Autowired
	private ViewLogin viewLogin;

	public void pegarTransportadora(Transportadora transportadora) {
		Preconditions.checkNotNull(transportadora, "A transportadora não pode ser nula");
		this.nomeTransportadora = transportadora.getNome().toUpperCase();
		this.transportadora = transportadora;
		setTitle(nomeTransportadora);
				
	}
	
	public ViewEntregas() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnCadastroEntregas = new JButton("Cadastrar Entregas");
		btnCadastroEntregas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewCadastroEntregas.pegarTransportadora(transportadora);
				//viewCadastroEntregas.setVisible(true);
				dispose();
			}
		});
		btnCadastroEntregas.setBounds(10, 227, 168, 23);
		contentPane.add(btnCadastroEntregas);
		
		JButton btnSair = new JButton("Logout");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewLogin.setVisible(true);
				dispose();
			}
		});
		btnSair.setBounds(345, 0, 89, 23);
		contentPane.add(btnSair);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 37, 414, 158);
		contentPane.add(panel);
	}
}
