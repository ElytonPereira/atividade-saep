package br.com.senai.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import br.com.senai.entity.Entrega;
import br.com.senai.entity.Motorista;
import br.com.senai.entity.Transportadora;
import br.com.senai.service.MotoristaService;

@Component
@Lazy
public class ViewCadastroEntregas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtDescricao;
	private JComboBox<Motorista> cbMotoristas;
	private  String nomeTransportadora; 	
		
	@Autowired
	private Transportadora transportadora;
	
	@Autowired
	private MotoristaService motoristaService;
	
	@Autowired
	private ViewLogin viewLogin;
	
	private Integer idTransportadora;
	
	public void pegarTransportadora(Transportadora transportadora) {
		Preconditions.checkNotNull(transportadora, "A transportadora não pode ser nula");
		this.nomeTransportadora = transportadora.getNome().toUpperCase();
		this.idTransportadora = transportadora.getId();
		this.transportadora = transportadora;
		setTitle(nomeTransportadora);		
		
	}	
	
	public void carregarCombo() {
		
		List<Motorista> motoristas = motoristaService.listarPor(idTransportadora);
		for (Motorista motorista : motoristas) {
			cbMotoristas.addItem(motorista);
		}
	}
	
	public ViewCadastroEntregas() {
		setResizable(false);
		System.out.println("Service motorista entrega: " + motoristaService);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel txtMotoristas = new JLabel("Motoristas");
		txtMotoristas.setBounds(10, 62, 68, 14);
		contentPane.add(txtMotoristas);
		
		JComboBox cbMotoristas = new JComboBox<Motorista>();
		cbMotoristas.setBounds(78, 58, 277, 22);
		contentPane.add(cbMotoristas);
		
		edtDescricao = new JTextField();
		edtDescricao.setBounds(78, 132, 277, 20);
		contentPane.add(edtDescricao);
		edtDescricao.setColumns(10);
		
		JLabel txtDescricao = new JLabel("Descrição");
		txtDescricao.setBounds(10, 135, 59, 14);
		contentPane.add(txtDescricao);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Motorista motorista = (Motorista) cbMotoristas.getSelectedItem();
					String descricao = edtDescricao.getText();
					
					Entrega entrega = new Entrega();
					
					if (entrega != null) {
						entrega.setDescricao(descricao);
						entrega.setMotorista(motorista);
						
						motoristaService.salvar(motorista);
						
						JOptionPane.showInternalMessageDialog(null, "Entrega salva com sucesso!");
						edtDescricao.setText("");
						
					}
					
					
				} catch (Exception e2) {
					JOptionPane.showInternalMessageDialog(null, "Erro ao tentar salvar a Entrega");
				}
				
			}
		});
		btnInserir.setBounds(172, 170, 89, 23);
		contentPane.add(btnInserir);
		
		JButton btnSair = new JButton("Logout");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewLogin.setVisible(true);
				dispose();
			}
		});
		btnSair.setBounds(345, 0, 89, 23);
		contentPane.add(btnSair);
		
		this.carregarCombo();
		
	}
}
