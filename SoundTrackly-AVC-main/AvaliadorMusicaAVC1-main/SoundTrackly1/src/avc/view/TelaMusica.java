package avc.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import avc.model.Musica;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaMusica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeMusica;
	private JTextField txtArtista;
	private JTextField txtComentario;
	private JSpinner spEstrela;
	private JComboBox<String> comboGenero;
	private TelaInicial telaPai;
	private int indexEdicao = -1;

	public TelaMusica(TelaInicial pai, Musica musicaParaEditar, int index) {
		this.telaPai = pai;
		this.indexEdicao = index;

		setTitle(musicaParaEditar == null ? "Avaliar Música" : "Editar Avaliação");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 589, 442);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(18, 18, 18)); // Fundo preto no painel base
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelPrincipal = new JPanel();
		// COR DO FUNDO ALTERADA PARA PRETO (18, 18, 18)
		panelPrincipal.setBackground(new Color(18, 18, 18));
		panelPrincipal.setBorder(new MatteBorder(12, 1, 1, 1, new Color(106, 100, 250)));
		panelPrincipal.setBounds(0, 0, 573, 403);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Sua Opinião sobre a Música");
		lblTitulo.setForeground(new Color(106, 100, 250)); // Título mantido na cor roxa
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
		lblTitulo.setBounds(118, 21, 346, 40);
		panelPrincipal.add(lblTitulo);
		
		JLabel lblNome = new JLabel("Nome da Música:");
		lblNome.setForeground(Color.WHITE); // Texto em branco para constrastar
		lblNome.setBounds(54, 72, 150, 16);
		panelPrincipal.add(lblNome);
		
		txtNomeMusica = new JTextField();
		txtNomeMusica.setBounds(54, 87, 209, 29);
		panelPrincipal.add(txtNomeMusica);
		
		JLabel lblArtista = new JLabel("Artista:");
		lblArtista.setForeground(Color.WHITE); // Texto em branco
		lblArtista.setBounds(285, 72, 150, 16);
		panelPrincipal.add(lblArtista);
		
		txtArtista = new JTextField();
		txtArtista.setBounds(285, 87, 209, 29);
		panelPrincipal.add(txtArtista);
		
		JLabel lblGenero = new JLabel("Gênero:");
		lblGenero.setForeground(Color.WHITE); // Texto em branco
		lblGenero.setBounds(285, 120, 150, 16);
		panelPrincipal.add(lblGenero);
		
		JLabel lblEstrelas = new JLabel("Avaliação (0-5):");
		lblEstrelas.setForeground(Color.WHITE); // Texto em branco
		lblEstrelas.setBounds(54, 120, 150, 16);
		panelPrincipal.add(lblEstrelas);
		
		spEstrela = new JSpinner();
		spEstrela.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spEstrela.setBounds(54, 135, 50, 28);
		panelPrincipal.add(spEstrela);
		
		JLabel lblComent = new JLabel("Escreva seu Comentário:");
		lblComent.setForeground(Color.WHITE); // Texto em branco
		lblComent.setBounds(54, 190, 200, 16);
		panelPrincipal.add(lblComent);
		
		txtComentario = new JTextField();
		txtComentario.setBounds(54, 210, 440, 80);
		panelPrincipal.add(txtComentario);
		
		comboGenero = new JComboBox<>();
		comboGenero.setModel(new DefaultComboBoxModel<>(new String[] {"Pop", "Rock", "Funk", "Trap", "Eletrônica", "Rap", "Pagode", "Samba", "MPB", "Sertanejo", "Forró", "Gospel", "Clássica", "Jazz", "Reggae", "K-Pop", "Metal"}));
		comboGenero.setBounds(286, 139, 209, 22);
		panelPrincipal.add(comboGenero);

		// PREENCHER CAMPOS SE FOR EDIÇÃO
		if (musicaParaEditar != null) {
			txtNomeMusica.setText(musicaParaEditar.getNome());
			txtArtista.setText(musicaParaEditar.getArtista());
			txtComentario.setText(musicaParaEditar.getComentario());
			spEstrela.setValue(musicaParaEditar.getEstrelas());
			comboGenero.setSelectedItem(musicaParaEditar.getGenero());
		}
		
		JButton btnPublicar = new JButton(musicaParaEditar == null ? "Publicar" : "Salvar Alterações");
		btnPublicar.setFont(new Font("Gadugi", Font.BOLD, 14));
		btnPublicar.setForeground(Color.WHITE);
		btnPublicar.setBackground(new Color(106, 100, 250));
		btnPublicar.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Adicionei a mãozinha também!
		btnPublicar.setBounds(311, 316, 183, 40);
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = txtNomeMusica.getText();
				String artista = txtArtista.getText();
				String genero = comboGenero.getSelectedItem().toString();
				String comentario = txtComentario.getText();
				int estrelas = (int) spEstrela.getValue();

				Musica m = new Musica(nome, artista, genero, comentario, estrelas);
				
				if (indexEdicao == -1) {
					telaPai.adicionarMusica(m);
				} else {
					telaPai.editarMusica(m, indexEdicao);
				}
				dispose();
			}
		});
		panelPrincipal.add(btnPublicar);
	}
}