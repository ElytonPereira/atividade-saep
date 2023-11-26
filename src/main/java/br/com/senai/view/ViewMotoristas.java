package br.com.senai.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import br.com.senai.entity.Motorista;
import br.com.senai.entity.Transportadora;
import br.com.senai.service.MotoristaService;
import br.com.senai.view.componentes.MotoristaTableModel;

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

	@Autowired
	private ViewLogin viewLogin;
	
	private JTable tabelaMotoristas;
    private MotoristaTableModel modeloTabela;
    
    @Autowired
    private MotoristaService motoristaService;
	
	public void pegarTransportadora(Transportadora transportadora) {
		Preconditions.checkNotNull(transportadora, "A transportadora não pode ser nula");
		this.nomeTransportadora = transportadora.getNome().toUpperCase();
		this.transportadora = transportadora;
		setTitle(nomeTransportadora);		
		setVisible(true);
		atualizarTabela();
		
	}

    public ViewMotoristas() {
        setResizable(false);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 570, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        // Configuração da tabela
        modeloTabela = new MotoristaTableModel();
        tabelaMotoristas = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaMotoristas);
        scrollPane.setBounds(10, 32, 414, 180);
        contentPane.add(scrollPane);

        JButton btnCadastrarMotorista = new JButton("Cadastrar Motorista");
        btnCadastrarMotorista.setBounds(10, 227, 157, 23);
        btnCadastrarMotorista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewCadastroMotorista.pegarTransportadora(transportadora);
                viewCadastroMotorista.setVisible(true);
                dispose();
            }
        });
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        contentPane.add(btnCadastrarMotorista);

        JButton btnSair = new JButton("Logout");
        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewLogin.setVisible(true);
                dispose();
            }
        });
        btnSair.setBounds(430, 0, 89, 23);
        contentPane.add(btnSair);
    }

    private void atualizarTabela() {
        // Aqui você deve obter os dados reais dos motoristas da sua aplicação, seja do banco de dados, serviço, etc.
        // Por enquanto, vou criar dados fictícios para exemplificar
        modeloTabela = new MotoristaTableModel(obterDadosFicticios());
        tabelaMotoristas.setModel(modeloTabela);
    }

    private List<Motorista> obterDadosFicticios() {
        // Crie seus próprios dados fictícios ou substitua esta lógica com a obtenção real dos dados
        List<Motorista> motoristas = motoristaService.listarPor(transportadora.getId());        

        return motoristas;
    }
}

