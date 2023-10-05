/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.vrs.projetovrs.view;

import com.vrs.projetovrs.dao.ClienteDao;
import com.vrs.projetovrs.model.Cliente;
import com.vrs.projetovrs.service.ClienteService;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * @author Rubens
 */
public class SelecaoClienteView extends javax.swing.JFrame {
    private ClienteService clienteService;
    private VendaView vendaView;

    /**
     * Creates new form ClienteView
     */
    public SelecaoClienteView(VendaView vendaView) {
        initComponents();
        setLocationRelativeTo(null);
        this.vendaView = vendaView;
        clienteService = new ClienteService(new ClienteDao());
    }

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        listarClienteNaTabela();
    }//GEN-LAST:event_formWindowActivated

    /**
     * lista todos os clientes registrados no banco de dados ta tabela de visualização clientes (tabelaExibicaoClientesLista)
     */
    public void listarClienteNaTabela() {
        List<Cliente> lista = clienteService.listarClientes();
        DefaultTableModel dados = (DefaultTableModel) tabelaExibicaoSelecaoClientesLista.getModel();
        dados.setNumRows(0);
        for (Cliente cliente : lista) {
            dados.addRow(new Object[]{
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getDocumento(),
                    cliente.getCelular(),
                    cliente.getEmail(),
                    cliente.getCidade()
            });
        }
    }

    private void textoNomePesquisaSelecaoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoNomePesquisaSelecaoClienteActionPerformed
    }//GEN-LAST:event_textoNomePesquisaSelecaoClienteActionPerformed

    private void textoNomePesquisaSelecaoClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNomePesquisaSelecaoClienteKeyPressed
        String nomeDesejado = textoNomePesquisaSelecaoCliente.getText();
        List<Cliente> lista = clienteService.listarClientesPorNome(nomeDesejado);
        DefaultTableModel dados = (DefaultTableModel) tabelaExibicaoSelecaoClientesLista.getModel();
        dados.setNumRows(0);
        for (Cliente cliente : lista) {
            dados.addRow(new Object[]{
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getDocumento(),
                    cliente.getCelular(),
                    cliente.getEmail(),
                    cliente.getCidade()
            });
        }
    }//GEN-LAST:event_textoNomePesquisaSelecaoClienteKeyPressed

    private void tabelaExibicaoSelecaoClientesListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaExibicaoSelecaoClientesListaMouseClicked
        String identificadorCliente = tabelaExibicaoSelecaoClientesLista.
                getValueAt(tabelaExibicaoSelecaoClientesLista.getSelectedRow(), 0).toString();
        String nomeCliente = tabelaExibicaoSelecaoClientesLista.getValueAt(tabelaExibicaoSelecaoClientesLista.getSelectedRow(), 1).toString();
        this.vendaView.textoIdentificadorClientePdv.setText(identificadorCliente);
        this.vendaView.textoNomeClientePdv.setText(nomeCliente);
        this.dispose();
    }//GEN-LAST:event_tabelaExibicaoSelecaoClientesListaMouseClicked


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
            java.util.logging.Logger.getLogger(SelecaoClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelecaoClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelecaoClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelecaoClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SelecaoClienteView(new VendaView()).setVisible(true);
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
        textoNomePesquisaSelecaoCliente = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaExibicaoSelecaoClientesLista = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro Clientes");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        painelTituloSelecaoCliente.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nome:");

        textoNomePesquisaSelecaoCliente.setBackground(new java.awt.Color(255, 255, 255));
        textoNomePesquisaSelecaoCliente.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        textoNomePesquisaSelecaoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoNomePesquisaSelecaoClienteActionPerformed(evt);
            }
        });
        textoNomePesquisaSelecaoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoNomePesquisaSelecaoClienteKeyPressed(evt);
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
                .addComponent(textoNomePesquisaSelecaoCliente)
                .addContainerGap())
        );
        painelTituloSelecaoClienteLayout.setVerticalGroup(
            painelTituloSelecaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTituloSelecaoClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelTituloSelecaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoNomePesquisaSelecaoCliente)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        tabelaExibicaoSelecaoClientesLista.setBackground(new java.awt.Color(255, 255, 255));
        tabelaExibicaoSelecaoClientesLista.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tabelaExibicaoSelecaoClientesLista.setForeground(new java.awt.Color(0, 0, 0));
        tabelaExibicaoSelecaoClientesLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identificador", "Nome", "Documento", "Celular", "E-mail", "Cidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaExibicaoSelecaoClientesLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaExibicaoSelecaoClientesListaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaExibicaoSelecaoClientesLista);
        if (tabelaExibicaoSelecaoClientesLista.getColumnModel().getColumnCount() > 0) {
            tabelaExibicaoSelecaoClientesLista.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
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
    public javax.swing.JTable tabelaExibicaoSelecaoClientesLista;
    private javax.swing.JTextField textoNomePesquisaSelecaoCliente;
    // End of variables declaration//GEN-END:variables
}
