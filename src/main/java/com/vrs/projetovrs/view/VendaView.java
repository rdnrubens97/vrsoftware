/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.vrs.projetovrs.view;

import com.vrs.projetovrs.dao.ClienteDao;
import com.vrs.projetovrs.dao.ProdutoDao;
import com.vrs.projetovrs.model.Cliente;
import com.vrs.projetovrs.service.ClienteService;
import com.vrs.projetovrs.service.ProdutoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Rubens
 */
public class VendaView extends javax.swing.JFrame {
    ClienteService clienteService;
    ProdutoService produtoService;
    BigDecimal total = BigDecimal.ZERO;
    BigDecimal preco, subtotal;
    Integer quantidade;
    DefaultTableModel carrinhoDeComprasVendaView;
    Cliente clienteVendaView = new Cliente();

    /**
     * Creates new form ProdutoView
     */
    public VendaView() {
        initComponents();
        clienteService = new ClienteService(new ClienteDao());
        produtoService = new ProdutoService(new ProdutoDao());
        setLocationRelativeTo(null);
        textoIdentificadorClientePdv.setEnabled(false);
        textoNomeClientePdv.setEnabled(false);
        textoDataPdv.setEnabled(false);
        textoIdentificadorProdutoPdv.setEnabled(false);
        textoDescricaoProdutoPdv.setEnabled(false);
    }

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Date agora = new Date();
        SimpleDateFormat formatoBr = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formatoBr.format(agora);
        textoDataPdv.setText(dataFormatada);
    }//GEN-LAST:event_formWindowActivated

    /**
     * procura um cliente pelo seu id e insere no campo nomeCliente no PDV
     * @param evt
     */
    private void botaoPesquisarClientePdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarClientePdvActionPerformed
        SelecaoClienteView selecaoClienteView = new SelecaoClienteView(this);
        selecaoClienteView.setVisible(true);
    }//GEN-LAST:event_botaoPesquisarClientePdvActionPerformed

    /**
     * Este método é executado quando o botão "Adicionar Item" é pressionado no contexto de uma tela de ponto de venda (PDV).
     * Ele permite gerenciar o carrinho de compras, também verificando se existe estoque disponível do produto a cada tentativa
     * de inserção de um novo produto no carrinho. Caso o produto tenha estoque disponível, a inserção é realizada.
     * Um mesmo produto não pode ser adicionado em dois registros diferente do carrinho, nesse caso, atualizamos somente a quantidade.
     *
     * @param evt O evento que desencadeou a ação do botão.
     */
    private void botaoAcicionarItemPdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAcicionarItemPdvActionPerformed
        String codigo = textoIdentificadorProdutoPdv.getText();
        quantidade = Integer.parseInt(textoQuantidadeProdutoPdv.getText());

        if (verificaSeTemEstoqueDisponivel(codigo, quantidade)) {

            preco = new BigDecimal(textoPrecoProdutoPdv.getText().replace("R$ ", ""));
            subtotal = preco.multiply(BigDecimal.valueOf(quantidade));
            total = total.add(subtotal);
            textoTotalProdutosPdv.setText(total.toString());
            carrinhoDeComprasVendaView = (DefaultTableModel) tabelaItensVenda.getModel();
            String identificadorProduto = textoIdentificadorProdutoPdv.getText();
            boolean identificadorExistente = false;

            for (int i = 0; i < carrinhoDeComprasVendaView.getRowCount(); i++) {
                String identificadorSendoVerificado = (String) carrinhoDeComprasVendaView.getValueAt(i, 0).toString();

                if (identificadorSendoVerificado.equals(identificadorProduto)) {
                    Integer quantidadeExistente = (Integer) carrinhoDeComprasVendaView.getValueAt(i, 2);
                    BigDecimal subtotalExistente = (BigDecimal) carrinhoDeComprasVendaView.getValueAt(i, 4);
                    quantidadeExistente += quantidade;

                    if (verificaSeTemEstoqueDisponivel(codigo, quantidadeExistente)) {
                        subtotalExistente = subtotalExistente.add(subtotal);
                        carrinhoDeComprasVendaView.setValueAt(quantidadeExistente, i, 2);
                        carrinhoDeComprasVendaView.setValueAt(subtotalExistente, i, 4);
                        identificadorExistente = true;
                        break;
                    }
                    else {
                        total = total.subtract(subtotal);
                        textoTotalProdutosPdv.setText(total.toString());
                        return;
                    }
                }
            }
            if (!identificadorExistente) {
                carrinhoDeComprasVendaView.addRow(new Object[]{
                        identificadorProduto,
                        textoDescricaoProdutoPdv.getText(),
                        quantidade,
                        textoPrecoProdutoPdv.getText().replace("R$ ", ""),
                        subtotal
                });
            }
        }
        atualizaTextosDeTotaisRelativosAVenda();
    }//GEN-LAST:event_botaoAcicionarItemPdvActionPerformed

    private void botaoPesquisarProdutoPdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarProdutoPdvActionPerformed
        SelecaoProdutoView selecaoProdutoView = new SelecaoProdutoView(this);
        selecaoProdutoView.setVisible(true);
    }//GEN-LAST:event_botaoPesquisarProdutoPdvActionPerformed

    private void verificaSeTemClienteSelecionado(String nomeCliente) {
        if (nomeCliente.isEmpty()){
            JOptionPane.showMessageDialog(null, "Selecione um cliente para essa venda");
            throw new RuntimeException();
        }
    }

    private void botaoEfetivarVendaPdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEfetivarVendaPdvActionPerformed
        String nomeCliente = textoIdentificadorClientePdv.getText();
        verificaSeTemClienteSelecionado(nomeCliente);
        clienteVendaView = clienteService.buscarClientePorId(nomeCliente);
        PagamentosView pagamentosView = new PagamentosView();
        pagamentosView.textoTotalPagamento.setText(total.toString());
        pagamentosView.clientePagamentoView = clienteVendaView;
        pagamentosView.carrinhoDeComprasPagamentoView = carrinhoDeComprasVendaView;
        pagamentosView.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botaoEfetivarVendaPdvActionPerformed

    /**
     * Verifica se há estoque disponível para atender a uma solicitação de um produto.
     *
     * @param codigoProduto           O ID do produto que está sendo verificado.
     * @param quantidadeSolicitada A quantidade desejada do produto.
     * @return                    {@code true} se houver estoque disponível para atender ao pedido;
     *                            {@code false} caso contrário, exibindo uma mensagem de aviso.
     */
    public boolean verificaSeTemEstoqueDisponivel(String codigoProduto, Integer quantidadeSolicitada) {
        Integer estoqueAtual = produtoService.retornaEstoqueAtualPorCodigo(codigoProduto);
        if (estoqueAtual < quantidadeSolicitada) {
            JOptionPane.showMessageDialog(null, "A quantidade em estoque deste produto é insuficiente para atender seu pedido." +
                    "\nEstoque atual: " + estoqueAtual + " unidades");
            return false;
        }
        return true;
    }

    /**
     * atualiza o valor do campo de texto que indica o valor total da venda
     */
    private void atualizaTextosDeTotaisRelativosAVenda() {
        atualizarTextoTotalProdutosPdv();
        atualizarTextoTotalMaoDeObraPdv();
        BigDecimal valorProdutos = new BigDecimal(textoTotalProdutosPdv.getText());
        BigDecimal valorMaoDeObra = new BigDecimal(textoTotalMaoDeObraPdv.getText());
        BigDecimal valorTotalVenda = valorProdutos.add(valorMaoDeObra);
        textoTotalVendaPdv.setText(valorTotalVenda.toString());
    }

    /**
     * atualiza o valor do campo de texto que indica o valor total de produtos
     */
    private void atualizarTextoTotalProdutosPdv() {
        DefaultTableModel carrinhoDeComprasVendaView = (DefaultTableModel) tabelaItensVenda.getModel();
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (int i = 0; i < carrinhoDeComprasVendaView.getRowCount(); i++) {
            BigDecimal valorItem = (BigDecimal) carrinhoDeComprasVendaView.getValueAt(i, 4);
            if (valorItem != null) {
                valorTotal = valorTotal.add(valorItem);
            }
        }
        textoTotalProdutosPdv.setText(valorTotal.toString());
    }

    /**
     * atualiza o valor do campo de texto que indica o valor total de mão de obra
     */
    private void atualizarTextoTotalMaoDeObraPdv() {
        DefaultTableModel tabelaMaoDeObraPdvModel = (DefaultTableModel) tabelaMaoDeObraPdv.getModel();
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (int i = 0; i < tabelaMaoDeObraPdvModel.getRowCount(); i++) {
            BigDecimal valorItem = (BigDecimal) tabelaMaoDeObraPdvModel.getValueAt(i, 4);
            if (valorItem != null) {
                valorTotal = valorTotal.add(valorItem);
            }
        }
        textoTotalMaoDeObraPdv.setText(valorTotal.toString());
    }

    /**
     * método responsável por adicionar um popMenu na tabela carrinho de produtos com a opção de
     * excluir o registro selecionado com o mouse na tabela.
     */
    private void tabelaItensVendaMouseReleased(MouseEvent evt) {
        if (evt.isPopupTrigger()) {
            // Obtém a linha selecionada
            int row = tabelaItensVenda.rowAtPoint(evt.getPoint());
            // Se a linha selecionada for válida, mostra o menu de contexto
            if (row >= 0 && row < tabelaItensVenda.getRowCount()) {
                JPopupMenu menuContexto = criarMenuContexto();
                menuContexto.show(tabelaItensVenda, evt.getX(), evt.getY());
            }
        }
    }

    private JPopupMenu criarMenuContexto() {
        JPopupMenu menuContexto = new JPopupMenu();

        JMenuItem excluirItem = new JMenuItem("Excluir Registro");
        excluirItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Coloque aqui o código para excluir o registro selecionado
                int selectedRow = tabelaItensVenda.getSelectedRow();
                // Verifique se uma linha está selecionada
                if (selectedRow >= 0) {
                    // Remova o registro da tabela ou do modelo de dados
                    DefaultTableModel model = (DefaultTableModel) tabelaItensVenda.getModel();
                    model.removeRow(selectedRow);
                    atualizaTextosDeTotaisRelativosAVenda();
                }
            }
        });

        menuContexto.add(excluirItem);
        return menuContexto;
    }

    private void textoDescricaoProdutoPdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoDescricaoProdutoPdvActionPerformed
    }//GEN-LAST:event_textoDescricaoProdutoPdvActionPerformed

    private void textoDescricaoProdutoPdvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoDescricaoProdutoPdvKeyPressed
    }//GEN-LAST:event_textoDescricaoProdutoPdvKeyPressed

    private void textoIdentificadorProdutoPdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoIdentificadorProdutoPdvActionPerformed
    }//GEN-LAST:event_textoIdentificadorProdutoPdvActionPerformed

    private void textoIdentificadorProdutoPdvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoIdentificadorProdutoPdvKeyPressed
    }//GEN-LAST:event_textoIdentificadorProdutoPdvKeyPressed

    private void textoIdentificadorClientePdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoIdentificadorClientePdvActionPerformed
    }//GEN-LAST:event_textoIdentificadorClientePdvActionPerformed

    private void textoIdentificadorClientePdvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoIdentificadorClientePdvKeyPressed
    }//GEN-LAST:event_textoIdentificadorClientePdvKeyPressed

    private void textoNomeClientePdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoNomeClientePdvActionPerformed
    }//GEN-LAST:event_textoNomeClientePdvActionPerformed

    private void textoNomeClientePdvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNomeClientePdvKeyPressed
    }//GEN-LAST:event_textoNomeClientePdvKeyPressed

    private void textoQuantidadeProdutoPdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoQuantidadeProdutoPdvActionPerformed
    }//GEN-LAST:event_textoQuantidadeProdutoPdvActionPerformed

    private void textoQuantidadeProdutoPdvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoQuantidadeProdutoPdvKeyPressed
    }//GEN-LAST:event_textoQuantidadeProdutoPdvKeyPressed

    private void textoPrecoProdutoPdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoPrecoProdutoPdvActionPerformed
    }//GEN-LAST:event_textoPrecoProdutoPdvActionPerformed

    private void textoPrecoProdutoPdvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoPrecoProdutoPdvKeyPressed
    }//GEN-LAST:event_textoPrecoProdutoPdvKeyPressed

    private void textoTotalProdutosPdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoTotalProdutosPdvActionPerformed
    }//GEN-LAST:event_textoTotalProdutosPdvActionPerformed

    private void textoTotalProdutosPdvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoTotalProdutosPdvKeyPressed
    }//GEN-LAST:event_textoTotalProdutosPdvKeyPressed

    private void textoDataPdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoDataPdvActionPerformed
    }//GEN-LAST:event_textoDataPdvActionPerformed

    private void textoDataPdvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoDataPdvKeyPressed
    }//GEN-LAST:event_textoDataPdvKeyPressed

    private void textoTotalMaoDeObraPdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoTotalMaoDeObraPdvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoTotalMaoDeObraPdvActionPerformed

    private void textoTotalMaoDeObraPdvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoTotalMaoDeObraPdvKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoTotalMaoDeObraPdvKeyPressed

    private void textoTotalVendaPdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoTotalVendaPdvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoTotalVendaPdvActionPerformed

    private void textoTotalVendaPdvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoTotalVendaPdvKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoTotalVendaPdvKeyPressed


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
            java.util.logging.Logger.getLogger(VendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VendaView().setVisible(true);
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
        campoNomeCadastro3 = new javax.swing.JLabel();
        textoDataPdv = new javax.swing.JTextField();
        painelDadosProdutoPdv = new javax.swing.JPanel();
        campoNomeCadastro = new javax.swing.JLabel();
        textoDescricaoProdutoPdv = new javax.swing.JTextField();
        campoNomeCadastro1 = new javax.swing.JLabel();
        textoIdentificadorProdutoPdv = new javax.swing.JTextField();
        campoNomeCadastro4 = new javax.swing.JLabel();
        textoQuantidadeProdutoPdv = new javax.swing.JTextField();
        campoNomeCadastro5 = new javax.swing.JLabel();
        textoPrecoProdutoPdv = new javax.swing.JTextField();
        botaoAcicionarItemPdv = new javax.swing.JButton();
        botaoPesquisarProdutoPdv = new javax.swing.JButton();
        painelDadosClientePdv = new javax.swing.JPanel();
        campoNomeCadastro2 = new javax.swing.JLabel();
        textoIdentificadorClientePdv = new javax.swing.JTextField();
        textoNomeClientePdv = new javax.swing.JTextField();
        botaoPesquisarClientePdv = new javax.swing.JButton();
        campoIdentificadorClientePdv = new javax.swing.JLabel();
        painelCarrinhoDeComprasPdv = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaItensVenda = new javax.swing.JTable();
        painelTotalVendaPdv = new javax.swing.JPanel();
        campoTotalProdutosPdv = new javax.swing.JLabel();
        textoTotalProdutosPdv = new javax.swing.JTextField();
        campoTotalMaoDeObraPdv = new javax.swing.JLabel();
        textoTotalMaoDeObraPdv = new javax.swing.JTextField();
        campoTotalVendaPdv = new javax.swing.JLabel();
        textoTotalVendaPdv = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaMaoDeObraPdv = new javax.swing.JTable();
        botaoEfetivarVendaPdv = new javax.swing.JButton();
        botaoGerarOrcamentoPdv = new javax.swing.JButton();
        botaoAdicionarMaoDeObraPdv = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Vendas PDV");
        setBackground(new java.awt.Color(0, 0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 255, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nova Venda / Orçamento");

        campoNomeCadastro3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        campoNomeCadastro3.setForeground(new java.awt.Color(0, 0, 0));
        campoNomeCadastro3.setText("  Data");
        campoNomeCadastro3.setBorder(new javax.swing.border.MatteBorder(null));

        textoDataPdv.setEditable(false);
        textoDataPdv.setBackground(new java.awt.Color(255, 255, 255));
        textoDataPdv.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        textoDataPdv.setForeground(new java.awt.Color(0, 0, 0));
        textoDataPdv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoDataPdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoDataPdvActionPerformed(evt);
            }
        });
        textoDataPdv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoDataPdvKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(campoNomeCadastro3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoDataPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoDataPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNomeCadastro3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelDadosProdutoPdv.setBackground(new java.awt.Color(204, 204, 204));
        painelDadosProdutoPdv.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        campoNomeCadastro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoNomeCadastro.setForeground(new java.awt.Color(0, 0, 0));
        campoNomeCadastro.setText("Código");
        campoNomeCadastro.setBorder(new javax.swing.border.MatteBorder(null));

        textoDescricaoProdutoPdv.setEditable(false);
        textoDescricaoProdutoPdv.setBackground(new java.awt.Color(255, 255, 255));
        textoDescricaoProdutoPdv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoDescricaoProdutoPdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoDescricaoProdutoPdvActionPerformed(evt);
            }
        });
        textoDescricaoProdutoPdv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoDescricaoProdutoPdvKeyPressed(evt);
            }
        });

        campoNomeCadastro1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoNomeCadastro1.setForeground(new java.awt.Color(0, 0, 0));
        campoNomeCadastro1.setText("Descrição");
        campoNomeCadastro1.setBorder(new javax.swing.border.MatteBorder(null));

        textoIdentificadorProdutoPdv.setBackground(new java.awt.Color(255, 255, 255));
        textoIdentificadorProdutoPdv.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textoIdentificadorProdutoPdv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoIdentificadorProdutoPdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoIdentificadorProdutoPdvActionPerformed(evt);
            }
        });
        textoIdentificadorProdutoPdv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoIdentificadorProdutoPdvKeyPressed(evt);
            }
        });

        campoNomeCadastro4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoNomeCadastro4.setForeground(new java.awt.Color(0, 0, 0));
        campoNomeCadastro4.setText("  Preço");
        campoNomeCadastro4.setBorder(new javax.swing.border.MatteBorder(null));

        textoQuantidadeProdutoPdv.setBackground(new java.awt.Color(255, 255, 255));
        textoQuantidadeProdutoPdv.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textoQuantidadeProdutoPdv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoQuantidadeProdutoPdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoQuantidadeProdutoPdvActionPerformed(evt);
            }
        });
        textoQuantidadeProdutoPdv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoQuantidadeProdutoPdvKeyPressed(evt);
            }
        });

        campoNomeCadastro5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoNomeCadastro5.setForeground(new java.awt.Color(0, 0, 0));
        campoNomeCadastro5.setText("QTD.");
        campoNomeCadastro5.setBorder(new javax.swing.border.MatteBorder(null));

        textoPrecoProdutoPdv.setBackground(new java.awt.Color(255, 255, 255));
        textoPrecoProdutoPdv.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textoPrecoProdutoPdv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoPrecoProdutoPdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoPrecoProdutoPdvActionPerformed(evt);
            }
        });
        textoPrecoProdutoPdv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoPrecoProdutoPdvKeyPressed(evt);
            }
        });

        botaoAcicionarItemPdv.setText("ADICIONAR PRODUTO");
        botaoAcicionarItemPdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAcicionarItemPdvActionPerformed(evt);
            }
        });

        botaoPesquisarProdutoPdv.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        botaoPesquisarProdutoPdv.setText("Pesquisar");
        botaoPesquisarProdutoPdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarProdutoPdvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelDadosProdutoPdvLayout = new javax.swing.GroupLayout(painelDadosProdutoPdv);
        painelDadosProdutoPdv.setLayout(painelDadosProdutoPdvLayout);
        painelDadosProdutoPdvLayout.setHorizontalGroup(
            painelDadosProdutoPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosProdutoPdvLayout.createSequentialGroup()
                .addGroup(painelDadosProdutoPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosProdutoPdvLayout.createSequentialGroup()
                        .addGroup(painelDadosProdutoPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoNomeCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoNomeCadastro1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelDadosProdutoPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDadosProdutoPdvLayout.createSequentialGroup()
                                .addComponent(textoIdentificadorProdutoPdv)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoPesquisarProdutoPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDadosProdutoPdvLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(textoDescricaoProdutoPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(painelDadosProdutoPdvLayout.createSequentialGroup()
                        .addComponent(campoNomeCadastro4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoPrecoProdutoPdv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoNomeCadastro5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoQuantidadeProdutoPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botaoAcicionarItemPdv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        painelDadosProdutoPdvLayout.setVerticalGroup(
            painelDadosProdutoPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosProdutoPdvLayout.createSequentialGroup()
                .addGroup(painelDadosProdutoPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(textoIdentificadorProdutoPdv, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoNomeCadastro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoPesquisarProdutoPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosProdutoPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textoDescricaoProdutoPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNomeCadastro1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosProdutoPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelDadosProdutoPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoNomeCadastro4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textoPrecoProdutoPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textoQuantidadeProdutoPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(campoNomeCadastro5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoAcicionarItemPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painelDadosProdutoPdvLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {campoNomeCadastro, campoNomeCadastro1, campoNomeCadastro4, campoNomeCadastro5});

        painelDadosClientePdv.setBackground(new java.awt.Color(51, 153, 255));
        painelDadosClientePdv.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        campoNomeCadastro2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoNomeCadastro2.setForeground(new java.awt.Color(0, 0, 0));
        campoNomeCadastro2.setText("Nome");
        campoNomeCadastro2.setBorder(new javax.swing.border.MatteBorder(null));

        textoIdentificadorClientePdv.setBackground(new java.awt.Color(255, 255, 255));
        textoIdentificadorClientePdv.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textoIdentificadorClientePdv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoIdentificadorClientePdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoIdentificadorClientePdvActionPerformed(evt);
            }
        });
        textoIdentificadorClientePdv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoIdentificadorClientePdvKeyPressed(evt);
            }
        });

        textoNomeClientePdv.setEditable(false);
        textoNomeClientePdv.setBackground(new java.awt.Color(255, 255, 255));
        textoNomeClientePdv.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textoNomeClientePdv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoNomeClientePdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoNomeClientePdvActionPerformed(evt);
            }
        });
        textoNomeClientePdv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoNomeClientePdvKeyPressed(evt);
            }
        });

        botaoPesquisarClientePdv.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        botaoPesquisarClientePdv.setText("Pesquisar");
        botaoPesquisarClientePdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarClientePdvActionPerformed(evt);
            }
        });

        campoIdentificadorClientePdv.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoIdentificadorClientePdv.setForeground(new java.awt.Color(0, 0, 0));
        campoIdentificadorClientePdv.setText("Identificador");
        campoIdentificadorClientePdv.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout painelDadosClientePdvLayout = new javax.swing.GroupLayout(painelDadosClientePdv);
        painelDadosClientePdv.setLayout(painelDadosClientePdvLayout);
        painelDadosClientePdvLayout.setHorizontalGroup(
            painelDadosClientePdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosClientePdvLayout.createSequentialGroup()
                .addComponent(campoIdentificadorClientePdv, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoIdentificadorClientePdv, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoPesquisarClientePdv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(painelDadosClientePdvLayout.createSequentialGroup()
                .addComponent(campoNomeCadastro2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoNomeClientePdv, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        painelDadosClientePdvLayout.setVerticalGroup(
            painelDadosClientePdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosClientePdvLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosClientePdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoIdentificadorClientePdv, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoIdentificadorClientePdv, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoPesquisarClientePdv, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosClientePdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNomeCadastro2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNomeClientePdv, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelDadosClientePdvLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {campoIdentificadorClientePdv, campoNomeCadastro2, textoIdentificadorClientePdv, textoNomeClientePdv});

        painelCarrinhoDeComprasPdv.setBackground(new java.awt.Color(255, 51, 51));
        painelCarrinhoDeComprasPdv.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Carrinho de Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        tabelaItensVenda.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tabelaItensVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Produto", "Qtd", "Preço", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaItensVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelaItensVendaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaItensVenda);
        if (tabelaItensVenda.getColumnModel().getColumnCount() > 0) {
            tabelaItensVenda.getColumnModel().getColumn(0).setPreferredWidth(12);
            tabelaItensVenda.getColumnModel().getColumn(1).setPreferredWidth(300);
            tabelaItensVenda.getColumnModel().getColumn(2).setPreferredWidth(12);
            tabelaItensVenda.getColumnModel().getColumn(3).setPreferredWidth(30);
            tabelaItensVenda.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        javax.swing.GroupLayout painelCarrinhoDeComprasPdvLayout = new javax.swing.GroupLayout(painelCarrinhoDeComprasPdv);
        painelCarrinhoDeComprasPdv.setLayout(painelCarrinhoDeComprasPdvLayout);
        painelCarrinhoDeComprasPdvLayout.setHorizontalGroup(
            painelCarrinhoDeComprasPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
        );
        painelCarrinhoDeComprasPdvLayout.setVerticalGroup(
            painelCarrinhoDeComprasPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
        );

        painelTotalVendaPdv.setBackground(new java.awt.Color(255, 255, 0));
        painelTotalVendaPdv.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total de Venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        campoTotalProdutosPdv.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoTotalProdutosPdv.setForeground(new java.awt.Color(0, 0, 0));
        campoTotalProdutosPdv.setText("   Total Produtos");
        campoTotalProdutosPdv.setBorder(new javax.swing.border.MatteBorder(null));

        textoTotalProdutosPdv.setBackground(new java.awt.Color(255, 255, 255));
        textoTotalProdutosPdv.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textoTotalProdutosPdv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoTotalProdutosPdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoTotalProdutosPdvActionPerformed(evt);
            }
        });
        textoTotalProdutosPdv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoTotalProdutosPdvKeyPressed(evt);
            }
        });

        campoTotalMaoDeObraPdv.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoTotalMaoDeObraPdv.setForeground(new java.awt.Color(0, 0, 0));
        campoTotalMaoDeObraPdv.setText("   Mao de Obra");
        campoTotalMaoDeObraPdv.setBorder(new javax.swing.border.MatteBorder(null));

        textoTotalMaoDeObraPdv.setBackground(new java.awt.Color(255, 255, 255));
        textoTotalMaoDeObraPdv.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textoTotalMaoDeObraPdv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoTotalMaoDeObraPdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoTotalMaoDeObraPdvActionPerformed(evt);
            }
        });
        textoTotalMaoDeObraPdv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoTotalMaoDeObraPdvKeyPressed(evt);
            }
        });

        campoTotalVendaPdv.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        campoTotalVendaPdv.setForeground(new java.awt.Color(0, 0, 0));
        campoTotalVendaPdv.setText("  Total de Venda");
        campoTotalVendaPdv.setBorder(new javax.swing.border.MatteBorder(null));

        textoTotalVendaPdv.setBackground(new java.awt.Color(255, 255, 255));
        textoTotalVendaPdv.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        textoTotalVendaPdv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textoTotalVendaPdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoTotalVendaPdvActionPerformed(evt);
            }
        });
        textoTotalVendaPdv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoTotalVendaPdvKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout painelTotalVendaPdvLayout = new javax.swing.GroupLayout(painelTotalVendaPdv);
        painelTotalVendaPdv.setLayout(painelTotalVendaPdvLayout);
        painelTotalVendaPdvLayout.setHorizontalGroup(
            painelTotalVendaPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTotalVendaPdvLayout.createSequentialGroup()
                .addComponent(campoTotalProdutosPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoTotalProdutosPdv))
            .addGroup(painelTotalVendaPdvLayout.createSequentialGroup()
                .addComponent(campoTotalVendaPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoTotalVendaPdv))
            .addGroup(painelTotalVendaPdvLayout.createSequentialGroup()
                .addComponent(campoTotalMaoDeObraPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoTotalMaoDeObraPdv))
        );
        painelTotalVendaPdvLayout.setVerticalGroup(
            painelTotalVendaPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTotalVendaPdvLayout.createSequentialGroup()
                .addGroup(painelTotalVendaPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTotalProdutosPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoTotalProdutosPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelTotalVendaPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoTotalMaoDeObraPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelTotalVendaPdvLayout.createSequentialGroup()
                        .addComponent(textoTotalMaoDeObraPdv)
                        .addGap(3, 3, 3)))
                .addGroup(painelTotalVendaPdvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoTotalVendaPdv, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(textoTotalVendaPdv))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        painelTotalVendaPdvLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {campoTotalMaoDeObraPdv, campoTotalProdutosPdv, campoTotalVendaPdv});

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mão de Obra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        tabelaMaoDeObraPdv.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tabelaMaoDeObraPdv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Valor"
            }
        ));
        jScrollPane2.setViewportView(tabelaMaoDeObraPdv);
        if (tabelaMaoDeObraPdv.getColumnModel().getColumnCount() > 0) {
            tabelaMaoDeObraPdv.getColumnModel().getColumn(0).setPreferredWidth(700);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        );

        botaoEfetivarVendaPdv.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        botaoEfetivarVendaPdv.setText("EFETIVAR VENDA");
        botaoEfetivarVendaPdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEfetivarVendaPdvActionPerformed(evt);
            }
        });

        botaoGerarOrcamentoPdv.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        botaoGerarOrcamentoPdv.setText("GERAR ORÇAMENTO");

        botaoAdicionarMaoDeObraPdv.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        botaoAdicionarMaoDeObraPdv.setText("INSERIR MÃO DE OBRA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botaoGerarOrcamentoPdv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(botaoAdicionarMaoDeObraPdv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoEfetivarVendaPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(painelTotalVendaPdv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(painelDadosClientePdv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(painelDadosProdutoPdv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(painelCarrinhoDeComprasPdv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(painelCarrinhoDeComprasPdv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(painelDadosClientePdv, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(painelDadosProdutoPdv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(painelTotalVendaPdv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoEfetivarVendaPdv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botaoAdicionarMaoDeObraPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoGerarOrcamentoPdv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAcicionarItemPdv;
    private javax.swing.JButton botaoAdicionarMaoDeObraPdv;
    private javax.swing.JButton botaoEfetivarVendaPdv;
    private javax.swing.JButton botaoGerarOrcamentoPdv;
    private javax.swing.JButton botaoPesquisarClientePdv;
    private javax.swing.JButton botaoPesquisarProdutoPdv;
    private javax.swing.JLabel campoIdentificadorClientePdv;
    private javax.swing.JLabel campoNomeCadastro;
    private javax.swing.JLabel campoNomeCadastro1;
    private javax.swing.JLabel campoNomeCadastro2;
    private javax.swing.JLabel campoNomeCadastro3;
    private javax.swing.JLabel campoNomeCadastro4;
    private javax.swing.JLabel campoNomeCadastro5;
    private javax.swing.JLabel campoTotalMaoDeObraPdv;
    private javax.swing.JLabel campoTotalProdutosPdv;
    private javax.swing.JLabel campoTotalVendaPdv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel painelCarrinhoDeComprasPdv;
    private javax.swing.JPanel painelDadosClientePdv;
    private javax.swing.JPanel painelDadosProdutoPdv;
    private javax.swing.JPanel painelTotalVendaPdv;
    public javax.swing.JTable tabelaItensVenda;
    private javax.swing.JTable tabelaMaoDeObraPdv;
    private javax.swing.JTextField textoDataPdv;
    public javax.swing.JTextField textoDescricaoProdutoPdv;
    public javax.swing.JTextField textoIdentificadorClientePdv;
    public javax.swing.JTextField textoIdentificadorProdutoPdv;
    public javax.swing.JTextField textoNomeClientePdv;
    public javax.swing.JTextField textoPrecoProdutoPdv;
    public javax.swing.JTextField textoQuantidadeProdutoPdv;
    private javax.swing.JTextField textoTotalMaoDeObraPdv;
    private javax.swing.JTextField textoTotalProdutosPdv;
    private javax.swing.JTextField textoTotalVendaPdv;
    // End of variables declaration//GEN-END:variables
}
