package view;

import model.ListaSimplesmenteEncadeada;
import model.ListaDuplamenteEncadeada;
import model.ListaEncadeadaCircular;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class GerenciadorListas extends JFrame {

    private ListaSimplesmenteEncadeada listaSimples;
    private ListaDuplamenteEncadeada listaDupla;
    private ListaEncadeadaCircular listaCircular;

    private JComboBox<String> comboTipoLista;
    private JTextArea areaVisualizacao;
    private JTextField campoValor;
    private JTextField campoValorAntigo;
    private JTextField campoValorNovo;
    private JTextField campoValorBuscar;
    private JTextField campoValorRemover;
    private JLabel labelTamanho;
    private JLabel labelStatus;
    private JTextArea areaCaracteristicas;

    public GerenciadorListas() {
        listaSimples = new ListaSimplesmenteEncadeada();
        listaDupla = new ListaDuplamenteEncadeada();
        listaCircular = new ListaEncadeadaCircular();

        setTitle("Gerenciador de Listas Encadeadas - Sistema CRUD");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        criarInterface();
        atualizarVisualizacao();
    }

    private void criarInterface() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(15, 15));
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(new Color(245, 245, 250));

        // PAINEL SUPERIOR - Seleção de tipo
        JPanel painelTopo = criarPainelTopo();

        // PAINEL CENTRAL - Visualização
        JPanel painelCentral = criarPainelVisualizacao();

        // PAINEL LATERAL - Características
        JPanel painelLateral = criarPainelCaracteristicas();

        // PAINEL INFERIOR - Operações CRUD
        JPanel painelOperacoes = criarPainelOperacoes();

        // PAINEL DE STATUS
        JPanel painelStatus = criarPainelStatus();

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);

        // Combinar visualização e características
        JPanel painelMeio = new JPanel(new BorderLayout(10, 0));
        painelMeio.setBackground(new Color(245, 245, 250));
        painelMeio.add(painelCentral, BorderLayout.CENTER);
        painelMeio.add(painelLateral, BorderLayout.EAST);

        painelPrincipal.add(painelMeio, BorderLayout.CENTER);
        painelPrincipal.add(painelOperacoes, BorderLayout.SOUTH);

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(new Color(245, 245, 250));
        painelInferior.add(painelOperacoes, BorderLayout.CENTER);
        painelInferior.add(painelStatus, BorderLayout.SOUTH);

        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private JPanel criarPainelTopo() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        painel.setBackground(new Color(63, 81, 181));

        JLabel labelTitulo = new JLabel("🔗 GERENCIADOR DE LISTAS ENCADEADAS");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setForeground(Color.WHITE);
        painel.add(labelTitulo);

        painel.add(Box.createRigidArea(new Dimension(30, 0)));

        JLabel labelTipo = new JLabel("Tipo de Lista:");
        labelTipo.setFont(new Font("Arial", Font.BOLD, 14));
        labelTipo.setForeground(Color.WHITE);
        painel.add(labelTipo);

        String[] tipos = {
                "Lista Simplesmente Encadeada",
                "Lista Duplamente Encadeada",
                "Lista Circular"
        };
        comboTipoLista = new JComboBox<>(tipos);
        comboTipoLista.setFont(new Font("Arial", Font.PLAIN, 13));
        comboTipoLista.setPreferredSize(new Dimension(250, 32));
        comboTipoLista.addActionListener(e -> {
            atualizarVisualizacao();
            atualizarCaracteristicas();
            atualizarStatus("Lista alterada para: " + comboTipoLista.getSelectedItem(), Color.BLUE);
        });
        painel.add(comboTipoLista);

        return painel;
    }

    private JPanel criarPainelCaracteristicas() {
        JPanel painel = new JPanel(new BorderLayout(5, 5));
        painel.setBackground(new Color(245, 245, 250));
        painel.setPreferredSize(new Dimension(280, 0));

        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(156, 39, 176), 2),
                "ℹ️ Características da Lista"
        );
        border.setTitleFont(new Font("Arial", Font.BOLD, 13));
        border.setTitleColor(new Color(156, 39, 176));

        areaCaracteristicas = new JTextArea();
        areaCaracteristicas.setEditable(false);
        areaCaracteristicas.setFont(new Font("Arial", Font.PLAIN, 12));
        areaCaracteristicas.setLineWrap(true);
        areaCaracteristicas.setWrapStyleWord(true);
        areaCaracteristicas.setBackground(new Color(255, 255, 255));
        areaCaracteristicas.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(areaCaracteristicas);
        scroll.setBorder(border);

        painel.add(scroll, BorderLayout.CENTER);

        atualizarCaracteristicas();

        return painel;
    }

    private JPanel criarPainelVisualizacao() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBackground(new Color(245, 245, 250));

        areaVisualizacao = new JTextArea(7, 50);
        areaVisualizacao.setEditable(false);
        areaVisualizacao.setFont(new Font("Monospaced", Font.BOLD, 15));
        areaVisualizacao.setBackground(Color.WHITE);
        areaVisualizacao.setLineWrap(true);
        areaVisualizacao.setWrapStyleWord(true);
        areaVisualizacao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                new EmptyBorder(10, 10, 10, 10)
        ));

        JScrollPane scroll = new JScrollPane(areaVisualizacao);
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(63, 81, 181), 2),
                "📋 Visualização da Lista em Tempo Real"
        );
        border.setTitleFont(new Font("Arial", Font.BOLD, 14));
        border.setTitleColor(new Color(63, 81, 181));
        scroll.setBorder(border);

        labelTamanho = new JLabel("📊 Tamanho: 0 elementos | Lista vazia");
        labelTamanho.setFont(new Font("Arial", Font.BOLD, 13));
        labelTamanho.setForeground(new Color(100, 100, 100));
        labelTamanho.setBorder(new EmptyBorder(5, 5, 5, 5));

        painel.add(scroll, BorderLayout.CENTER);
        painel.add(labelTamanho, BorderLayout.SOUTH);

        return painel;
    }

    private JPanel criarPainelOperacoes() {
        JPanel painel = new JPanel(new GridLayout(4, 1, 8, 8));
        painel.setBackground(new Color(245, 245, 250));
        painel.setBorder(new EmptyBorder(10, 0, 10, 0));

        // CREATE
        painel.add(criarPainelCreate());

        // READ
        painel.add(criarPainelRead());

        // UPDATE
        painel.add(criarPainelUpdate());

        // DELETE
        painel.add(criarPainelDelete());

        return painel;
    }

    private JPanel criarPainelCreate() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(76, 175, 80), 2),
                new EmptyBorder(8, 10, 8, 10)
        ));

        JLabel label = new JLabel("🟢 CREATE - Inserir:");
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(new Color(76, 175, 80));
        label.setPreferredSize(new Dimension(150, 25));
        painel.add(label);

        painel.add(new JLabel("Valor:"));
        campoValor = new JTextField(12);
        campoValor.setFont(new Font("Arial", Font.PLAIN, 13));
        campoValor.addActionListener(e -> inserir());
        painel.add(campoValor);

        JButton btnInserir = criarBotao("➕ Inserir", new Color(76, 175, 80));
        btnInserir.addActionListener(e -> inserir());
        painel.add(btnInserir);

        return painel;
    }

    private JPanel criarPainelRead() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(33, 150, 243), 2),
                new EmptyBorder(8, 10, 8, 10)
        ));

        JLabel label = new JLabel("🔵 READ - Buscar:");
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(new Color(33, 150, 243));
        label.setPreferredSize(new Dimension(150, 25));
        painel.add(label);

        painel.add(new JLabel("Valor:"));
        campoValorBuscar = new JTextField(12);
        campoValorBuscar.setFont(new Font("Arial", Font.PLAIN, 13));
        campoValorBuscar.addActionListener(e -> buscar());
        painel.add(campoValorBuscar);

        JButton btnBuscar = criarBotao("🔍 Buscar", new Color(33, 150, 243));
        btnBuscar.addActionListener(e -> buscar());
        painel.add(btnBuscar);

        return painel;
    }

    private JPanel criarPainelUpdate() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 152, 0), 2),
                new EmptyBorder(8, 10, 8, 10)
        ));

        JLabel label = new JLabel("🟠 UPDATE - Atualizar:");
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(new Color(255, 152, 0));
        label.setPreferredSize(new Dimension(150, 25));
        painel.add(label);

        painel.add(new JLabel("Antigo:"));
        campoValorAntigo = new JTextField(8);
        campoValorAntigo.setFont(new Font("Arial", Font.PLAIN, 13));
        painel.add(campoValorAntigo);

        painel.add(new JLabel("Novo:"));
        campoValorNovo = new JTextField(8);
        campoValorNovo.setFont(new Font("Arial", Font.PLAIN, 13));
        campoValorNovo.addActionListener(e -> atualizar());
        painel.add(campoValorNovo);

        JButton btnAtualizar = criarBotao("✏️ Atualizar", new Color(255, 152, 0));
        btnAtualizar.addActionListener(e -> atualizar());
        painel.add(btnAtualizar);

        return painel;
    }

    private JPanel criarPainelDelete() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(244, 67, 54), 2),
                new EmptyBorder(8, 10, 8, 10)
        ));

        JLabel label = new JLabel("🔴 DELETE - Remover:");
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(new Color(244, 67, 54));
        label.setPreferredSize(new Dimension(150, 25));
        painel.add(label);

        painel.add(new JLabel("Valor:"));
        campoValorRemover = new JTextField(12);
        campoValorRemover.setFont(new Font("Arial", Font.PLAIN, 13));
        campoValorRemover.addActionListener(e -> remover());
        painel.add(campoValorRemover);

        JButton btnRemover = criarBotao("🗑️ Remover", new Color(244, 67, 54));
        btnRemover.addActionListener(e -> remover());
        painel.add(btnRemover);

        JButton btnLimpar = criarBotao("🧹 Limpar Tudo", new Color(180, 50, 50));
        btnLimpar.addActionListener(e -> limparLista());
        painel.add(btnLimpar);

        return painel;
    }

    private JPanel criarPainelStatus() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        painel.setBackground(new Color(240, 240, 240));
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(200, 200, 200)),
                new EmptyBorder(8, 10, 8, 10)
        ));

        JLabel labelTitulo = new JLabel("📢 Status:");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 12));
        painel.add(labelTitulo);

        labelStatus = new JLabel("Sistema pronto. Selecione uma operação.");
        labelStatus.setFont(new Font("Arial", Font.PLAIN, 12));
        labelStatus.setForeground(new Color(100, 100, 100));
        painel.add(labelStatus);

        return painel;
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 12));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setOpaque(true);
        botao.setPreferredSize(new Dimension(130, 32));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efeito hover
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor);
            }
        });

        return botao;
    }

    // ==================== OPERAÇÕES CRUD ====================

    // CREATE
    private void inserir() {
        try {
            String texto = campoValor.getText().trim();
            if (texto.isEmpty()) {
                atualizarStatus("⚠️ Digite um valor para inserir!", new Color(255, 152, 0));
                return;
            }

            int valor = Integer.parseInt(texto);

            switch (comboTipoLista.getSelectedIndex()) {
                case 0: listaSimples.inserir(valor); break;
                case 1: listaDupla.inserir(valor); break;
                case 2: listaCircular.inserir(valor); break;
            }

            atualizarVisualizacao();
            campoValor.setText("");
            campoValor.requestFocus();
            atualizarStatus("✅ Valor " + valor + " inserido com sucesso!", new Color(76, 175, 80));
        } catch (NumberFormatException e) {
            atualizarStatus("❌ Erro: Digite um valor numérico válido!", new Color(244, 67, 54));
            campoValor.selectAll();
            campoValor.requestFocus();
        }
    }

    // READ
    private void buscar() {
        try {
            String texto = campoValorBuscar.getText().trim();
            if (texto.isEmpty()) {
                atualizarStatus("⚠️ Digite um valor para buscar!", new Color(255, 152, 0));
                return;
            }

            int valor = Integer.parseInt(texto);
            boolean encontrado = false;

            switch (comboTipoLista.getSelectedIndex()) {
                case 0: encontrado = listaSimples.buscar(valor); break;
                case 1: encontrado = listaDupla.buscar(valor); break;
                case 2: encontrado = listaCircular.buscar(valor); break;
            }

            if (encontrado) {
                atualizarStatus("✅ Valor " + valor + " ENCONTRADO na lista!", new Color(33, 150, 243));
                JOptionPane.showMessageDialog(this,
                        "Valor " + valor + " foi encontrado na lista!",
                        "Busca - Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                atualizarStatus("❌ Valor " + valor + " NÃO encontrado na lista.", new Color(244, 67, 54));
                JOptionPane.showMessageDialog(this,
                        "Valor " + valor + " não existe na lista.",
                        "Busca - Não Encontrado",
                        JOptionPane.WARNING_MESSAGE);
            }

            campoValorBuscar.selectAll();
            campoValorBuscar.requestFocus();
        } catch (NumberFormatException e) {
            atualizarStatus("❌ Erro: Digite um valor numérico válido!", new Color(244, 67, 54));
            campoValorBuscar.selectAll();
            campoValorBuscar.requestFocus();
        }
    }

    // UPDATE
    private void atualizar() {
        try {
            String textoAntigo = campoValorAntigo.getText().trim();
            String textoNovo = campoValorNovo.getText().trim();

            if (textoAntigo.isEmpty() || textoNovo.isEmpty()) {
                atualizarStatus("⚠️ Preencha ambos os campos (antigo e novo)!", new Color(255, 152, 0));
                return;
            }

            int valorAntigo = Integer.parseInt(textoAntigo);
            int valorNovo = Integer.parseInt(textoNovo);
            boolean atualizado = false;

            switch (comboTipoLista.getSelectedIndex()) {
                case 0: atualizado = listaSimples.atualizar(valorAntigo, valorNovo); break;
                case 1: atualizado = listaDupla.atualizar(valorAntigo, valorNovo); break;
                case 2: atualizado = listaCircular.atualizar(valorAntigo, valorNovo); break;
            }

            if (atualizado) {
                atualizarVisualizacao();
                campoValorAntigo.setText("");
                campoValorNovo.setText("");
                campoValorAntigo.requestFocus();
                atualizarStatus("✅ Valor " + valorAntigo + " atualizado para " + valorNovo + "!", new Color(255, 152, 0));
            } else {
                atualizarStatus("❌ Valor " + valorAntigo + " não encontrado na lista!", new Color(244, 67, 54));
                JOptionPane.showMessageDialog(this,
                        "Valor " + valorAntigo + " não existe na lista.",
                        "Atualização - Erro",
                        JOptionPane.ERROR_MESSAGE);
                campoValorAntigo.selectAll();
                campoValorAntigo.requestFocus();
            }
        } catch (NumberFormatException e) {
            atualizarStatus("❌ Erro: Digite valores numéricos válidos!", new Color(244, 67, 54));
        }
    }

    // DELETE
    private void remover() {
        try {
            String texto = campoValorRemover.getText().trim();
            if (texto.isEmpty()) {
                atualizarStatus("⚠️ Digite um valor para remover!", new Color(255, 152, 0));
                return;
            }

            int valor = Integer.parseInt(texto);
            boolean removido = false;

            switch (comboTipoLista.getSelectedIndex()) {
                case 0: removido = listaSimples.remover(valor); break;
                case 1: removido = listaDupla.remover(valor); break;
                case 2: removido = listaCircular.remover(valor); break;
            }

            if (removido) {
                atualizarVisualizacao();
                campoValorRemover.setText("");
                campoValorRemover.requestFocus();
                atualizarStatus("✅ Valor " + valor + " removido com sucesso!", new Color(244, 67, 54));
            } else {
                atualizarStatus("❌ Valor " + valor + " não encontrado na lista!", new Color(244, 67, 54));
                JOptionPane.showMessageDialog(this,
                        "Valor " + valor + " não existe na lista.",
                        "Remoção - Erro",
                        JOptionPane.ERROR_MESSAGE);
                campoValorRemover.selectAll();
                campoValorRemover.requestFocus();
            }
        } catch (NumberFormatException e) {
            atualizarStatus("❌ Erro: Digite um valor numérico válido!", new Color(244, 67, 54));
            campoValorRemover.selectAll();
            campoValorRemover.requestFocus();
        }
    }

    private void limparLista() {
        int resposta = JOptionPane.showConfirmDialog(this,
                "⚠️ Tem certeza que deseja limpar TODOS os elementos da lista?\n\nEsta ação não pode ser desfeita!",
                "Confirmar Limpeza",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            switch (comboTipoLista.getSelectedIndex()) {
                case 0: listaSimples.limpar(); break;
                case 1: listaDupla.limpar(); break;
                case 2: listaCircular.limpar(); break;
            }
            atualizarVisualizacao();
            atualizarStatus("🧹 Lista limpa com sucesso! Todos os elementos foram removidos.", new Color(180, 50, 50));
        } else {
            atualizarStatus("↩️ Operação de limpeza cancelada.", new Color(100, 100, 100));
        }
    }

    // ==================== MÉTODOS AUXILIARES ====================

    private void atualizarVisualizacao() {
        String texto = "";
        int tamanho = 0;
        boolean vazia = true;

        switch (comboTipoLista.getSelectedIndex()) {
            case 0:
                texto = listaSimples.toString();
                tamanho = listaSimples.getTamanho();
                vazia = listaSimples.estaVazia();
                break;
            case 1:
                texto = listaDupla.toString();
                tamanho = listaDupla.getTamanho();
                vazia = listaDupla.estaVazia();
                break;
            case 2:
                texto = listaCircular.toString();
                tamanho = listaCircular.getTamanho();
                vazia = listaCircular.estaVazia();
                break;
        }

        areaVisualizacao.setText(texto);

        String statusLista = vazia ? " | Lista vazia" : " | Lista contém elementos";
        labelTamanho.setText("📊 Tamanho: " + tamanho + " elemento(s)" + statusLista);
    }

    private void atualizarStatus(String mensagem, Color cor) {
        labelStatus.setText(mensagem);
        labelStatus.setForeground(cor);
    }

    private void atualizarCaracteristicas() {
        String texto = "";

        switch (comboTipoLista.getSelectedIndex()) {
            case 0: // Lista Simples
                texto = "📌 LISTA SIMPLESMENTE ENCADEADA\n\n" +
                        "🔗 Estrutura:\n" +
                        "• Cada nó possui apenas 1 ponteiro\n" +
                        "• Aponta somente para o PRÓXIMO\n" +
                        "• Navegação unidirecional (→)\n\n" +
                        "💾 Memória:\n" +
                        "• Menor uso de memória\n" +
                        "• 1 ponteiro por nó\n\n" +
                        "⚡ Vantagens:\n" +
                        "• Simples de implementar\n" +
                        "• Econômica em memória\n" +
                        "• Boa para inserção no início\n\n" +
                        "⚠️ Desvantagens:\n" +
                        "• Não permite voltar\n" +
                        "• Busca sequencial obrigatória\n" +
                        "• Remoção no fim é lenta (O(n))\n\n" +
                        "📊 Complexidade:\n" +
                        "• Inserir início: O(1)\n" +
                        "• Inserir fim: O(n)\n" +
                        "• Buscar: O(n)\n" +
                        "• Remover: O(n)";
                break;

            case 1: // Lista Dupla
                texto = "📌 LISTA DUPLAMENTE ENCADEADA\n\n" +
                        "🔗 Estrutura:\n" +
                        "• Cada nó possui 2 ponteiros\n" +
                        "• Aponta para PRÓXIMO e ANTERIOR\n" +
                        "• Navegação bidirecional (← →)\n\n" +
                        "💾 Memória:\n" +
                        "• Maior uso de memória\n" +
                        "• 2 ponteiros por nó\n\n" +
                        "⚡ Vantagens:\n" +
                        "• Navegação em duas direções\n" +
                        "• Remoção mais eficiente\n" +
                        "• Pode percorrer do fim ao início\n" +
                        "• Inserção/remoção no fim: O(1)\n\n" +
                        "⚠️ Desvantagens:\n" +
                        "• Usa mais memória\n" +
                        "• Implementação mais complexa\n" +
                        "• Manutenção de 2 ponteiros\n\n" +
                        "📊 Complexidade:\n" +
                        "• Inserir início/fim: O(1)\n" +
                        "• Buscar: O(n)\n" +
                        "• Remover: O(n)";
                break;

            case 2: // Lista Circular
                texto = "📌 LISTA ENCADEADA CIRCULAR\n\n" +
                        "🔗 Estrutura:\n" +
                        "• Cada nó possui 1 ponteiro\n" +
                        "• Último nó aponta para o INÍCIO\n" +
                        "• Forma um círculo (∞)\n" +
                        "• Não tem fim definido!\n\n" +
                        "💾 Memória:\n" +
                        "• Mesma de lista simples\n" +
                        "• 1 ponteiro por nó\n\n" +
                        "⚡ Vantagens:\n" +
                        "• Navegação contínua infinita\n" +
                        "• Ótima para round-robin\n" +
                        "• Qualquer nó pode ser início\n" +
                        "• Útil em filas circulares\n\n" +
                        "⚠️ Desvantagens:\n" +
                        "• Risco de loop infinito\n" +
                        "• Precisa marcar ponto de parada\n" +
                        "• Lógica mais complexa\n\n" +
                        "📊 Complexidade:\n" +
                        "• Inserir início: O(n)\n" +
                        "• Inserir fim: O(n)\n" +
                        "• Buscar: O(n)\n" +
                        "• Remover: O(n)\n\n" +
                        "💡 Uso comum: Gerenciadores de tarefas";
                break;
        }

        areaCaracteristicas.setText(texto);
        areaCaracteristicas.setCaretPosition(0);
    }

    // ==================== MAIN ====================

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            GerenciadorListas frame = new GerenciadorListas();
            frame.setVisible(true);
        });
    }
}