/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.vrs.projetovrs.view;

import com.vrs.projetovrs.dao.ItemVendaDao;
import com.vrs.projetovrs.dao.ProdutoDao;
import com.vrs.projetovrs.dao.ServicoDao;
import com.vrs.projetovrs.dao.VendaDao;
import com.vrs.projetovrs.model.*;
import com.vrs.projetovrs.model.status.Status;
import com.vrs.projetovrs.service.ItemVendaService;
import com.vrs.projetovrs.service.ProdutoService;
import com.vrs.projetovrs.service.ServicoService;
import com.vrs.projetovrs.service.VendaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Rubens
 */
public class PagamentosView extends javax.swing.JFrame {
    private VendaService vendaService;
    private ProdutoService produtoService;
    private ItemVendaService itemVendaService;
    private ServicoService servicoService;
    Cliente clientePagamentoView = new Cliente();
    DefaultTableModel carrinhoDeComprasPagamentoView;
    DefaultTableModel tabelaMaoDeObraPdvPagamentoView;

    /**
     * Creates new form PagamentosView
     */
    public PagamentosView() {
        initComponents();
        carrinhoDeComprasPagamentoView = (DefaultTableModel) new DefaultTableModel();
        tabelaMaoDeObraPdvPagamentoView = (DefaultTableModel) new DefaultTableModel();
        setLocationRelativeTo(null);
        vendaService = new VendaService(new VendaDao());
        produtoService = new ProdutoService(new ProdutoDao());
        itemVendaService = new ItemVendaService(new ItemVendaDao());
        servicoService = new ServicoService(new ServicoDao());
    }

    /**
     * Este método é acionado quando o botão "Finalizar Venda" é clicado. Realiza o cálculo do troco, registra a venda no sistema
     * e atualiza o estoque dos produtos vendidos.
     */
    private void botaoFinalizarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFinalizarVendaActionPerformed
        Date agora = new Date();
        BigDecimal pagCartao, pagDinheiro, totalPago, totalVenda, troco, totalMaoDeObra;
        pagCartao = !textoCartaoPagamento.getText().equals("") ? new BigDecimal(textoCartaoPagamento.getText().replace(",", ".")) : BigDecimal.ZERO;
        pagDinheiro = !textoDinheiroPagamento.getText().equals("") ? new BigDecimal(textoDinheiroPagamento.getText().replace(",", ".")) : BigDecimal.ZERO;
        totalVenda = new BigDecimal(textoTotalPagamento.getText());

        totalPago = pagCartao.add(pagDinheiro);
        troco = totalPago.subtract(totalVenda);
        textoTrocoPagamento.setText(troco.toString());

        if (troco.compareTo(BigDecimal.ZERO) >= 0) {
            Venda venda = new Venda();

            venda.setData(agora);
            venda.setCliente(clientePagamentoView);
            venda.setValorTotal(totalVenda);
            venda.setStatus(Status.EFETIVADA);

            vendaService.cadastrarVenda(venda);
            venda.setId(vendaService.retornarIdDaUltimaVenda());

            for (int i = 0; i < carrinhoDeComprasPagamentoView.getRowCount(); i++) {
                Integer quantidadeEstoque, quantidadeComprada, quantidadeAtualizada;
                Produto produto = new Produto();
                String codigoPeca = carrinhoDeComprasPagamentoView.getValueAt(i, 0).toString();
                Integer id = produtoService.buscarIdPorCodigo(codigoPeca);
                produto.setId(id);

                ItemVenda item = new ItemVenda();
                item.setVenda(venda);
                item.setProduto(produto);
                item.setQuantidade(Integer.parseInt(carrinhoDeComprasPagamentoView.getValueAt(i, 2).toString()));
                item.getProduto().setPreco(new BigDecimal(carrinhoDeComprasPagamentoView.getValueAt(i, 3).toString().replace("R$ ", "")));
                item.setValorTotal(new BigDecimal(carrinhoDeComprasPagamentoView.getValueAt(i, 4).toString()));

                quantidadeEstoque = produtoService.retornaEstoqueAtual(produto.getId());
                quantidadeComprada = item.getQuantidade();
                quantidadeAtualizada = quantidadeEstoque - quantidadeComprada;

                produtoService.baixarEstoque(produto.getId(), quantidadeAtualizada);
                itemVendaService.cadastrarItem(item);
            }

            for (int i = 0; i < tabelaMaoDeObraPdvPagamentoView.getRowCount(); i++) {
                String descricao = tabelaMaoDeObraPdvPagamentoView.getValueAt(i, 0).toString();
                BigDecimal preco = new BigDecimal(tabelaMaoDeObraPdvPagamentoView.getValueAt(i, 1).toString());

                Servico servico = new Servico();
                servico.setIdVenda(venda.getId());
                servico.setDescricao(descricao);
                servico.setPreco(preco);
                servicoService.salvarServico(servico);
            }

            JOptionPane.showMessageDialog(null, "Venda registrada com sucesso. \nTroco: R$ " + troco);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Valor insuficiente!");
        }

    }//GEN-LAST:event_botaoFinalizarVendaActionPerformed

    private void textoTrocoPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoTrocoPagamentoActionPerformed
    }//GEN-LAST:event_textoTrocoPagamentoActionPerformed

    private void textoTotalPagamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoTotalPagamentoKeyPressed
    }//GEN-LAST:event_textoTotalPagamentoKeyPressed

    private void textoTotalPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoTotalPagamentoActionPerformed
    }//GEN-LAST:event_textoTotalPagamentoActionPerformed

    private void textoDinheiroPagamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoDinheiroPagamentoKeyPressed
    }//GEN-LAST:event_textoDinheiroPagamentoKeyPressed

    private void textoDinheiroPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoDinheiroPagamentoActionPerformed
    }//GEN-LAST:event_textoDinheiroPagamentoActionPerformed

    private void textoCartaoPagamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoCartaoPagamentoKeyPressed
    }//GEN-LAST:event_textoCartaoPagamentoKeyPressed

    private void textoCartaoPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoCartaoPagamentoActionPerformed
    }//GEN-LAST:event_textoCartaoPagamentoActionPerformed

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
            java.util.logging.Logger.getLogger(PagamentosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PagamentosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PagamentosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PagamentosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PagamentosView().setVisible(true);
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
        jPanel2 = new javax.swing.JPanel();
        campoDinheiroPagamento = new javax.swing.JLabel();
        textoCartaoPagamento = new javax.swing.JTextField();
        campoTrocoPagamento = new javax.swing.JLabel();
        textoDinheiroPagamento = new javax.swing.JTextField();
        campoCartaoPagamento = new javax.swing.JLabel();
        textoTotalPagamento = new javax.swing.JTextField();
        campoTotalPagamento = new javax.swing.JLabel();
        textoTrocoPagamento = new javax.swing.JTextField();
        botaoFinalizarVenda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pagamento");

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Pagamento");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        campoDinheiroPagamento.setBackground(new java.awt.Color(0, 0, 0));
        campoDinheiroPagamento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoDinheiroPagamento.setForeground(new java.awt.Color(0, 0, 0));
        campoDinheiroPagamento.setText("DINHEIRO");
        campoDinheiroPagamento.setBorder(new javax.swing.border.MatteBorder(null));

        textoCartaoPagamento.setBackground(new java.awt.Color(255, 255, 255));
        textoCartaoPagamento.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        textoCartaoPagamento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoCartaoPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoCartaoPagamentoActionPerformed(evt);
            }
        });
        textoCartaoPagamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoCartaoPagamentoKeyPressed(evt);
            }
        });

        campoTrocoPagamento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoTrocoPagamento.setForeground(new java.awt.Color(0, 0, 0));
        campoTrocoPagamento.setText("TROCO");
        campoTrocoPagamento.setBorder(new javax.swing.border.MatteBorder(null));

        textoDinheiroPagamento.setBackground(new java.awt.Color(255, 255, 255));
        textoDinheiroPagamento.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        textoDinheiroPagamento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoDinheiroPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoDinheiroPagamentoActionPerformed(evt);
            }
        });
        textoDinheiroPagamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoDinheiroPagamentoKeyPressed(evt);
            }
        });

        campoCartaoPagamento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoCartaoPagamento.setForeground(new java.awt.Color(0, 0, 0));
        campoCartaoPagamento.setText("CARTÃO");
        campoCartaoPagamento.setBorder(new javax.swing.border.MatteBorder(null));

        textoTotalPagamento.setEditable(false);
        textoTotalPagamento.setBackground(new java.awt.Color(255, 255, 255));
        textoTotalPagamento.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        textoTotalPagamento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoTotalPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoTotalPagamentoActionPerformed(evt);
            }
        });
        textoTotalPagamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoTotalPagamentoKeyPressed(evt);
            }
        });

        campoTotalPagamento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoTotalPagamento.setForeground(new java.awt.Color(0, 0, 0));
        campoTotalPagamento.setText("TOTAL");
        campoTotalPagamento.setBorder(new javax.swing.border.MatteBorder(null));

        textoTrocoPagamento.setEditable(false);
        textoTrocoPagamento.setBackground(new java.awt.Color(255, 255, 255));
        textoTrocoPagamento.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        textoTrocoPagamento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoTrocoPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoTrocoPagamentoActionPerformed(evt);
            }
        });
        textoTrocoPagamento.addKeyListener(new java.awt.event.KeyAdapter() {
        });

        botaoFinalizarVenda.setText("FINALIZAR VENDA");
        botaoFinalizarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFinalizarVendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(campoTrocoPagamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(campoCartaoPagamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(campoDinheiroPagamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                                        .addComponent(campoTotalPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 28, Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(textoCartaoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textoDinheiroPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textoTotalPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textoTrocoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(135, 135, 135)
                                                .addComponent(botaoFinalizarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(33, 33, 33))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(textoDinheiroPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                                .addGap(1, 1, 1))
                                        .addComponent(campoDinheiroPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(campoCartaoPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                                        .addComponent(textoCartaoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(campoTrocoPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                                        .addComponent(textoTrocoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(campoTotalPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textoTotalPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoFinalizarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(145, 145, 145))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFinalizarVenda;
    private javax.swing.JLabel campoCartaoPagamento;
    private javax.swing.JLabel campoDinheiroPagamento;
    private javax.swing.JLabel campoTotalPagamento;
    private javax.swing.JLabel campoTrocoPagamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField textoCartaoPagamento;
    private javax.swing.JTextField textoDinheiroPagamento;
    public javax.swing.JTextField textoTotalPagamento;
    private javax.swing.JTextField textoTrocoPagamento;
    // End of variables declaration//GEN-END:variables
}
