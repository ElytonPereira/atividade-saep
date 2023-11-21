package br.com.senai.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import br.com.senai.entity.Motorista;
import br.com.senai.entity.Transportadora;
import br.com.senai.service.MotoristaService;

@Component
@Lazy
public class ViewCadastroMotorista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtNome;
	private JTextField edtCnh;	
	
	@Autowired
	private Transportadora transportadora;
	
	private String nomeTransportadora;
	
	@Autowired
	private MotoristaService service;

	public void pegarTransportadora(Transportadora transportadora) {
		Preconditions.checkNotNull(transportadora, "A transportadora não pode ser nula");
		this.nomeTransportadora = transportadora.getNome().toUpperCase();
		this.transportadora = transportadora;
		setTitle(nomeTransportadora);				
	}
	
	public ViewCadastroMotorista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txtNome = new JLabel("Nome:");
		txtNome.setBounds(65, 76, 46, 14);
		contentPane.add(txtNome);
		
		edtNome = new JTextField();
		edtNome.setBounds(121, 73, 252, 20);
		contentPane.add(edtNome);
		edtNome.setColumns(10);
		
		JLabel txtCnh = new JLabel("CNH:");
		txtCnh.setBounds(65, 119, 46, 14);
		contentPane.add(txtCnh);
		
		edtCnh = new JTextField();
		edtCnh.setColumns(10);
		edtCnh.setBounds(121, 116, 252, 20);
		contentPane.add(edtCnh);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				try {
					String nome = edtNome.getText();
					Integer cnh = Integer.parseInt(edtCnh.getText());
					
					Motorista motorista = new Motorista();
					
					if (motorista != null) {
						motorista.setNome(nome);
						motorista.setCnh(cnh);
						
						motorista.setTransportadora(transportadora);
												
						motorista = service.salvar(motorista);
						JOptionPane.showInternalMessageDialog(null, "Motorista salvo com sucesso!");
						edtNome.setText("");
						edtCnh.setText("");
					}					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
			}
		});
		btnInserir.setBounds(172, 173, 89, 23);
		contentPane.add(btnInserir);
		
		JLabel txtTitulo = new JLabel("CADASTRO DE MOTORISTA");
		txtTitulo.setBounds(131, 11, 172, 14);
		contentPane.add(txtTitulo);
	}
}
