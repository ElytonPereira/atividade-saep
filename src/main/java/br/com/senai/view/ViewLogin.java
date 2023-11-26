package br.com.senai.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.com.senai.entity.Usuario;
import br.com.senai.service.UsuarioService;

@Component
@Lazy
public class ViewLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtNome;
	private JPasswordField edtSenha;
	private JButton btnEntrar;
	
	@Autowired
	private UsuarioService service;	
	
	@Autowired
	private ViewPrincipal viewPrincipal;
		
	public ViewLogin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		edtNome = new JTextField();
		edtNome.setBounds(165, 81, 92, 20);
		contentPane.add(edtNome);
		edtNome.setColumns(10);
		
		edtSenha = new JPasswordField();
		edtSenha.setBounds(165, 150, 92, 20);
		contentPane.add(edtSenha);
		
		JLabel txtNome = new JLabel("Nome:");
		txtNome.setBounds(109, 84, 46, 14);
		contentPane.add(txtNome);
		
		JLabel txtSenha = new JLabel("Senha:");
		txtSenha.setBounds(109, 153, 46, 14);
		contentPane.add(txtSenha);
		
		JLabel txtLogin = new JLabel("LOGIN");
		txtLogin.setBounds(192, 11, 46, 14);
		contentPane.add(txtLogin);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String nome = edtNome.getText();
					String senha =new String(edtSenha.getPassword());
					
					Usuario usuarioEncontrado = service.buscarUsuarioPor(nome, senha);
					
					if (usuarioEncontrado != null) {
						JOptionPane.showMessageDialog(null, "Bem vindo: " + usuarioEncontrado.getNome());
						viewPrincipal.pegarTransportadora(usuarioEncontrado.getTransportadora());						
						dispose();
						
					}else {
						JOptionPane.showMessageDialog(null, "Usuario nao encontrado ou informacoes incorretas");
					
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Usuario nao encontrado ou informacoes incorretas");
				}
			}
		});
		btnEntrar.setBounds(168, 205, 89, 23);
		contentPane.add(btnEntrar);
	}
}
