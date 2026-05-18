package avc.view;

//Biblioteca
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import avc.model.Musica;
import avc.model.Playlist;
import avc.data.RepositorioMusica;

public class TelaInicial extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelListaCartoes;
    private ArrayList<Musica> listaDeAvaliacoes = new ArrayList<>();

    // Lista que armazena todas as playlists criadas pelo usuário
    private ArrayList<Playlist> listaDePlaylist = new ArrayList<>();

    // Painel onde os cards de playlist são exibidos
    private JPanel panelListaPlaylist;

    //
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaInicial frame = new TelaInicial();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    //Método Construtor
    public TelaInicial() {
		setResizable(false);
        setTitle("SoundTrackly - Minha Lista");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        //Inicialização do Painel
        contentPane = new JPanel();
        contentPane.setBackground(new Color(18, 18, 18));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        //Criação Header
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(new Color(25, 25, 25));
        panelHeader.setPreferredSize(new Dimension(0, 60));
        panelHeader.setBorder(new MatteBorder(0, 0, 2, 0, new Color(106, 100, 250))); 
        
        //Criação Logo
        JLabel lblLogo = new JLabel("  🎵 SoundTrackly");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("SansSerif", Font.BOLD, 22));
        panelHeader.add(lblLogo, BorderLayout.WEST);
        
        //"Informações do Usuário"
        JLabel lblUser = new JLabel(""); 
        lblUser.setForeground(Color.WHITE);
        lblUser.setFont(new Font("SansSerif", Font.PLAIN, 18));
        panelHeader.add(lblUser, BorderLayout.EAST);
        contentPane.add(panelHeader, BorderLayout.NORTH);

        //Painel Esquerdo
        JPanel panelEsquerdo = new JPanel();
        panelEsquerdo.setBackground(new Color(35, 35, 35));
        panelEsquerdo.setPreferredSize(new Dimension(60, 0));
        panelEsquerdo.setLayout(new BoxLayout(panelEsquerdo, BoxLayout.Y_AXIS));
        String[] icones = {"🏠", "🚪"};
        
        //Loop para a criação e estilização da lateral
        for (String icone : icones) {
            JLabel lblIcon = new JLabel(icone);
            lblIcon.setForeground(Color.GRAY);
            lblIcon.setFont(new Font("SansSerif", Font.PLAIN, 24));
            lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblIcon.setBorder(new EmptyBorder(15, 0, 15, 0));
            
            lblIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            //Verificação Logout
            if (icone.equals("🚪")) {
                lblIcon.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int confirm = JOptionPane.showConfirmDialog(null, 
                            "Deseja realmente sair da sua conta?", 
                            "Fazer Logout", 
                            JOptionPane.YES_NO_OPTION);
                            
                        if (confirm == JOptionPane.YES_OPTION) {
                            dispose();
                            new TelaLogin().setVisible(true);
                        }
                    }
                });
            }
            panelEsquerdo.add(lblIcon);
        }
        
        contentPane.add(panelEsquerdo, BorderLayout.WEST);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(new Color(18, 18, 18));

        JPanel panelBotao = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelBotao.setBackground(new Color(18, 18, 18));
        
        //Botão "Nova Avaliação"
        JButton btnAdicionar = new JButton("+ Nova Avaliação");
        btnAdicionar.setPreferredSize(new Dimension(160, 45));
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setBackground(new Color(106, 100, 250)); // Cor roxa
        btnAdicionar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdicionar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdicionar.setFocusPainted(false);
        btnAdicionar.setBorderPainted(false);
        btnAdicionar.setOpaque(true); 
        
        //Lógica de abrir "TelaMusica"
        btnAdicionar.addActionListener(e -> new TelaMusica(this, null, -1).setVisible(true));
        panelBotao.add(btnAdicionar);

        // Botão "Nova Playlist" — abre a TelaPlaylist passando a lista de músicas avaliadas
        JButton btnPlaylist = new JButton("+ Nova Playlist");
        btnPlaylist.setPreferredSize(new Dimension(160, 45));
        btnPlaylist.setForeground(Color.WHITE);
        btnPlaylist.setBackground(new Color(60, 120, 60)); // verde para distinguir do roxo
        btnPlaylist.setFont(new Font("Arial", Font.BOLD, 14));
        btnPlaylist.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPlaylist.setFocusPainted(false);
        btnPlaylist.setBorderPainted(false);
        btnPlaylist.setOpaque(true);
        btnPlaylist.addActionListener(e -> new TelaPlaylist(this, listaDeAvaliacoes).setVisible(true));
        panelBotao.add(btnPlaylist);
        panelCentral.add(panelBotao, BorderLayout.NORTH);

        panelListaCartoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelListaCartoes.setBackground(new Color(18, 18, 18));
        
        //Scroll p/ várias avaliações
        JScrollPane scrollPane = new JScrollPane(panelListaCartoes);
        scrollPane.setBorder(null); 
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        panelCentral.add(scrollPane, BorderLayout.CENTER);

        // ── SEÇÃO DE PLAYLISTS ──────────────────────────────────────────
        // Painel que agrupa o título e os cards de playlist
        JPanel panelSecaoPlaylist = new JPanel(new BorderLayout());
        panelSecaoPlaylist.setBackground(new Color(18, 18, 18));

        // Rótulo separador entre músicas e playlists
        JLabel lblSecaoPlaylist = new JLabel("  🎶 Minhas Playlists");
        lblSecaoPlaylist.setForeground(new Color(106, 100, 250));
        lblSecaoPlaylist.setFont(new Font("Arial", Font.BOLD, 16));
        lblSecaoPlaylist.setBorder(new javax.swing.border.MatteBorder(2, 0, 0, 0, new Color(50, 50, 50)));
        lblSecaoPlaylist.setPreferredSize(new Dimension(0, 40));
        panelSecaoPlaylist.add(lblSecaoPlaylist, BorderLayout.NORTH);

        // Painel que mostra os cards de playlist lado a lado
        panelListaPlaylist = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelListaPlaylist.setBackground(new Color(18, 18, 18));

        JScrollPane scrollPlaylist = new JScrollPane(panelListaPlaylist);
        scrollPlaylist.setBorder(null);
        scrollPlaylist.setPreferredSize(new Dimension(0, 220));
        scrollPlaylist.getVerticalScrollBar().setUnitIncrement(12);
        panelSecaoPlaylist.add(scrollPlaylist, BorderLayout.CENTER);

        panelCentral.add(panelSecaoPlaylist, BorderLayout.SOUTH);
        // ────────────────────────────────────────────────────────────────
        contentPane.add(panelCentral, BorderLayout.CENTER);

        //Salvamento dos dados da música cadastrada
        listaDeAvaliacoes = RepositorioMusica.carregarMusicas();
        atualizarInterface();
    }

    //Adicionar nova música na Array
    public void adicionarMusica(Musica m) {
        listaDeAvaliacoes.add(m);
        RepositorioMusica.salvarTodas(listaDeAvaliacoes);
        atualizarInterface();
    }

    //Edição dos dados da música
    public void editarMusica(Musica m, int index) {
        listaDeAvaliacoes.set(index, m);
        RepositorioMusica.salvarTodas(listaDeAvaliacoes);
        atualizarInterface();
    }

    // Recebe uma playlist criada na TelaPlaylist e atualiza a interface
    public void adicionarPlaylist(Playlist p) {
        listaDePlaylist.add(p);
        atualizarPlaylist();
    }

    // Redesenha a seção de playlists com os dados atuais
    private void atualizarPlaylist() {
        panelListaPlaylist.removeAll();

        for (Playlist p : listaDePlaylist) {
            // Card de playlist (visual diferente do card de música)
            JPanel card = new JPanel(new BorderLayout(8, 8));
            card.setPreferredSize(new Dimension(300, 175));
            card.setBackground(new Color(35, 35, 50)); // tom azulado para diferenciar
            card.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(1, 1, 1, 1, new Color(80, 70, 160)),
                new EmptyBorder(10, 12, 10, 12)
            ));

            // Nome da playlist em destaque
            JLabel lblNome = new JLabel("🎶 " + p.getNome());
            lblNome.setFont(new Font("Arial", Font.BOLD, 15));
            lblNome.setForeground(new Color(106, 100, 250));
            card.add(lblNome, BorderLayout.NORTH);

            // Quantidade de músicas + média de estrelas das músicas
            JPanel panelInfo = new JPanel(new GridLayout(2, 1, 0, 4));
            panelInfo.setOpaque(false);

            JLabel lblQtd = new JLabel(p.getMusicas().size() + " música(s)  |  Média: "
                    + String.format("%.1f", p.getMediaEstrelas()) + " ★");
            lblQtd.setForeground(new Color(180, 180, 180));
            lblQtd.setFont(new Font("Arial", Font.PLAIN, 12));
            panelInfo.add(lblQtd);

            // Exibe a avaliação dada pelo usuário à playlist
            if (p.isAvaliada()) {
                StringBuilder sb = new StringBuilder("<html><font color='#F5A623'>");
                for (int j = 0; j < p.getEstrelas(); j++) sb.append("&#9733;");
                for (int j = p.getEstrelas(); j < 5; j++) sb.append("&#9734;");
                sb.append("</font>  <font color='#cccccc'>" + p.getComentario() + "</font></html>");
                panelInfo.add(new JLabel(sb.toString()));
            } else {
                JLabel lblSemAval = new JLabel("Ainda não avaliada");
                lblSemAval.setForeground(Color.GRAY);
                lblSemAval.setFont(new Font("Arial", Font.ITALIC, 12));
                panelInfo.add(lblSemAval);
            }
            card.add(panelInfo, BorderLayout.CENTER);

            // Lista com os nomes das músicas da playlist (máximo 4 exibidos)
            StringBuilder musLista = new StringBuilder("<html><small><font color='#aaaaaa'>");
            int exibidos = 0;
            for (Musica m : p.getMusicas()) {
                if (exibidos >= 4) { musLista.append("• ...<br>"); break; }
                musLista.append("• ").append(m.getNome()).append("<br>");
                exibidos++;
            }
            musLista.append("</font></small></html>");
            card.add(new JLabel(musLista.toString()), BorderLayout.SOUTH);

            panelListaPlaylist.add(card);
        }

        panelListaPlaylist.revalidate();
        panelListaPlaylist.repaint();
    }

    //Exclusão dos dados da música
    public void excluirMusica(int index) {
        int confirm = JOptionPane.showConfirmDialog(null, "Deseja excluir esta música?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            listaDeAvaliacoes.remove(index);
            RepositorioMusica.salvarTodas(listaDeAvaliacoes);
            atualizarInterface();
        }
    }

    //"Limpeza" do painel
    public void atualizarInterface() {
        panelListaCartoes.removeAll();
        
        for (int i = 0; i < listaDeAvaliacoes.size(); i++) {
            final int index = i;
            Musica m = listaDeAvaliacoes.get(i);

            //Criação do card de música
            JPanel card = new JPanel(new BorderLayout(15, 15));
            card.setPreferredSize(new Dimension(480, 200)); 
            card.setBackground(new Color(30, 30, 30)); 
            card.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(1, 1, 1, 1, new Color(50, 50, 50)), 
                new EmptyBorder(15, 15, 15, 15) 
            ));
            
            JPanel panelTop = new JPanel(new BorderLayout(15, 0));
            panelTop.setOpaque(false);

            JLabel lblImagem = new JLabel("🎵", SwingConstants.CENTER);
            lblImagem.setPreferredSize(new Dimension(80, 80));
            lblImagem.setOpaque(true);
            lblImagem.setBackground(Color.BLACK);
            lblImagem.setForeground(new Color(106, 100, 250));
            lblImagem.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
            panelTop.add(lblImagem, BorderLayout.WEST);

            JPanel panelInfo = new JPanel(new GridLayout(4, 1));
            panelInfo.setOpaque(false);
            
            //Nome da Música
            JLabel lblTitulo = new JLabel(m.getNome());
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
            lblTitulo.setForeground(Color.WHITE);

            //Artista
            JLabel lblArtista = new JLabel(m.getArtista());
            lblArtista.setFont(new Font("Arial", Font.PLAIN, 13));
            lblArtista.setForeground(new Color(160, 160, 160));
            
            //Gênero Musical
            JLabel lblGenero = new JLabel(m.getGenero());
            lblGenero.setFont(new Font("Arial", Font.ITALIC, 12));
            lblGenero.setForeground(new Color(130, 130, 130));
            
            //Estrelas
            StringBuilder estrelasHtml = new StringBuilder("<html><font size='+1' color='#F5A623'>");
            for (int j = 0; j < m.getEstrelas(); j++) estrelasHtml.append("&#9733; ");
            for (int j = m.getEstrelas(); j < 5; j++) estrelasHtml.append("&#9734; ");
            estrelasHtml.append("</font></html>");
            
            JLabel lblEstrelas = new JLabel(estrelasHtml.toString());

            //Montagem do card
            panelInfo.add(lblTitulo);
            panelInfo.add(lblArtista);
            panelInfo.add(lblGenero);
            panelInfo.add(lblEstrelas);
            panelTop.add(panelInfo, BorderLayout.CENTER);

            card.add(panelTop, BorderLayout.NORTH);

            JTextArea txtComentario = new JTextArea(m.getComentario());
            txtComentario.setFont(new Font("Arial", Font.PLAIN, 14));
            txtComentario.setForeground(new Color(220, 220, 220));
            txtComentario.setBackground(new Color(30, 30, 30)); 
            txtComentario.setLineWrap(true);
            txtComentario.setWrapStyleWord(true);
            txtComentario.setEditable(false);
            card.add(txtComentario, BorderLayout.CENTER);

            JPanel pnlAcoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            pnlAcoes.setOpaque(false);

            JButton btnEdit = new JButton("Editar");
            btnEdit.setFont(new Font("Arial", Font.BOLD, 11));
            btnEdit.setForeground(Color.WHITE);
            btnEdit.setBackground(new Color(70, 70, 70));
            btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnEdit.setFocusPainted(false);
            btnEdit.setBorderPainted(false);
            btnEdit.setOpaque(true);
            btnEdit.addActionListener(e -> new TelaMusica(this, m, index).setVisible(true));

            JButton btnDel = new JButton("Excluir");
            btnDel.setFont(new Font("Arial", Font.BOLD, 11));
            btnDel.setForeground(Color.WHITE);
            btnDel.setBackground(new Color(200, 50, 50));
            btnDel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnDel.setFocusPainted(false);
            btnDel.setBorderPainted(false);
            btnDel.setOpaque(true);
            btnDel.addActionListener(e -> excluirMusica(index));

            pnlAcoes.add(btnEdit);
            pnlAcoes.add(btnDel);
            card.add(pnlAcoes, BorderLayout.SOUTH);

            panelListaCartoes.add(card);
        }
        
        panelListaCartoes.revalidate();
        panelListaCartoes.repaint();
    }
}