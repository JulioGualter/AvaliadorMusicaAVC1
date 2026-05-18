package avc.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TelaLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUser;
    private JPasswordField txtSenha;

    private boolean verificarLogin(String usuario, String senha) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("SoundTrackly1\\src\\usuarios.txt"));
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                if (dados.length >= 4) {
                    if (dados[0].equals(usuario) && dados[3].equals(senha)) {
                        br.close();
                        return true;
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaLogin frame = new TelaLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void abrirCadastro() {
        TelaCadastro tela = new TelaCadastro();
        tela.setVisible(true);
        this.dispose();
    }

    private void abrirTelaInicial() {
        TelaInicial tela = new TelaInicial();
        tela.setVisible(true);
        this.dispose();
    }

    public TelaLogin() {
		setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        
        // COMANDO PARA DEIXAR EM TELA CHEIA RESPONSIVA
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // --- PAINEL PRINCIPAL (FUNDO PRETO) ---
        contentPane = new JPanel();
        contentPane.setBackground(new Color(18, 18, 18));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        // Define o layout principal para centralizar o conteúdo interno
        contentPane.setLayout(new GridBagLayout()); 

        // --- PAINEL CENTRAL (AGRUPA O FORMULÁRIO E A LOGO) ---
        JPanel panelCentral = new JPanel();
        panelCentral.setOpaque(false); // Deixa transparente para mostrar o fundo preto
        panelCentral.setLayout(new GridBagLayout());

        // Posiciona o painel central bem no meio do painel principal
        GridBagConstraints gbc_centro = new GridBagConstraints();
        gbc_centro.gridx = 0;
        gbc_centro.gridy = 0;
        gbc_centro.weightx = 1.0;
        gbc_centro.weighty = 1.0;
        gbc_centro.anchor = GridBagConstraints.CENTER;
        contentPane.add(panelCentral, gbc_centro);

        // ==========================================================
        // ADICIONANDO COMPONENTES DENTRO DO PAINEL CENTRAL
        // ==========================================================

        // --- LOGO PRINCIPAL ---
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon("SoundTrackly1\\Imagens\\imagem.png"));
        GridBagConstraints gbc_lblLogo = new GridBagConstraints();
        gbc_lblLogo.gridheight = 6; // Faz a imagem ocupar a altura de todas as linhas do formulário
        gbc_lblLogo.gridx = 0;
        gbc_lblLogo.gridy = 0;
        gbc_lblLogo.insets = new Insets(0, 0, 0, 40); // Dá um respiro de 40px entre a logo e os inputs
        gbc_lblLogo.anchor = GridBagConstraints.CENTER;
        panelCentral.add(lblLogo, gbc_lblLogo);

        // --- TÍTULO ---
        JLabel lblFacaLogin = new JLabel("Faça seu Login");
        lblFacaLogin.setForeground(new Color(106, 100, 250));
        lblFacaLogin.setFont(new Font("Arial", Font.BOLD, 30));
        GridBagConstraints gbc_lblFacaLogin = new GridBagConstraints();
        gbc_lblFacaLogin.gridwidth = 2; // Ocupa a coluna do ícone e a coluna do input
        gbc_lblFacaLogin.gridx = 1;
        gbc_lblFacaLogin.gridy = 0;
        gbc_lblFacaLogin.insets = new Insets(0, 0, 30, 0); // Espaço em baixo do título
        gbc_lblFacaLogin.anchor = GridBagConstraints.CENTER;
        panelCentral.add(lblFacaLogin, gbc_lblFacaLogin);

        // --- ÍCONE USUÁRIO ---
        JLabel lblImgUser = new JLabel("");
        lblImgUser.setIcon(new ImageIcon("SoundTrackly1\\Imagens\\icons8-usuário-homem-com-círculo-28.png"));
        GridBagConstraints gbc_lblImgUser = new GridBagConstraints();
        gbc_lblImgUser.gridx = 1;
        gbc_lblImgUser.gridy = 1;
        gbc_lblImgUser.insets = new Insets(0, 0, 15, 10);
        gbc_lblImgUser.anchor = GridBagConstraints.EAST; // Alinha o ícone à direita, colado no input
        panelCentral.add(lblImgUser, gbc_lblImgUser);

        // --- CAMPO USUÁRIO ---
        txtUser = new JTextField();
        txtUser.setColumns(20);
        GridBagConstraints gbc_txtUser = new GridBagConstraints();
        gbc_txtUser.gridx = 2;
        gbc_txtUser.gridy = 1;
        gbc_txtUser.insets = new Insets(0, 0, 15, 0);
        gbc_txtUser.fill = GridBagConstraints.HORIZONTAL;
        panelCentral.add(txtUser, gbc_txtUser);

        // --- ÍCONE SENHA ---
        JLabel lblImgSenha = new JLabel("");
        lblImgSenha.setIcon(new ImageIcon("SoundTrackly1\\Imagens\\icons8-password-28.png"));
        GridBagConstraints gbc_lblImgSenha = new GridBagConstraints();
        gbc_lblImgSenha.gridx = 1;
        gbc_lblImgSenha.gridy = 2;
        gbc_lblImgSenha.insets = new Insets(0, 0, 20, 10);
        gbc_lblImgSenha.anchor = GridBagConstraints.EAST;
        panelCentral.add(lblImgSenha, gbc_lblImgSenha);

        // --- CAMPO SENHA ---
        txtSenha = new JPasswordField();
        txtSenha.setColumns(20);
        GridBagConstraints gbc_txtSenha = new GridBagConstraints();
        gbc_txtSenha.gridx = 2;
        gbc_txtSenha.gridy = 2;
        gbc_txtSenha.insets = new Insets(0, 0, 20, 0);
        gbc_txtSenha.fill = GridBagConstraints.HORIZONTAL;
        panelCentral.add(txtSenha, gbc_txtSenha);

        // --- BOTÃO LOGIN ---
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUser.getText();
                String senha = new String(txtSenha.getPassword());

                if (verificarLogin(usuario, senha)) {
                    abrirTelaInicial();
                } else {
                    JOptionPane.showMessageDialog(null, "Login inválido!");
                }
            }
        });
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 15));
        btnLogin.setBackground(new Color(106, 100, 250));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mãozinha ao passar por cima
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.gridwidth = 2;
        gbc_btnLogin.gridx = 1;
        gbc_btnLogin.gridy = 3;
        gbc_btnLogin.insets = new Insets(0, 0, 20, 0);
        gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnLogin.ipady = 10; // Deixa o botão um pouco mais alto
        panelCentral.add(btnLogin, gbc_btnLogin);

        // --- TEXTO "NÃO TEM CONTA?" ---
        JLabel lblCadastro = new JLabel("Não tem conta?");
        lblCadastro.setForeground(Color.WHITE);
        lblCadastro.setFont(new Font("Arial", Font.PLAIN, 13));
        GridBagConstraints gbc_lblCadastro = new GridBagConstraints();
        gbc_lblCadastro.gridx = 1;
        gbc_lblCadastro.gridy = 4;
        gbc_lblCadastro.anchor = GridBagConstraints.EAST;
        gbc_lblCadastro.insets = new Insets(0, 0, 0, 5);
        panelCentral.add(lblCadastro, gbc_lblCadastro);

        // --- LINK CADASTRE-SE ---
        JLabel lblCadastroLink = new JLabel("Cadastre-se");
        lblCadastroLink.setForeground(new Color(106, 100, 250));
        lblCadastroLink.setFont(new Font("Arial", Font.BOLD, 13));
        lblCadastroLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        GridBagConstraints gbc_lblCadastroLink = new GridBagConstraints();
        gbc_lblCadastroLink.gridx = 2;
        gbc_lblCadastroLink.gridy = 4;
        gbc_lblCadastroLink.anchor = GridBagConstraints.WEST;
        panelCentral.add(lblCadastroLink, gbc_lblCadastroLink);
        
        lblCadastroLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirCadastro();
            }
        });
    }
}