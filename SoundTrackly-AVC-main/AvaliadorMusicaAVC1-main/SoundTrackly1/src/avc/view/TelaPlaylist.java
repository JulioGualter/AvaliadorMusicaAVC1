package avc.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import avc.model.Musica;
import avc.model.Playlist;
import java.awt.*;
import java.util.ArrayList;

// Tela para criar uma nova playlist e, em seguida, avaliá-la
public class TelaPlaylist extends JFrame {

    private static final long serialVersionUID = 1L;

    // Referência à tela principal para avisar quando a playlist for criada
    private TelaInicial telaPai;

    // Lista de músicas disponíveis para adicionar à playlist
    private ArrayList<Musica> musicasDisponiveis;

    // Campo onde o usuário digita o nome da playlist
    private JTextField txtNomePlaylist;

    // Lista visual com checkboxes para selecionar as músicas
    private JPanel panelCheckboxes;

    // Guarda os checkboxes para saber quais foram marcados
    private ArrayList<JCheckBox> checkboxes = new ArrayList<>();

    // Playlist que está sendo montada
    private Playlist playlistAtual;

    // Painel de avaliação (aparece depois que a playlist é criada)
    private JPanel panelAvaliacao;

    // Spinner para escolher a nota da playlist (0 a 5)
    private JSpinner spEstrelas;

    // Campo para digitar o comentário sobre a playlist
    private JTextField txtComentario;

    // Construtor: recebe a tela pai e a lista de músicas já avaliadas
    public TelaPlaylist(TelaInicial pai, ArrayList<Musica> musicasDisponiveis) {
        this.telaPai = pai;
        this.musicasDisponiveis = musicasDisponiveis;

        // Configurações básicas da janela
        setTitle("Criar Nova Playlist");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(150, 100, 620, 600);
        setResizable(false);

        // Painel principal com fundo escuro (mesmo padrão do app)
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(18, 18, 18));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(0, 10));
        setContentPane(contentPane);

        // ── SEÇÃO SUPERIOR: nome da playlist ──────────────────────────
        JPanel panelTopo = new JPanel();
        panelTopo.setBackground(new Color(25, 25, 25));
        panelTopo.setBorder(new MatteBorder(0, 0, 2, 0, new Color(106, 100, 250)));
        panelTopo.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));

        JLabel lblTitulo = new JLabel("🎶 Nova Playlist");
        lblTitulo.setForeground(new Color(106, 100, 250));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        panelTopo.add(lblTitulo);

        contentPane.add(panelTopo, BorderLayout.NORTH);

        // ── SEÇÃO CENTRAL: nome + lista de músicas ───────────────────
        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(new Color(18, 18, 18));
        panelCentro.setLayout(new BorderLayout(0, 10));

        // Campo de nome da playlist
        JPanel panelNome = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelNome.setBackground(new Color(18, 18, 18));

        JLabel lblNome = new JLabel("Nome da Playlist:");
        lblNome.setForeground(Color.WHITE);
        lblNome.setFont(new Font("Arial", Font.PLAIN, 14));
        panelNome.add(lblNome);

        txtNomePlaylist = new JTextField(28);
        txtNomePlaylist.setFont(new Font("Arial", Font.PLAIN, 14));
        panelNome.add(txtNomePlaylist);

        panelCentro.add(panelNome, BorderLayout.NORTH);

        // Rótulo acima da lista de músicas
        JLabel lblEscolha = new JLabel("  Selecione as músicas para adicionar:");
        lblEscolha.setForeground(new Color(180, 180, 180));
        lblEscolha.setFont(new Font("Arial", Font.PLAIN, 13));

        // Painel com os checkboxes de músicas (com scroll se houver muitas)
        panelCheckboxes = new JPanel();
        panelCheckboxes.setBackground(new Color(30, 30, 30));
        panelCheckboxes.setLayout(new BoxLayout(panelCheckboxes, BoxLayout.Y_AXIS));

        // Verifica se há músicas disponíveis antes de montar a lista
        if (musicasDisponiveis.isEmpty()) {
            JLabel lblVazio = new JLabel("  Nenhuma música avaliada ainda.");
            lblVazio.setForeground(Color.GRAY);
            lblVazio.setFont(new Font("Arial", Font.ITALIC, 13));
            panelCheckboxes.add(lblVazio);
        } else {
            // Cria um checkbox para cada música disponível
            for (Musica m : musicasDisponiveis) {
                JCheckBox chk = new JCheckBox(m.getNome() + " - " + m.getArtista() + "  [" + m.getEstrelas() + "★]");
                chk.setBackground(new Color(30, 30, 30));
                chk.setForeground(Color.WHITE);
                chk.setFont(new Font("Arial", Font.PLAIN, 13));
                chk.setBorder(new EmptyBorder(4, 8, 4, 8));
                panelCheckboxes.add(chk);
                checkboxes.add(chk); // guarda para consultar depois
            }
        }

        // Scroll para a lista de músicas
        JScrollPane scroll = new JScrollPane(panelCheckboxes);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50)));
        scroll.getVerticalScrollBar().setUnitIncrement(12);

        // Agrupa rótulo + scroll no centro
        JPanel panelLista = new JPanel(new BorderLayout(0, 4));
        panelLista.setBackground(new Color(18, 18, 18));
        panelLista.add(lblEscolha, BorderLayout.NORTH);
        panelLista.add(scroll, BorderLayout.CENTER);

        panelCentro.add(panelLista, BorderLayout.CENTER);
        contentPane.add(panelCentro, BorderLayout.CENTER);

        // ── SEÇÃO INFERIOR: botão criar + painel de avaliação ─────────
        JPanel panelRodape = new JPanel();
        panelRodape.setBackground(new Color(18, 18, 18));
        panelRodape.setLayout(new BorderLayout(0, 10));

        // Botão que confirma a criação da playlist
        JButton btnCriar = new JButton("✔ Criar Playlist");
        btnCriar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCriar.setForeground(Color.WHITE);
        btnCriar.setBackground(new Color(106, 100, 250));
        btnCriar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCriar.setFocusPainted(false);
        btnCriar.setBorderPainted(false);
        btnCriar.setOpaque(true);
        btnCriar.setPreferredSize(new Dimension(160, 40));

        // Ação do botão "Criar Playlist"
        btnCriar.addActionListener(e -> criarPlaylist(btnCriar));

        JPanel panelBtnCriar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBtnCriar.setBackground(new Color(18, 18, 18));
        panelBtnCriar.add(btnCriar);
        panelRodape.add(panelBtnCriar, BorderLayout.NORTH);

        // Painel de avaliação (fica oculto até a playlist ser criada)
        panelAvaliacao = new JPanel();
        panelAvaliacao.setBackground(new Color(30, 30, 30));
        panelAvaliacao.setBorder(BorderFactory.createCompoundBorder(
            new MatteBorder(2, 0, 0, 0, new Color(106, 100, 250)),
            new EmptyBorder(10, 15, 10, 15)
        ));
        panelAvaliacao.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 8));
        panelAvaliacao.setVisible(false); // escondido no início

        JLabel lblAvaliar = new JLabel("Avaliar Playlist —");
        lblAvaliar.setForeground(new Color(106, 100, 250));
        lblAvaliar.setFont(new Font("Arial", Font.BOLD, 14));
        panelAvaliacao.add(lblAvaliar);

        // Spinner de estrelas (0 a 5)
        JLabel lblEstr = new JLabel("Nota (0-5):");
        lblEstr.setForeground(Color.WHITE);
        panelAvaliacao.add(lblEstr);

        spEstrelas = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
        spEstrelas.setPreferredSize(new Dimension(55, 28));
        panelAvaliacao.add(spEstrelas);

        // Campo de comentário
        JLabel lblComent = new JLabel("Comentário:");
        lblComent.setForeground(Color.WHITE);
        panelAvaliacao.add(lblComent);

        txtComentario = new JTextField(20);
        txtComentario.setFont(new Font("Arial", Font.PLAIN, 13));
        panelAvaliacao.add(txtComentario);

        // Botão para confirmar a avaliação
        JButton btnAvaliar = new JButton("★ Avaliar");
        btnAvaliar.setFont(new Font("Arial", Font.BOLD, 13));
        btnAvaliar.setForeground(Color.WHITE);
        btnAvaliar.setBackground(new Color(230, 160, 30)); // cor dourada para avaliação
        btnAvaliar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAvaliar.setFocusPainted(false);
        btnAvaliar.setBorderPainted(false);
        btnAvaliar.setOpaque(true);
        btnAvaliar.addActionListener(e -> avaliarPlaylist());
        panelAvaliacao.add(btnAvaliar);

        panelRodape.add(panelAvaliacao, BorderLayout.CENTER);
        contentPane.add(panelRodape, BorderLayout.SOUTH);
    }

    // Método chamado ao clicar em "Criar Playlist"
    private void criarPlaylist(JButton btnCriar) {
        String nome = txtNomePlaylist.getText().trim();

        // Valida se o nome foi preenchido
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, insira um nome para a playlist.",
                "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verifica se ao menos uma música foi selecionada
        boolean algumaMarcada = false;
        for (JCheckBox chk : checkboxes) {
            if (chk.isSelected()) { algumaMarcada = true; break; }
        }

        if (!algumaMarcada) {
            JOptionPane.showMessageDialog(this,
                "Selecione ao menos uma música para a playlist.",
                "Nenhuma música selecionada", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Cria o objeto Playlist e adiciona as músicas marcadas
        playlistAtual = new Playlist(nome);
        for (int i = 0; i < checkboxes.size(); i++) {
            if (checkboxes.get(i).isSelected()) {
                playlistAtual.adicionarMusica(musicasDisponiveis.get(i));
            }
        }

        // Congela os campos de criação para não editar depois de criar
        txtNomePlaylist.setEditable(false);
        for (JCheckBox chk : checkboxes) chk.setEnabled(false);
        btnCriar.setEnabled(false);

        // Exibe o painel de avaliação
        panelAvaliacao.setVisible(true);
        pack();

        JOptionPane.showMessageDialog(this,
            "Playlist \"" + nome + "\" criada com " + playlistAtual.getMusicas().size() + " música(s)!\nAgora avalie sua playlist.",
            "Playlist criada!", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método chamado ao clicar em "Avaliar"
    private void avaliarPlaylist() {
        // Garante que a playlist foi criada antes de avaliar
        if (playlistAtual == null) return;

        int estrelas = (int) spEstrelas.getValue();
        String comentario = txtComentario.getText().trim();

        // Aplica a avaliação no objeto Playlist
        playlistAtual.avaliar(estrelas, comentario);

        // Envia a playlist pronta para a TelaInicial
        telaPai.adicionarPlaylist(playlistAtual);

        JOptionPane.showMessageDialog(this,
            "Playlist avaliada com " + estrelas + " estrela(s)!\nEla aparecerá na sua lista.",
            "Avaliação salva!", JOptionPane.INFORMATION_MESSAGE);

        // Fecha esta tela
        dispose();
    }
}
