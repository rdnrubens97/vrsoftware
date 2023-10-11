/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.vrs.projetovrs.view;

import com.vrs.projetovrs.dao.VendaDao;
import com.vrs.projetovrs.model.Venda;
import com.vrs.projetovrs.model.status.Status;
import com.vrs.projetovrs.service.VendaService;

import javax.swing.*;

/**
 * @author Rubens
 */
public class HistoricoVendasDetalhadoView extends javax.swing.JFrame {
    private VendaService vendaService;
    public Integer idVenda;

    /**
     * Creates new form HistoricoVendasView
     */
    public HistoricoVendasDetalhadoView() {
        initComponents();
        vendaService = new VendaService(new VendaDao());
    }

    /**
     * Este método é acionado quando o botão "Estornar Venda" é clicado na janela de detalhes da venda.
     * Ele realiza o estorno de uma venda com base no ID da venda e atualiza o status da venda para "DIGITANDO".
     * Também exibe uma mensagem de sucesso ao usuário.
     */
    private void botaoEstornarVendaDetalhesVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEstornarVendaDetalhesVendaActionPerformed
        int op;
        op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja cancelar essa venda?");
        if (op == 0) {
            vendaService.estornarVendaPorId(this.idVenda, Status.CANCELADA);
            JOptionPane.showMessageDialog(null, "Venda com id " + this.idVenda + " cancelada com sucesso");
        }
    }//GEN-LAST:event_botaoEstornarVendaDetalhesVendaActionPerformed

    private void textoTotalHistoricoVendaDetalhadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoTotalHistoricoVendaDetalhadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoTotalHistoricoVendaDetalhadaActionPerformed

    private void textoTotalHistoricoVendaDetalhadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoTotalHistoricoVendaDetalhadaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoTotalHistoricoVendaDetalhadaKeyPressed

    private void textoClienteHistoricoVendaDetalhadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoClienteHistoricoVendaDetalhadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoClienteHistoricoVendaDetalhadaActionPerformed

    private void textoClienteHistoricoVendaDetalhadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoClienteHistoricoVendaDetalhadaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoClienteHistoricoVendaDetalhadaKeyPressed

    private void textoDataHistoricoVendaDetalhadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoDataHistoricoVendaDetalhadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoDataHistoricoVendaDetalhadaActionPerformed

    private void textoDataHistoricoVendaDetalhadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoDataHistoricoVendaDetalhadaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoDataHistoricoVendaDetalhadaKeyPressed

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
            java.util.logging.Logger.getLogger(HistoricoVendasDetalhadoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistoricoVendasDetalhadoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistoricoVendasDetalhadoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistoricoVendasDetalhadoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HistoricoVendasDetalhadoView().setVisible(true);
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

        painelTituloHistoricoVendas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        painelSelecaoDatasHistoricoVendasDetalhada = new javax.swing.JPanel();
        campoDataHistoricoVendaDetalhada = new javax.swing.JLabel();
        campoTotalHistoricoVendaDetalhada = new javax.swing.JLabel();
        textoTotalHistoricoVendaDetalhada = new javax.swing.JTextField();
        campoClienteHistoricoVendaDetalhada = new javax.swing.JLabel();
        textoClienteHistoricoVendaDetalhada = new javax.swing.JTextField();
        textoDataHistoricoVendaDetalhada = new javax.swing.JTextField();
        botaoEstornarVendaDetalhesVenda = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaItensVendidosHistoricoVendasDetalhada = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaServicosHistoricoVendasDetalhada = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalhes da Venda");

        painelTituloHistoricoVendas.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Detalhes da Venda");

        javax.swing.GroupLayout painelTituloHistoricoVendasLayout = new javax.swing.GroupLayout(painelTituloHistoricoVendas);
        painelTituloHistoricoVendas.setLayout(painelTituloHistoricoVendasLayout);
        painelTituloHistoricoVendasLayout.setHorizontalGroup(
            painelTituloHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTituloHistoricoVendasLayout.createSequentialGroup()
                .addGap(373, 373, 373)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelTituloHistoricoVendasLayout.setVerticalGroup(
            painelTituloHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelTituloHistoricoVendasLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        painelSelecaoDatasHistoricoVendasDetalhada.setBackground(new java.awt.Color(102, 102, 102));
        painelSelecaoDatasHistoricoVendasDetalhada.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        campoDataHistoricoVendaDetalhada.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoDataHistoricoVendaDetalhada.setForeground(new java.awt.Color(255, 255, 255));
        campoDataHistoricoVendaDetalhada.setText("Data");
        campoDataHistoricoVendaDetalhada.setBorder(new javax.swing.border.MatteBorder(null));

        campoTotalHistoricoVendaDetalhada.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoTotalHistoricoVendaDetalhada.setForeground(new java.awt.Color(255, 255, 255));
        campoTotalHistoricoVendaDetalhada.setText(" Total da Venda");
        campoTotalHistoricoVendaDetalhada.setBorder(new javax.swing.border.MatteBorder(null));

        textoTotalHistoricoVendaDetalhada.setEditable(false);
        textoTotalHistoricoVendaDetalhada.setBackground(new java.awt.Color(255, 255, 255));
        textoTotalHistoricoVendaDetalhada.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textoTotalHistoricoVendaDetalhada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoTotalHistoricoVendaDetalhada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoTotalHistoricoVendaDetalhadaActionPerformed(evt);
            }
        });
        textoTotalHistoricoVendaDetalhada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoTotalHistoricoVendaDetalhadaKeyPressed(evt);
            }
        });

        campoClienteHistoricoVendaDetalhada.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoClienteHistoricoVendaDetalhada.setForeground(new java.awt.Color(255, 255, 255));
        campoClienteHistoricoVendaDetalhada.setText("Cliente");
        campoClienteHistoricoVendaDetalhada.setBorder(new javax.swing.border.MatteBorder(null));

        textoClienteHistoricoVendaDetalhada.setEditable(false);
        textoClienteHistoricoVendaDetalhada.setBackground(new java.awt.Color(255, 255, 255));
        textoClienteHistoricoVendaDetalhada.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textoClienteHistoricoVendaDetalhada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoClienteHistoricoVendaDetalhada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoClienteHistoricoVendaDetalhadaActionPerformed(evt);
            }
        });
        textoClienteHistoricoVendaDetalhada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoClienteHistoricoVendaDetalhadaKeyPressed(evt);
            }
        });

        textoDataHistoricoVendaDetalhada.setEditable(false);
        textoDataHistoricoVendaDetalhada.setBackground(new java.awt.Color(255, 255, 255));
        textoDataHistoricoVendaDetalhada.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textoDataHistoricoVendaDetalhada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoDataHistoricoVendaDetalhada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoDataHistoricoVendaDetalhadaActionPerformed(evt);
            }
        });
        textoDataHistoricoVendaDetalhada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoDataHistoricoVendaDetalhadaKeyPressed(evt);
            }
        });

        botaoEstornarVendaDetalhesVenda.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        botaoEstornarVendaDetalhesVenda.setText("Cancelar essa venda");
        botaoEstornarVendaDetalhesVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEstornarVendaDetalhesVendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelSelecaoDatasHistoricoVendasDetalhadaLayout = new javax.swing.GroupLayout(painelSelecaoDatasHistoricoVendasDetalhada);
        painelSelecaoDatasHistoricoVendasDetalhada.setLayout(painelSelecaoDatasHistoricoVendasDetalhadaLayout);
        painelSelecaoDatasHistoricoVendasDetalhadaLayout.setHorizontalGroup(
            painelSelecaoDatasHistoricoVendasDetalhadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSelecaoDatasHistoricoVendasDetalhadaLayout.createSequentialGroup()
                .addComponent(campoDataHistoricoVendaDetalhada, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoDataHistoricoVendaDetalhada, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoClienteHistoricoVendaDetalhada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoClienteHistoricoVendaDetalhada, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(campoTotalHistoricoVendaDetalhada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoTotalHistoricoVendaDetalhada, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(botaoEstornarVendaDetalhesVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        painelSelecaoDatasHistoricoVendasDetalhadaLayout.setVerticalGroup(
            painelSelecaoDatasHistoricoVendasDetalhadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelSelecaoDatasHistoricoVendasDetalhadaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelSelecaoDatasHistoricoVendasDetalhadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoDataHistoricoVendaDetalhada, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTotalHistoricoVendaDetalhada, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoTotalHistoricoVendaDetalhada, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoDataHistoricoVendaDetalhada, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEstornarVendaDetalhesVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoClienteHistoricoVendaDetalhada, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoClienteHistoricoVendaDetalhada, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabelaItensVendidosHistoricoVendasDetalhada.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        tabelaItensVendidosHistoricoVendasDetalhada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Produto", "Quantidade", "Preço", "Subtotal Item"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaItensVendidosHistoricoVendasDetalhada);
        if (tabelaItensVendidosHistoricoVendasDetalhada.getColumnModel().getColumnCount() > 0) {
            tabelaItensVendidosHistoricoVendasDetalhada.getColumnModel().getColumn(1).setPreferredWidth(400);
        }

        tabelaServicosHistoricoVendasDetalhada.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        tabelaServicosHistoricoVendasDetalhada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição do Serviço", "Preço Serviço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelaServicosHistoricoVendasDetalhada);
        if (tabelaServicosHistoricoVendasDetalhada.getColumnModel().getColumnCount() > 0) {
            tabelaServicosHistoricoVendasDetalhada.getColumnModel().getColumn(0).setPreferredWidth(800);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelSelecaoDatasHistoricoVendasDetalhada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(painelTituloHistoricoVendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelTituloHistoricoVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelSelecaoDatasHistoricoVendasDetalhada, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane2});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEstornarVendaDetalhesVenda;
    private javax.swing.JLabel campoClienteHistoricoVendaDetalhada;
    private javax.swing.JLabel campoDataHistoricoVendaDetalhada;
    private javax.swing.JLabel campoTotalHistoricoVendaDetalhada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel painelSelecaoDatasHistoricoVendasDetalhada;
    private javax.swing.JPanel painelTituloHistoricoVendas;
    public javax.swing.JTable tabelaItensVendidosHistoricoVendasDetalhada;
    public javax.swing.JTable tabelaServicosHistoricoVendasDetalhada;
    public javax.swing.JTextField textoClienteHistoricoVendaDetalhada;
    public javax.swing.JTextField textoDataHistoricoVendaDetalhada;
    public javax.swing.JTextField textoTotalHistoricoVendaDetalhada;
    // End of variables declaration//GEN-END:variables
}
