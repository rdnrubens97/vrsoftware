/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.vrs.projetovrs.view;

import com.vrs.projetovrs.dao.ProdutoDao;
import com.vrs.projetovrs.exception.ValidationException;
import com.vrs.projetovrs.model.Produto;
import com.vrs.projetovrs.service.ProdutoService;
import com.vrs.projetovrs.util.Utilities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Rubens
 */
public class ProdutoView extends javax.swing.JFrame {
    private ProdutoService produtoService;
    /**
     * variável global que é determinada de acordo com um produto selecionado na 'tabelaExibicaoProdutos'
     * tendo armazenado o id de determinado registro, podemos executar os métodos de excluir e editar registros
     */
    private Integer idSelecionado;
    private enum Estado { INICIAL, NOVO, EDITANDO }
    private Estado estadoAtual;

    /**
     * Creates new form ProdutoView
     */
    public ProdutoView() {
        initComponents();
        setLocationRelativeTo(null);
        estadoAtual = Estado.INICIAL;
        gerenciadorDeBotoes();
        produtoService = new ProdutoService(new ProdutoDao());
    }

    private void gerenciadorDeBotoes() {
        switch (estadoAtual) {
            case INICIAL:
                botaoNovo.setEnabled(true);
                botaoSalvar.setEnabled(false);
                botaoEditar.setEnabled(false);
                botaoExcluir.setEnabled(false);
                botaoLimpar.setEnabled(false);

                textoCodigoProduto.setEditable(false);
                textoCodigoProduto.setBackground(Color.LIGHT_GRAY);

                textoDescricaoProduto.setEditable(false);
                textoDescricaoProduto.setBackground(Color.LIGHT_GRAY);

                textoPrecoProduto.setEditable(false);
                textoPrecoProduto.setBackground(Color.LIGHT_GRAY);

                textoNcmProduto.setEditable(false);
                textoNcmProduto.setBackground(Color.LIGHT_GRAY);

                textoQuantidade.setEditable(false);
                textoQuantidade.setBackground(Color.LIGHT_GRAY);

                break;
            case NOVO:
                painelComAbasProduto.setSelectedIndex(1);
                botaoNovo.setEnabled(false);
                botaoSalvar.setEnabled(true);
                botaoEditar.setEnabled(false);
                botaoExcluir.setEnabled(false);
                botaoLimpar.setEnabled(true);

                textoCodigoProduto.setEditable(true);
                textoCodigoProduto.setBackground(Color.WHITE);

                textoDescricaoProduto.setEditable(true);
                textoDescricaoProduto.setBackground(Color.WHITE);

                textoPrecoProduto.setEditable(true);
                textoPrecoProduto.setBackground(Color.WHITE);

                textoNcmProduto.setEditable(true);
                textoNcmProduto.setBackground(Color.WHITE);

                textoQuantidade.setEditable(true);
                textoQuantidade.setBackground(Color.WHITE);

                break;
            case EDITANDO:
                botaoNovo.setEnabled(false);
                botaoSalvar.setEnabled(false);
                botaoEditar.setEnabled(true);
                botaoExcluir.setEnabled(true);
                botaoLimpar.setEnabled(true);

                textoCodigoProduto.setEditable(true);
                textoCodigoProduto.setBackground(Color.WHITE);

                textoDescricaoProduto.setEditable(true);
                textoDescricaoProduto.setBackground(Color.WHITE);

                textoPrecoProduto.setEditable(true);
                textoPrecoProduto.setBackground(Color.WHITE);

                textoNcmProduto.setEditable(true);
                textoNcmProduto.setBackground(Color.WHITE);

                textoQuantidade.setEditable(true);
                textoQuantidade.setBackground(Color.WHITE);

                break;
            default:

        }
    }

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        listarProdutosNaTabela();
        painelComAbasProduto.setSelectedIndex(0);
    }//GEN-LAST:event_formWindowActivated

    /**
     * lista todos os produtos registrados no banco de dados na tabela de visualização de produtos (tabelaExibicaoProdutos)
     */
    public void listarProdutosNaTabela() {
        List<Produto> lista = produtoService.listarProdutos();
        DefaultTableModel dados = (DefaultTableModel) tabelaExibicaoProdutos.getModel();
        dados.setNumRows(0);
        for (Produto produto : lista) {
            dados.addRow(new Object[]{
                    produto.getCodigo(),
                    produto.getDescricao(),
                    "R$ " + produto.getPreco(),
                    produto.getNcm()
            });
        }
    }

    /**
     * faz uma busca instantânea ao por produtos que tenham na descrição os caracteres inseridos no campo de busca
     * da aba consulta produtos
     *
     * @param evt
     */
    private void textoDescricaoPesquisaProdutoKeyPressed(java.awt.event.KeyEvent evt) {
        String nomeOuCodigoDesejado = textoDescricaoPesquisaProduto.getText();

        ProdutoDao dao = new ProdutoDao();
        List<Produto> listaDeProdutos = dao.listarProdutosPorNomeOuCodigo(nomeOuCodigoDesejado);

        DefaultTableModel dados = (DefaultTableModel) tabelaExibicaoProdutos.getModel();
        dados.setNumRows(0);
        for (Produto produto : listaDeProdutos) {
            dados.addRow(new Object[]{
                    //produto.getId(),
                    produto.getCodigo(),
                    produto.getDescricao(),
                    "R$ " + produto.getPreco(),
                    produto.getNcm()
            });
        }
    }

    private void textoQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoQuantidadeActionPerformed
    }//GEN-LAST:event_textoQuantidadeActionPerformed

    private void textoDescricaoPesquisaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoDescricaoPesquisaProdutoActionPerformed
        String nomeOuCodigoDesejado = textoDescricaoPesquisaProduto.getText();
        List<Produto> listaDeProdutos = produtoService.listarProdutosPorNomeOuCodigo(nomeOuCodigoDesejado);

        DefaultTableModel dados = (DefaultTableModel) tabelaExibicaoProdutos.getModel();
        dados.setNumRows(0);
        for (Produto produto : listaDeProdutos) {
            dados.addRow(new Object[]{
                    //produto.getId(),
                    produto.getCodigo(),
                    produto.getDescricao(),
                    "R$ " + produto.getPreco(),
                    produto.getNcm()
            });
        }
    }//GEN-LAST:event_textoDescricaoPesquisaProdutoActionPerformed

    private void textoDescricaoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoDescricaoProdutoActionPerformed
    }//GEN-LAST:event_textoDescricaoProdutoActionPerformed

    private void textoNcmProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoNcmProdutoActionPerformed
    }//GEN-LAST:event_textoNcmProdutoActionPerformed

    /**
     * seleciona determinado produto na 'tabelaExibicaoProdutos' e preenche os campos 'textoDescricao', 'textoPreco' e 'textoQuantidade' na janela 'dados do produto'
     * de modo que o usuário possa editar ou excluir este mesmo produto
     *
     * @param evt
     */
    private void tabelaExibicaoProdutosMouseClicked(java.awt.event.MouseEvent evt) {
        String codigoProdutoSelecionadoNaTabela = tabelaExibicaoProdutos.getValueAt(tabelaExibicaoProdutos.getSelectedRow(), 0).toString();
        Produto produto = produtoService.buscarProdutoPorCodigo(codigoProdutoSelecionadoNaTabela);

        if (produto != null) {
            this.idSelecionado = produto.getId();

            textoCodigoProduto.setText(produto.getCodigo().toString());
            textoDescricaoProduto.setText(produto.getDescricao());
            textoNcmProduto.setText(produto.getNcm().toString());
            textoQuantidade.setText(produto.getQuandidade().toString());
            textoPrecoProduto.setText(produto.getPreco().toString());

            estadoAtual = Estado.EDITANDO;
            gerenciadorDeBotoes();
            painelComAbasProduto.setSelectedIndex(1);
        }
        else {
            throw new RuntimeException("Erro ao buscar dados do produto selecionado");
        }
    }

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        estadoAtual = Estado.NOVO;
        gerenciadorDeBotoes();
    }//GEN-LAST:event_botaoNovoActionPerformed

    /**
     * realiza a chamada de uma sequência de métodos responsáveis por salvar um novo registro no banco de dados
     *
     * @param evt
     */
    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String codigo = textoCodigoProduto.getText();
            String descricao = textoDescricaoProduto.getText();
            BigDecimal preco = new BigDecimal(textoPrecoProduto.getText().replace(",", "."));
            Integer quantidade = Integer.parseInt(textoQuantidade.getText());
            Integer ncm = Integer.parseInt(textoNcmProduto.getText());

            validarCampos(codigo, descricao, preco, quantidade);

            Produto produto = new Produto();
            produto.setCodigo(codigo);
            produto.setDescricao(descricao);
            produto.setPreco(preco);
            produto.setQuandidade(quantidade);
            produto.setNcm(ncm);
            produtoService.cadastrarProduto(produto);

            new Utilities().limpaCampos(painelDadosProduto);
            estadoAtual = Estado.INICIAL;
            gerenciadorDeBotoes();
        } catch (NumberFormatException | ValidationException ex) {
            JOptionPane.showMessageDialog(null, "Digite valores válidos para descrição, preço e quantidade");
        }
    }

    /**
     * realiza a chamada de uma sequência de métodos responsáveis pela edição de um registro presente no banco de dados
     *
     * @param evt
     */
    private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (this.idSelecionado != null) {
                String codigo = textoCodigoProduto.getText();
                String descricao = textoDescricaoProduto.getText();
                BigDecimal preco = new BigDecimal(textoPrecoProduto.getText().replace(",", "."));
                Integer quantidade = Integer.parseInt(textoQuantidade.getText());
                Integer ncm = Integer.parseInt(textoNcmProduto.getText());

                validarCampos(codigo, descricao, preco, quantidade);

                Produto produto = new Produto();
                produto.setId(this.idSelecionado);
                produto.setCodigo(codigo);
                produto.setDescricao(descricao);
                produto.setPreco(preco);
                produto.setQuandidade(quantidade);
                produto.setNcm(ncm);
                produtoService.editarProduto(produto);
            }
            new Utilities().limpaCampos(painelDadosProduto);
            this.idSelecionado = null;
            estadoAtual = Estado.INICIAL;
            gerenciadorDeBotoes();
        } catch (NumberFormatException | ValidationException ex) {
            JOptionPane.showMessageDialog(null, "Digite valores válidos para descrição, preço e quantidade");
        }
    }

    /**
     * realiza a chamada de uma sequência de métodos responsáveis pela exclusão de um registro presente no banco de dados
     *
     * @param evt
     */
    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.idSelecionado != null) {
            produtoService.excluirProduto(idSelecionado);
        }
        new Utilities().limpaCampos(painelDadosProduto);
        this.idSelecionado = null;
        estadoAtual = Estado.INICIAL;
        gerenciadorDeBotoes();
    }

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {
        new Utilities().limpaCampos(painelDadosProduto);
        this.idSelecionado = null;
        estadoAtual = Estado.INICIAL;
        gerenciadorDeBotoes();
    }

    private void textoCodigoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoCodigoProdutoActionPerformed
    }//GEN-LAST:event_textoCodigoProdutoActionPerformed

    private void textoPrecoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoPrecoProdutoActionPerformed
    }//GEN-LAST:event_textoPrecoProdutoActionPerformed

    /**
     * valida os os valores inseridos nos campos de criação/ edição de um produto
     * caso o usuários coloque valores inválidos, definido no método, uma exceção é lançada
     * @param descricao
     * @param preco
     * @param quantidade
     * @throws ValidationException
     */
    private void validarCampos(String codigo, String descricao, BigDecimal preco, Integer quantidade) throws ValidationException {
        if (codigo.isEmpty() || descricao.isEmpty() || preco.compareTo(BigDecimal.ZERO) <= 0 || quantidade <= 0) {
            throw new ValidationException("Valores inválidos");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProdutoView().setVisible(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        painelComAbasProduto = new javax.swing.JTabbedPane();
        painelConsultaProdutos = new javax.swing.JPanel();
        campoNomePesquisaProduto = new javax.swing.JLabel();
        textoDescricaoPesquisaProduto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaExibicaoProdutos = new javax.swing.JTable();
        painelDadosProduto = new javax.swing.JPanel();
        campoDescricaoProduto = new javax.swing.JLabel();
        textoQuantidade = new javax.swing.JTextField();
        campoQuantidadeProduto = new javax.swing.JLabel();
        textoDescricaoProduto = new javax.swing.JTextField();
        campoNcmProduto = new javax.swing.JLabel();
        textoNcmProduto = new javax.swing.JTextField();
        campoCodigoProduto = new javax.swing.JLabel();
        textoCodigoProduto = new javax.swing.JTextField();
        campoPrecoProduto = new javax.swing.JLabel();
        textoPrecoProduto = new javax.swing.JTextField();
        botaoEditar = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        botaoNovo = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro Consulta Produtos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Cadastro de Produtos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(324, 324, 324)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(17, 17, 17))
        );

        painelComAbasProduto.setBackground(new java.awt.Color(255, 255, 255));
        painelComAbasProduto.setForeground(new java.awt.Color(0, 0, 0));
        painelComAbasProduto.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        painelConsultaProdutos.setBackground(new java.awt.Color(204, 204, 204));
        painelConsultaProdutos.setMaximumSize(new java.awt.Dimension(1080, 1024));

        campoNomePesquisaProduto.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        campoNomePesquisaProduto.setForeground(new java.awt.Color(0, 0, 0));
        campoNomePesquisaProduto.setText(" Descrição");
        campoNomePesquisaProduto.setBorder(new javax.swing.border.MatteBorder(null));

        textoDescricaoPesquisaProduto.setBackground(new java.awt.Color(255, 255, 255));
        textoDescricaoPesquisaProduto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoDescricaoPesquisaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoDescricaoPesquisaProdutoActionPerformed(evt);
            }
        });
        textoDescricaoPesquisaProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoDescricaoPesquisaProdutoKeyPressed(evt);
            }
        });

        tabelaExibicaoProdutos.setBackground(new java.awt.Color(255, 255, 255));
        tabelaExibicaoProdutos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tabelaExibicaoProdutos.setForeground(new java.awt.Color(0, 0, 0));
        tabelaExibicaoProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Descrição", "Preço", "Ncm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaExibicaoProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaExibicaoProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaExibicaoProdutos);
        if (tabelaExibicaoProdutos.getColumnModel().getColumnCount() > 0) {
            tabelaExibicaoProdutos.getColumnModel().getColumn(1).setPreferredWidth(500);
        }

        javax.swing.GroupLayout painelConsultaProdutosLayout = new javax.swing.GroupLayout(painelConsultaProdutos);
        painelConsultaProdutos.setLayout(painelConsultaProdutosLayout);
        painelConsultaProdutosLayout.setHorizontalGroup(
            painelConsultaProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelConsultaProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                    .addGroup(painelConsultaProdutosLayout.createSequentialGroup()
                        .addComponent(campoNomePesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoDescricaoPesquisaProduto)))
                .addContainerGap())
        );
        painelConsultaProdutosLayout.setVerticalGroup(
            painelConsultaProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelConsultaProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelConsultaProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelConsultaProdutosLayout.createSequentialGroup()
                        .addComponent(campoNomePesquisaProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(9, 9, 9))
                    .addGroup(painelConsultaProdutosLayout.createSequentialGroup()
                        .addComponent(textoDescricaoPesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        painelComAbasProduto.addTab("Consulta de Produtos", painelConsultaProdutos);

        painelDadosProduto.setBackground(new java.awt.Color(204, 204, 204));

        campoDescricaoProduto.setBackground(new java.awt.Color(204, 204, 204));
        campoDescricaoProduto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoDescricaoProduto.setForeground(new java.awt.Color(0, 0, 0));
        campoDescricaoProduto.setText("Descrição");
        campoDescricaoProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        textoQuantidade.setBackground(new java.awt.Color(255, 255, 255));
        textoQuantidade.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        textoQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoQuantidadeActionPerformed(evt);
            }
        });

        campoQuantidadeProduto.setBackground(new java.awt.Color(204, 204, 204));
        campoQuantidadeProduto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoQuantidadeProduto.setForeground(new java.awt.Color(0, 0, 0));
        campoQuantidadeProduto.setText("Quantidade (estoque)");
        campoQuantidadeProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        textoDescricaoProduto.setBackground(new java.awt.Color(255, 255, 255));
        textoDescricaoProduto.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        textoDescricaoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoDescricaoProdutoActionPerformed(evt);
            }
        });

        campoNcmProduto.setBackground(new java.awt.Color(204, 204, 204));
        campoNcmProduto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoNcmProduto.setForeground(new java.awt.Color(0, 0, 0));
        campoNcmProduto.setText("NCM");
        campoNcmProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        textoNcmProduto.setBackground(new java.awt.Color(255, 255, 255));
        textoNcmProduto.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        textoNcmProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoNcmProdutoActionPerformed(evt);
            }
        });

        campoCodigoProduto.setBackground(new java.awt.Color(204, 204, 204));
        campoCodigoProduto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoCodigoProduto.setForeground(new java.awt.Color(0, 0, 0));
        campoCodigoProduto.setText("Código");
        campoCodigoProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        textoCodigoProduto.setBackground(new java.awt.Color(255, 255, 255));
        textoCodigoProduto.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        textoCodigoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoCodigoProdutoActionPerformed(evt);
            }
        });

        campoPrecoProduto.setBackground(new java.awt.Color(204, 204, 204));
        campoPrecoProduto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoPrecoProduto.setForeground(new java.awt.Color(0, 0, 0));
        campoPrecoProduto.setText("Preço");
        campoPrecoProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        textoPrecoProduto.setBackground(new java.awt.Color(255, 255, 255));
        textoPrecoProduto.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        textoPrecoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoPrecoProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelDadosProdutoLayout = new javax.swing.GroupLayout(painelDadosProduto);
        painelDadosProduto.setLayout(painelDadosProdutoLayout);
        painelDadosProdutoLayout.setHorizontalGroup(
            painelDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoPrecoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoDescricaoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(campoCodigoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelDadosProdutoLayout.createSequentialGroup()
                        .addComponent(textoPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campoQuantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelDadosProdutoLayout.createSequentialGroup()
                        .addComponent(textoCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoNcmProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoNcmProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(textoDescricaoProduto, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        painelDadosProdutoLayout.setVerticalGroup(
            painelDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNcmProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNcmProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(painelDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoDescricaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoDescricaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(painelDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoQuantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(225, Short.MAX_VALUE))
        );

        painelDadosProdutoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {campoCodigoProduto, campoDescricaoProduto, campoNcmProduto, campoPrecoProduto, campoQuantidadeProduto});

        painelComAbasProduto.addTab("Cadastro/Manutenção Produtos", painelDadosProduto);

        botaoEditar.setText("EDITAR");
        botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarActionPerformed(evt);
            }
        });

        botaoSalvar.setText("SALVAR");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoNovo.setText("NOVO");
        botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoActionPerformed(evt);
            }
        });

        botaoLimpar.setText("LIMPAR");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        botaoExcluir.setText("EXCLUIR");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelComAbasProduto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoEditar, botaoLimpar, botaoNovo, botaoSalvar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelComAbasProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botaoEditar, botaoLimpar, botaoNovo, botaoSalvar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEditar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JLabel campoCodigoProduto;
    private javax.swing.JLabel campoDescricaoProduto;
    private javax.swing.JLabel campoNcmProduto;
    private javax.swing.JLabel campoNomePesquisaProduto;
    private javax.swing.JLabel campoPrecoProduto;
    private javax.swing.JLabel campoQuantidadeProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTabbedPane painelComAbasProduto;
    public javax.swing.JPanel painelConsultaProdutos;
    private javax.swing.JPanel painelDadosProduto;
    private javax.swing.JTable tabelaExibicaoProdutos;
    private javax.swing.JTextField textoCodigoProduto;
    private javax.swing.JTextField textoDescricaoPesquisaProduto;
    private javax.swing.JTextField textoDescricaoProduto;
    private javax.swing.JTextField textoNcmProduto;
    private javax.swing.JTextField textoPrecoProduto;
    private javax.swing.JTextField textoQuantidade;
    // End of variables declaration//GEN-END:variables
}
