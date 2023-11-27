package br.com.senai.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	
	private JTable tableMotoristas;
    private MotoristaTableModel motoristaTableModel;
    
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
        motoristaTableModel = new MotoristaTableModel();
        tableMotoristas = new JTable(motoristaTableModel);
        JScrollPane scrollPane = new JScrollPane(tableMotoristas);
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
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Integer linhaSelecionada = tableMotoristas.getSelectedRow();
				MotoristaTableModel model = (MotoristaTableModel) tableMotoristas.getModel();		
				
				if (linhaSelecionada >=0 && !model.isVazio() && !model.isLinhaInvalida(linhaSelecionada)) {

					int op = JOptionPane.showConfirmDialog(contentPane, 
							"Deseja realmente remover?", 
							"Remoção", JOptionPane.YES_NO_OPTION);
					
					if (op == 0) {
						Motorista motoristaSelecionado = model.getPor(linhaSelecionada);
						
						try {
							model.removerPor(linhaSelecionada);
							motoristaService.excluirPor(motoristaSelecionado.getId());
							tableMotoristas.updateUI();
							JOptionPane.showMessageDialog(contentPane, "Motorista excluido com sucesso");
							
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(contentPane, "Algo de errado ao tentar excluir o motorista. " + e2.getMessage());
						}
					}
		
				} else {
					JOptionPane.showMessageDialog(contentPane, "Selecione apenas uma linha para remocao");
				}        		
        	}
        });
        btnExcluir.setBounds(434, 35, 89, 23);
        contentPane.add(btnExcluir);
    }

    private void atualizarTabela() {
        // Aqui você deve obter os dados reais dos motoristas da sua aplicação, seja do banco de dados, serviço, etc.
        // Por enquanto, vou criar dados fictícios para exemplificar
        motoristaTableModel = new MotoristaTableModel(obterDadosFicticios());
        tableMotoristas.setModel(motoristaTableModel);
    }

    private List<Motorista> obterDadosFicticios() {
        // Crie seus próprios dados fictícios ou substitua esta lógica com a obtenção real dos dados
        List<Motorista> motoristas = motoristaService.listarPor(transportadora.getId());        

        return motoristas;
    }
}

