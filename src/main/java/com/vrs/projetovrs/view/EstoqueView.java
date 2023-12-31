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
import java.util.List;

/**
 * @author Rubens
 */
public class EstoqueView extends javax.swing.JFrame {
    Integer idProduto;
    private ProdutoService produtoService;


    /**
     * Creates new form EstoqueView
     */
    public EstoqueView() {
        initComponents();
        setLocationRelativeTo(null);
        produtoService = new ProdutoService(new ProdutoDao());
    }

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        listarProdutosNaTabela();
    }//GEN-LAST:event_formWindowActivated

    /**
     * Este método preenche a tabela de estoque na interface de usuário com a lista de produtos obtida do banco de dados.
     */
    public void listarProdutosNaTabela() {
        List<Produto> lista = produtoService.listarProdutos();
        DefaultTableModel dados = (DefaultTableModel) tabelaEstoque.getModel();
        dados.setNumRows(0);
        for (Produto produto : lista) {
            dados.addRow(new Object[]{
                    produto.getId(),
                    produto.getDescricao(),
                    "R$ " + produto.getPreco(),
                    produto.getQuandidade()
            });
        }
    }

    /**
     * Este método é chamado quando o botão "Pesquisar" é clicado e realiza uma busca por produtos com base na descrição fornecida.
     * Ele atualiza a tabela de estoque com os resultados da pesquisa.
     */
    private void botaoPesquisarEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarEstoqueActionPerformed
        String nomeDesejado = textoDescricaoEstoque.getText();
        List<Produto> lista = produtoService.listarProdutosPorNome(nomeDesejado);
        DefaultTableModel dados = (DefaultTableModel) tabelaEstoque.getModel();
        dados.setNumRows(0);
        for (Produto produto : lista) {
            dados.addRow(new Object[]{
                    produto.getId(),
                    produto.getDescricao(),
                    produto.getPreco(),
                    produto.getQuandidade()
            });
        }
    }//GEN-LAST:event_botaoPesquisarEstoqueActionPerformed

    /**
     * Este método é chamado quando o botão "Adicionar" é clicado e adiciona a quantidade especificada de um produto ao estoque.
     * Ele realiza validações e atualiza o estoque no banco de dados.
     */
    private void botaoAdicionarEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarEstoqueActionPerformed
        try {
            String descricao = textoDescricaoEstoque.getText();
            int quantidadeJaExistenteNoEstoque = produtoService.retornaEstoqueAtual(this.idProduto);
            int quantidadeNovaASerAdicionada = Integer.parseInt(textoQuantidadeASerAdicionadaEstoque.getText());
            int quantidadeNova = quantidadeJaExistenteNoEstoque + quantidadeNovaASerAdicionada;

            validarCampos(this.idProduto, descricao, quantidadeNovaASerAdicionada);
            produtoService.adicionarEstoque(this.idProduto, quantidadeNova);
            JOptionPane.showMessageDialog(null, "Estoque atualizado com sucesso");

            new Utilities().limpaCampos(painelDadosEstoque);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selecione um produto e informe uma quantidade válida");
            e.printStackTrace();
        }
    }//GEN-LAST:event_botaoAdicionarEstoqueActionPerformed

    /**
     * Este método é chamado quando o usuário clica em uma linha da tabela de estoque.
     * Ele obtém informações sobre o produto selecionado e preenche os campos relevantes na interface de usuário.
     */
    private void tabelaEstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaEstoqueMouseClicked
        this.idProduto = Integer.parseInt(tabelaEstoque.getValueAt(tabelaEstoque.getSelectedRow(), 0).toString());
        textoDescricaoEstoque.setText(tabelaEstoque.getValueAt(tabelaEstoque.getSelectedRow(), 1).toString());
        textoEstoqueAtualEstoque.setText(tabelaEstoque.getValueAt(tabelaEstoque.getSelectedRow(), 3).toString());
    }//GEN-LAST:event_tabelaEstoqueMouseClicked

    /**
     * Este método realiza validações nos campos fornecidos, como o ID do produto, descrição e quantidade.
     * Se algum valor não atender aos critérios, uma exceção de validação é lançada.
     *
     * @param idProduto O ID do produto a ser validado.
     * @param descricao A descrição do produto a ser validada.
     * @param quantidade A quantidade a ser validada.
     * @throws ValidationException Se algum dos valores não atender aos critérios de validação.
     */
    private void validarCampos(Integer idProduto ,String descricao, Integer quantidade) throws ValidationException {
        if (idProduto == null || descricao.isEmpty() || quantidade <= 0 ) {
            throw new ValidationException();
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
            java.util.logging.Logger.getLogger(EstoqueView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EstoqueView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EstoqueView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EstoqueView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EstoqueView().setVisible(true);
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

        painelTituloEstoque = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        painelDadosEstoque = new javax.swing.JPanel();
        campoDescricaoEstoque = new javax.swing.JLabel();
        textoEstoqueAtualEstoque = new javax.swing.JTextField();
        campoQuantidadeEstoque = new javax.swing.JLabel();
        textoDescricaoEstoque = new javax.swing.JTextField();
        campoEstoqueAtualEstoque = new javax.swing.JLabel();
        textoQuantidadeASerAdicionadaEstoque = new javax.swing.JTextField();
        botaoAdicionarEstoque = new javax.swing.JButton();
        botaoPesquisarEstoque = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEstoque = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Controle Estoque");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        painelTituloEstoque.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Controle de Estoque de Produtos");

        javax.swing.GroupLayout painelTituloEstoqueLayout = new javax.swing.GroupLayout(painelTituloEstoque);
        painelTituloEstoque.setLayout(painelTituloEstoqueLayout);
        painelTituloEstoqueLayout.setHorizontalGroup(
            painelTituloEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTituloEstoqueLayout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelTituloEstoqueLayout.setVerticalGroup(
            painelTituloEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelTituloEstoqueLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(14, 14, 14))
        );

        painelDadosEstoque.setBackground(new java.awt.Color(255, 255, 255));
        painelDadosEstoque.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consulta de Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        painelDadosEstoque.setForeground(new java.awt.Color(0, 0, 0));

        campoDescricaoEstoque.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        campoDescricaoEstoque.setForeground(new java.awt.Color(0, 0, 0));
        campoDescricaoEstoque.setText("  Descrição");
        campoDescricaoEstoque.setBorder(new javax.swing.border.MatteBorder(null));

        textoEstoqueAtualEstoque.setEditable(false);
        textoEstoqueAtualEstoque.setBackground(new java.awt.Color(204, 204, 204));
        textoEstoqueAtualEstoque.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        campoQuantidadeEstoque.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        campoQuantidadeEstoque.setForeground(new java.awt.Color(0, 0, 0));
        campoQuantidadeEstoque.setText("  Quantidade a ser adicionada");
        campoQuantidadeEstoque.setBorder(new javax.swing.border.MatteBorder(null));

        textoDescricaoEstoque.setBackground(new java.awt.Color(204, 204, 204));
        textoDescricaoEstoque.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        campoEstoqueAtualEstoque.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        campoEstoqueAtualEstoque.setForeground(new java.awt.Color(0, 0, 0));
        campoEstoqueAtualEstoque.setText("  Estoque Atual");
        campoEstoqueAtualEstoque.setBorder(new javax.swing.border.MatteBorder(null));

        textoQuantidadeASerAdicionadaEstoque.setBackground(new java.awt.Color(204, 204, 204));
        textoQuantidadeASerAdicionadaEstoque.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        botaoAdicionarEstoque.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        botaoAdicionarEstoque.setText("Adicionar");
        botaoAdicionarEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarEstoqueActionPerformed(evt);
            }
        });

        botaoPesquisarEstoque.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        botaoPesquisarEstoque.setText("Pesquisar");
        botaoPesquisarEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarEstoqueActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Selecione o tem que deve ter a quantidade em estoque alterada");

        javax.swing.GroupLayout painelDadosEstoqueLayout = new javax.swing.GroupLayout(painelDadosEstoque);
        painelDadosEstoque.setLayout(painelDadosEstoqueLayout);
        painelDadosEstoqueLayout.setHorizontalGroup(
            painelDadosEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosEstoqueLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosEstoqueLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(painelDadosEstoqueLayout.createSequentialGroup()
                        .addGroup(painelDadosEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDadosEstoqueLayout.createSequentialGroup()
                                .addComponent(campoEstoqueAtualEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoEstoqueAtualEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoQuantidadeEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textoQuantidadeASerAdicionadaEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelDadosEstoqueLayout.createSequentialGroup()
                                .addComponent(campoDescricaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoDescricaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 139, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelDadosEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoAdicionarEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(botaoPesquisarEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))))
                .addContainerGap())
        );
        painelDadosEstoqueLayout.setVerticalGroup(
            painelDadosEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosEstoqueLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoDescricaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoDescricaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoPesquisarEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(painelDadosEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoEstoqueAtualEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoQuantidadeEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoEstoqueAtualEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAdicionarEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoQuantidadeASerAdicionadaEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabelaEstoque.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tabelaEstoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Preço", "Qtd. Estoque"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaEstoqueMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaEstoque);
        if (tabelaEstoque.getColumnModel().getColumnCount() > 0) {
            tabelaEstoque.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelTituloEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(painelDadosEstoque, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelTituloEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelDadosEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionarEstoque;
    private javax.swing.JButton botaoPesquisarEstoque;
    private javax.swing.JLabel campoDescricaoEstoque;
    private javax.swing.JLabel campoEstoqueAtualEstoque;
    private javax.swing.JLabel campoQuantidadeEstoque;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel painelDadosEstoque;
    private javax.swing.JPanel painelTituloEstoque;
    private javax.swing.JTable tabelaEstoque;
    private javax.swing.JTextField textoDescricaoEstoque;
    private javax.swing.JTextField textoEstoqueAtualEstoque;
    private javax.swing.JTextField textoQuantidadeASerAdicionadaEstoque;
    // End of variables declaration//GEN-END:variables
}
