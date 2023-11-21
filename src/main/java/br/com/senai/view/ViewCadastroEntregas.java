package br.com.senai.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ViewCadastroEntregas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtDescricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCadastroEntregas frame = new ViewCadastroEntregas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewCadastroEntregas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txtMotoristas = new JLabel("Motoristas");
		txtMotoristas.setBounds(10, 35, 68, 14);
		contentPane.add(txtMotoristas);
		
		JComboBox cbMotoristas = new JComboBox();
		cbMotoristas.setBounds(78, 31, 277, 22);
		contentPane.add(cbMotoristas);
		
		edtDescricao = new JTextField();
		edtDescricao.setBounds(78, 105, 277, 20);
		contentPane.add(edtDescricao);
		edtDescricao.setColumns(10);
		
		JLabel txtDescricao = new JLabel("Descrição");
		txtDescricao.setBounds(10, 108, 46, 14);
		contentPane.add(txtDescricao);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setBounds(172, 170, 89, 23);
		contentPane.add(btnInserir);
	}
}
