/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.vrs.projetovrs.view;

import com.vrs.projetovrs.dao.ProdutoDao;
import com.vrs.projetovrs.model.Produto;
import com.vrs.projetovrs.service.ProdutoService;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * @author Rubens
 */
public class SelecaoProdutoView extends javax.swing.JFrame {
    private ProdutoService produtoService;
    private VendaView vendaView;

    /**
     * Creates new form ClienteView
     */
    public SelecaoProdutoView(VendaView vendaView) {
        initComponents();
        setLocationRelativeTo(null);
        this.vendaView = vendaView;
        produtoService = new ProdutoService(new ProdutoDao());
    }

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        listarProdutosNaTabela();
    }//GEN-LAST:event_formWindowActivated

    /**
     * lista todos os clientes registrados no banco de dados ta tabela de visualização clientes (tabelaExibicaoClientesLista)
     */
    public void listarProdutosNaTabela() {
        List<Produto> lista = produtoService.listarProdutos();
        DefaultTableModel dados = (DefaultTableModel) tabelaExibicaoSelecaoProduto.getModel();
        dados.setNumRows(0);
        for (Produto produto : lista) {
            dados.addRow(new Object[]{
                    produto.getCodigo(),
                    produto.getDescricao(),
                    "R$ " + produto.getPreco(),
                    produto.getQuandidade()
            });
        }
    }

    private void textoNomePesquisaSelecaoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoNomePesquisaSelecaoProdutoActionPerformed
    }//GEN-LAST:event_textoNomePesquisaSelecaoProdutoActionPerformed

    private void textoNomePesquisaSelecaoProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNomePesquisaSelecaoProdutoKeyPressed
        String nomeDesejado = textoNomePesquisaSelecaoProduto.getText();
        List<Produto> lista = produtoService.listarProdutosPorNomeOuCodigo(nomeDesejado);
        DefaultTableModel dados = (DefaultTableModel) tabelaExibicaoSelecaoProduto.getModel();
        dados.setNumRows(0);
        for (Produto produto : lista) {
            dados.addRow(new Object[]{
                    produto.getCodigo(),
                    produto.getDescricao(),
                    produto.getPreco(),
                    produto.getQuandidade()
            });
        }
    }//GEN-LAST:event_textoNomePesquisaSelecaoProdutoKeyPressed

    private void tabelaExibicaoSelecaoProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaExibicaoSelecaoProdutoMouseClicked
        String codigoProduto = tabelaExibicaoSelecaoProduto.
                getValueAt(tabelaExibicaoSelecaoProduto.getSelectedRow(), 0).toString();
        String descricaoProduto = tabelaExibicaoSelecaoProduto.getValueAt(tabelaExibicaoSelecaoProduto.getSelectedRow(), 1).toString();
        String precoProduto = tabelaExibicaoSelecaoProduto.getValueAt(tabelaExibicaoSelecaoProduto.getSelectedRow(), 2).toString();

        this.vendaView.textoIdentificadorProdutoPdv.setText(codigoProduto);
        this.vendaView.textoDescricaoProdutoPdv.setText(descricaoProduto);
        this.vendaView.textoPrecoProdutoPdv.setText(precoProduto);
        this.dispose();
    }//GEN-LAST:event_tabelaExibicaoSelecaoProdutoMouseClicked


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
            java.util.logging.Logger.getLogger(SelecaoProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelecaoProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelecaoProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelecaoProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SelecaoProdutoView(new VendaView()).setVisible(true);
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

        painelTituloSelecaoCliente = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textoNomePesquisaSelecaoProduto = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaExibicaoSelecaoProduto = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro Clientes");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        painelTituloSelecaoCliente.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nome ou Código:");

        textoNomePesquisaSelecaoProduto.setBackground(new java.awt.Color(255, 255, 255));
        textoNomePesquisaSelecaoProduto.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        textoNomePesquisaSelecaoProduto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        textoNomePesquisaSelecaoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoNomePesquisaSelecaoProdutoActionPerformed(evt);
            }
        });
        textoNomePesquisaSelecaoProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoNomePesquisaSelecaoProdutoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout painelTituloSelecaoClienteLayout = new javax.swing.GroupLayout(painelTituloSelecaoCliente);
        painelTituloSelecaoCliente.setLayout(painelTituloSelecaoClienteLayout);
        painelTituloSelecaoClienteLayout.setHorizontalGroup(
            painelTituloSelecaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTituloSelecaoClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoNomePesquisaSelecaoProduto)
                .addContainerGap())
        );
        painelTituloSelecaoClienteLayout.setVerticalGroup(
            painelTituloSelecaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTituloSelecaoClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1))
            .addComponent(textoNomePesquisaSelecaoProduto)
        );

        tabelaExibicaoSelecaoProduto.setBackground(new java.awt.Color(255, 255, 255));
        tabelaExibicaoSelecaoProduto.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tabelaExibicaoSelecaoProduto.setForeground(new java.awt.Color(0, 0, 0));
        tabelaExibicaoSelecaoProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Preço", "Estoque"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaExibicaoSelecaoProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaExibicaoSelecaoProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaExibicaoSelecaoProduto);
        if (tabelaExibicaoSelecaoProduto.getColumnModel().getColumnCount() > 0) {
            tabelaExibicaoSelecaoProduto.getColumnModel().getColumn(0).setMinWidth(110);
            tabelaExibicaoSelecaoProduto.getColumnModel().getColumn(0).setMaxWidth(115);
            tabelaExibicaoSelecaoProduto.getColumnModel().getColumn(1).setPreferredWidth(600);
            tabelaExibicaoSelecaoProduto.getColumnModel().getColumn(2).setMinWidth(110);
            tabelaExibicaoSelecaoProduto.getColumnModel().getColumn(2).setMaxWidth(150);
            tabelaExibicaoSelecaoProduto.getColumnModel().getColumn(3).setMaxWidth(90);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(painelTituloSelecaoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelTituloSelecaoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel painelTituloSelecaoCliente;
    public javax.swing.JTable tabelaExibicaoSelecaoProduto;
    private javax.swing.JTextField textoNomePesquisaSelecaoProduto;
    // End of variables declaration//GEN-END:variables
}
