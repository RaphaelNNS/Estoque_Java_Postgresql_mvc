package telas;


import javax.swing.JFrame;

import dao.ClienteDaoDB;
import domain.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TelaCadastrar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1709472254445104887L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	ClienteDaoDB dao = new ClienteDaoDB();


	/**
	 * Create the application.
	 */
	public TelaCadastrar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Tela de cadastro de clientes");
		this.setBounds(100, 100, 450, 555);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(10, 11, 70, 29);
		this.getContentPane().add(lblNome);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 43, 416, 19);
		this.getContentPane().add(textField);
		
		JLabel lblEndereo = new JLabel("Endereço: ");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereo.setBounds(10, 72, 117, 29);
		this.getContentPane().add(lblEndereo);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 111, 416, 19);
		this.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 180, 416, 19);
		this.getContentPane().add(textField_2);
		
		JLabel lblCidade = new JLabel("Cidade: ");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCidade.setBounds(10, 141, 117, 29);
		this.getContentPane().add(lblCidade);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(10, 249, 416, 19);
		this.getContentPane().add(textField_3);
		
		JLabel lblEstado = new JLabel("Estado: ");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEstado.setBounds(10, 210, 117, 29);
		this.getContentPane().add(lblEstado);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 318, 416, 19);
		this.getContentPane().add(textField_4);
		
		JLabel lblTelefone = new JLabel("Telefone: ");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefone.setBounds(10, 279, 117, 29);
		this.getContentPane().add(lblTelefone);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(10, 387, 416, 19);
		this.getContentPane().add(textField_5);
		
		JLabel lblCpf = new JLabel("CPF: ");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCpf.setBounds(10, 348, 117, 29);
		this.getContentPane().add(lblCpf);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarCliente();
			}			
		});
		
		
		
		
		btnNewButton.setBounds(10, 417, 414, 29);
		this.getContentPane().add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirLogin();
			}
		});
		btnVoltar.setBounds(10, 457, 414, 29);
		this.getContentPane().add(btnVoltar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(10, 491, 414, 14);
		this.getContentPane().add(lblNewLabel);
	}
	
	private void abrirLogin() {
		 TelaLogin telaLogin = new TelaLogin();
         telaLogin.setVisible(true);
         setVisible(false);
	}
	
	
	private static void telaErro(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "erro", JOptionPane.ERROR_MESSAGE);
    }
	
	private void cadastrarCliente() {
		String nome =  textField.getText();
		String endereco =  textField_1.getText();
		String cidade =  textField_2.getText();
		String estado =  textField_3.getText();
		String telefone =  textField_4.getText();
		String cpf =  textField_1.getText();
		
		if(!(nome.isEmpty()) && !(endereco.isEmpty()) &&
				!(cidade.isEmpty()) && !(estado.isEmpty()) &&
				!(telefone.isEmpty()) &&!(cpf.isEmpty())) {
			Cliente novoCliente  = new Cliente(nome, endereco, cidade, estado, telefone, cpf);
			try {
				dao.cadastrar(novoCliente);
			} catch (Exception e) {
				telaErro("Erro ao cadastrar o cliente");
	            throw new RuntimeException(e);
			}
			
		}else {
			telaErro("Preencha todos os campos");
			
		}
	}
}
