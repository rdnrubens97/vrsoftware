/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.vrs.projetovrs.view;

import com.vrs.projetovrs.databaseconfig.ConnectionFactory;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author Rubens
 */
public class MenuView extends javax.swing.JFrame {

    /**
     * Creates new form MenuView
     */
    public MenuView() {
        ConnectionFactory conn = new ConnectionFactory();
        conn.verificarEstruturaBanco();
        initComponents();
    }

    /**
     * Este método é acionado quando a janela é ativada.
     * Ele maximiza a janela principal ao ser ativada.
     */
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setVisible(true);
    }//GEN-LAST:event_formWindowActivated

    /**
     * Este método é acionado quando a opção "Controle de Clientes" é selecionada no menu.
     * Ele cria uma instância da classe ClienteView e a torna visível.
     */
    private void menuOpControleDeClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpControleDeClientesActionPerformed
        ClienteView clienteView = new ClienteView();
        clienteView.setVisible(true);
    }//GEN-LAST:event_menuOpControleDeClientesActionPerformed

    /**
     * Este método é acionado quando a opção "Consulta de Produtos" é selecionada no menu.
     * Ele cria uma instância da classe ProdutoView e a torna visível, abrindo a aba de consulta de produtos.
     */
    private void menuOpConsultaDeProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpConsultaDeProdutosActionPerformed
        ProdutoView produtoView = new ProdutoView();
        produtoView.painelComAbasProduto.setSelectedIndex(1);
        produtoView.setVisible(true);
    }//GEN-LAST:event_menuOpConsultaDeProdutosActionPerformed

    /**
     * Este método é acionado quando a opção "Abrir PDV" é selecionada no menu.
     * Ele cria uma instância da classe VendaView e a torna visível.
     */
    private void menuOpAbrirPdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpAbrirPdvActionPerformed
        VendaView vendaView = new VendaView();
        vendaView.setVisible(true);
    }//GEN-LAST:event_menuOpAbrirPdvActionPerformed

    /**
     * Este método é acionado quando a opção "Controle de Vendas" é selecionada no menu.
     * Ele cria uma instância da classe HistoricoVendasConsolidadoView e a torna visível.
     */
    private void menuOpControleDeVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpControleDeVendasActionPerformed
        HistoricoVendasConsolidadoView historicoVendasConsolidadoView = new HistoricoVendasConsolidadoView();
        historicoVendasConsolidadoView.setVisible(true);
    }//GEN-LAST:event_menuOpControleDeVendasActionPerformed

    /**
     * Este método é acionado quando a opção "Controle de Estoque" é selecionada no menu.
     * Ele cria uma instância da classe EstoqueView e a torna visível.
     */
    private void menuOpControleDeEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpControleDeEstoqueActionPerformed
        EstoqueView estoqueView = new EstoqueView();
        estoqueView.setVisible(true);
    }//GEN-LAST:event_menuOpControleDeEstoqueActionPerformed

    /**
     * Este método é acionado quando a opção "Sair" no menu é clicada.
     * Ele exibe um diálogo de confirmação para a saída do programa e encerra a aplicação se confirmado.
     */
    private void menuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSairMouseClicked
        int op;
        op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?");
        if (op == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_menuSairMouseClicked

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
            java.util.logging.Logger.getLogger(MenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuView().setVisible(true);
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

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/fundo.jpg"));
        Image image = icon.getImage();
        painelFundo = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(image, 0,0, getWidth(),getHeight(),this);
            }
        };
        jMenuBar1 = new javax.swing.JMenuBar();
        menuClientes = new javax.swing.JMenu();
        menuOpControleDeClientes = new javax.swing.JMenuItem();
        menuProdutos = new javax.swing.JMenu();
        menuOpControleDeEstoque = new javax.swing.JMenuItem();
        menuOpConsultaDeProdutos = new javax.swing.JMenuItem();
        menuVendas = new javax.swing.JMenu();
        menuOpAbrirPdv = new javax.swing.JMenuItem();
        menuOpControleDeVendas = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menu Principal");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        javax.swing.GroupLayout painelFundoLayout = new javax.swing.GroupLayout(painelFundo);
        painelFundo.setLayout(painelFundoLayout);
        painelFundoLayout.setHorizontalGroup(
                painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 735, Short.MAX_VALUE)
        );
        painelFundoLayout.setVerticalGroup(
                painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 425, Short.MAX_VALUE)
        );

        menuClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clientes.png"))); // NOI18N
        menuClientes.setText("Clientes");
        menuClientes.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        menuOpControleDeClientes.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        menuOpControleDeClientes.setText("Controle de Clientes");
        menuOpControleDeClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpControleDeClientesActionPerformed(evt);
            }
        });
        menuClientes.add(menuOpControleDeClientes);

        jMenuBar1.add(menuClientes);

        menuProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/produtos.png"))); // NOI18N
        menuProdutos.setText("Produtos");
        menuProdutos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        menuOpControleDeEstoque.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        menuOpControleDeEstoque.setText("Controle De Estoque");
        menuOpControleDeEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpControleDeEstoqueActionPerformed(evt);
            }
        });
        menuProdutos.add(menuOpControleDeEstoque);

        menuOpConsultaDeProdutos.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        menuOpConsultaDeProdutos.setText("Cadastro / Consulta de Produtos");
        menuOpConsultaDeProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpConsultaDeProdutosActionPerformed(evt);
            }
        });
        menuProdutos.add(menuOpConsultaDeProdutos);

        jMenuBar1.add(menuProdutos);

        menuVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/vendas.png"))); // NOI18N
        menuVendas.setText("Vendas");
        menuVendas.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        menuOpAbrirPdv.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        menuOpAbrirPdv.setText("Abrir PDV");
        menuOpAbrirPdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpAbrirPdvActionPerformed(evt);
            }
        });
        menuVendas.add(menuOpAbrirPdv);

        menuOpControleDeVendas.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        menuOpControleDeVendas.setText("Controle de Vendas");
        menuOpControleDeVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpControleDeVendasActionPerformed(evt);
            }
        });
        menuVendas.add(menuOpControleDeVendas);

        jMenuBar1.add(menuVendas);

        menuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sair.png"))); // NOI18N
        menuSair.setText("Sair");
        menuSair.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSairMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuSair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(painelFundo)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(painelFundo)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuClientes;
    private javax.swing.JMenuItem menuOpAbrirPdv;
    private javax.swing.JMenuItem menuOpConsultaDeProdutos;
    private javax.swing.JMenuItem menuOpControleDeClientes;
    private javax.swing.JMenuItem menuOpControleDeEstoque;
    private javax.swing.JMenuItem menuOpControleDeVendas;
    private javax.swing.JMenu menuProdutos;
    private javax.swing.JMenu menuSair;
    private javax.swing.JMenu menuVendas;
    private javax.swing.JDesktopPane painelFundo;
    // End of variables declaration//GEN-END:variables
}
