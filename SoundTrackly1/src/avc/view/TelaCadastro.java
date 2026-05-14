package avc.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import avc.data.RepositorioCadastro;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;

public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIdade;
	private JTextField txtNomeUsuario;
	private JTable table;
	private JTextField txtEmail;
	private JPasswordField txtSenha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});

	};

	public TelaCadastro() {
		setResizable(false);
		setBackground(new Color(106, 100, 250));
		setAutoRequestFocus(false);
		setBounds(100, 100, 558, 438);
		contentPane = new JPanel();
		
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 542, 399);
		panel.setToolTipText("");
		panel.setBorder(new MatteBorder(12, 1, 1, 1, (Color) new Color(106, 100, 250)));
		panel.setForeground(new Color(106, 100, 250));
		panel.setBackground(new Color(18, 18, 18));
		panel.setLayout(null);
		contentPane.add(panel);
		
		
		JLabel lblEmailRegistrar_1_1 = new JLabel("Crie sua Conta");
		lblEmailRegistrar_1_1.setForeground(new Color(106, 100, 250));
		lblEmailRegistrar_1_1.setBackground(new Color(106, 100, 250));
		lblEmailRegistrar_1_1.setBounds(192, 21, 178, 40);
		panel.add(lblEmailRegistrar_1_1);
		lblEmailRegistrar_1_1.setFont(new Font("Gadugi", Font.BOLD, 25));
		
		JLabel lblNomeUsuario = new JLabel("Nome do Usuario : ");
		lblNomeUsuario.setForeground(new Color(255, 255, 255));
		lblNomeUsuario.setBounds(80, 114, 158, 16);
		panel.add(lblNomeUsuario);
		lblNomeUsuario.setFont(new Font("Gadugi", Font.PLAIN, 12));
		
		txtNomeUsuario = new JTextField();
		txtNomeUsuario.setBackground(new Color(255, 255, 255));
		txtNomeUsuario.setBounds(80, 129, 158, 22);
		txtNomeUsuario.setFont(new Font("Gadugi", Font.PLAIN, 12));
		panel.add(txtNomeUsuario);
		txtNomeUsuario.setColumns(10);
		
		JLabel lblIdade = new JLabel("Idade : ");
		lblIdade.setForeground(new Color(255, 255, 255));
		lblIdade.setBackground(new Color(106, 100, 250));
		lblIdade.setBounds(80, 162, 158, 16);
		panel.add(lblIdade);
		lblIdade.setFont(new Font("Gadugi", Font.PLAIN, 12));
		
		txtIdade = new JTextField();
		txtIdade = new JTextField();
		txtIdade.setBounds(80, 177, 158, 22);
		txtIdade.setFont(new Font("Gadugi", Font.PLAIN, 12));

		txtIdade.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();

		        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
		            e.consume();
		        }
		    }
		});

		panel.add(txtIdade);
		txtIdade.setColumns(10);
		panel.add(txtIdade);
		txtIdade.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(80, 210, 158, 16);
		lblEmail.setFont(new Font("Gadugi", Font.PLAIN, 12));
		panel.add(lblEmail);
		
		JLabel lblSenha = new JLabel("Nova Senha:");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setBounds(80, 258, 158, 16);
		lblSenha.setFont(new Font("Gadugi", Font.PLAIN, 12));
		panel.add(lblSenha);
		
		JButton btnFazerLogin = new JButton("Fazer Login");
		btnFazerLogin.setBounds(397, 348, 135, 40);
		panel.add(btnFazerLogin);
		btnFazerLogin.setForeground(new Color(255, 255, 255));
		btnFazerLogin.setBackground(new Color(106, 100, 250));
		btnFazerLogin.setFont(new Font("Gadugi", Font.PLAIN, 15));
		
		JLabel lblFotoPerfil = new JLabel("Foto de Usuario");
		lblFotoPerfil.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFotoPerfil.setBackground(new Color(106, 100, 250));
		lblFotoPerfil.setForeground(new Color(255, 255, 255));
		lblFotoPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotoPerfil.setBounds(328, 163, 88, 104);
		panel.add(lblFotoPerfil);
		
		JButton btnFotoPerfil = new JButton("Procurar Arquivo");
		btnFotoPerfil.setForeground(new Color(255, 255, 255));
		btnFotoPerfil.setFont(new Font("Gadugi", Font.PLAIN, 12));
		btnFotoPerfil.setBackground(new Color(106, 100, 250));
		btnFotoPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int resultado = chooser.showOpenDialog(null);
				
				if (resultado == JFileChooser.APPROVE_OPTION) {
		            File arquivo = chooser.getSelectedFile();

		            ImageIcon imagem = new ImageIcon(arquivo.getAbsolutePath());
		            Image img = imagem.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);

		            lblFotoPerfil.setIcon(new ImageIcon(img));
		            }
				}
			}
		);
		btnFotoPerfil.setBounds(311, 271, 129, 23);
		panel.add(btnFotoPerfil);
		
		table = new JTable();
		table.setBounds(328, 142, 0, 43);
		panel.add(table);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Gadugi", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(80, 225, 158, 22);
		panel.add(txtEmail);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Gadugi", Font.PLAIN, 12));
		txtSenha.setColumns(10);
		txtSenha.setBounds(80, 276, 158, 22);
		panel.add(txtSenha);
		
		JButton btnSalvarDados = new JButton("Salvar Dados");
		btnSalvarDados.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String nome = txtNomeUsuario.getText();
				String idade = txtIdade.getText();
				String email = txtEmail.getText();
				String senha = new String(txtSenha.getPassword());
				
				boolean erro = false;
				  
				if (nome.isEmpty()) {
				    lblNomeUsuario.setText("!!! Nome obrigatório !!!");
				    lblNomeUsuario.setForeground(Color.RED);
				    erro = true;

				} else if (nome.length() < 3) {
				    lblNomeUsuario.setText("!!! Nome inválido !!!");
				    lblNomeUsuario.setForeground(Color.RED);
				    erro = true;

				} else {
				    lblNomeUsuario.setText("Nome do Usuario:");
				    lblNomeUsuario.setForeground(Color.BLACK);
				}
				
			    if (email.isEmpty()) {
			        lblEmail.setText("!!! Email obrigatório !!!");
			        lblEmail.setForeground(Color.RED);
			        erro = true;
			    } else if (!email.contains("@") || !email.contains(".")) {
			        lblEmail.setText("!!! Email inválido !!!");
			        lblEmail.setForeground(Color.RED);
			        erro = true;
			    } else {
			        lblEmail.setText("");
			    }

			    if (senha.isEmpty()) {
			        lblSenha.setText("!!! Senha obrigatória !!!");
			        lblSenha.setForeground(Color.RED);
			        erro = true;
			    } else if (senha.length() < 5) {
			        lblSenha.setText("!!! Mínimo 5 caracteres !!!");
			        lblSenha.setForeground(Color.RED);
			        erro = true;
			    } else {
			        lblSenha.setText("");
			    }
			    
			    if (idade.isEmpty()) {
			    	lblIdade.setText("!!! Idade obrigatória !!!");
			    	lblIdade.setForeground(Color.RED);
			    	erro = true;
			    	} else {
			    		try {
			    			int idadeNum = Integer.parseInt(idade);
				            if (idadeNum < 18) {
				                lblIdade.setText("!!! Maior de 18 Anos !!!");
				                lblIdade.setForeground(Color.RED);
				                erro = true;
				            } else {
				                lblIdade.setText("");
				            }

				        } catch (NumberFormatException error) {
				            lblIdade.setText("!!! Digite um número válido !!!");
				            lblIdade.setForeground(Color.RED);
				            erro = true;
				        }
			    		
				    if (erro) return;

				    RepositorioCadastro.salvarDados(nome, idade, email, senha);
				    
				    JOptionPane.showMessageDialog(null, "Cadastro salvo!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				    }
			}
			});
		btnSalvarDados.setForeground(Color.WHITE);
		btnSalvarDados.setFont(new Font("Gadugi", Font.PLAIN, 15));
		btnSalvarDados.setBackground(new Color(106, 100, 250));
		btnSalvarDados.setBounds(90, 309, 135, 40);
		panel.add(btnSalvarDados);
		
		btnFazerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaLogin tela = new TelaLogin();
				tela.dispose();
				tela.setVisible(true);
				}
			});
		}
	}