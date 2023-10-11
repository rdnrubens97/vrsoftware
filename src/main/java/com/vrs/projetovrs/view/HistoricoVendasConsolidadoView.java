/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.vrs.projetovrs.view;

import com.vrs.projetovrs.dao.ClienteDao;
import com.vrs.projetovrs.dao.ItemVendaDao;
import com.vrs.projetovrs.dao.ServicoDao;
import com.vrs.projetovrs.dao.VendaDao;
import com.vrs.projetovrs.model.ItemVenda;
import com.vrs.projetovrs.model.Servico;
import com.vrs.projetovrs.model.Venda;
import com.vrs.projetovrs.service.ClienteService;
import com.vrs.projetovrs.service.ItemVendaService;
import com.vrs.projetovrs.service.ServicoService;
import com.vrs.projetovrs.service.VendaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Rubens
 */
public class HistoricoVendasConsolidadoView extends javax.swing.JFrame {
    private ClienteService clienteService;
    private VendaService vendaService;
    private ItemVendaService itemVendaService;
    private ServicoService servicoService;

    /**
     * Creates new form HistoricoVendasView
     */
    public HistoricoVendasConsolidadoView() {
        initComponents();
        clienteService = new ClienteService(new ClienteDao());
        vendaService = new VendaService(new VendaDao());
        itemVendaService = new ItemVendaService(new ItemVendaDao());
        servicoService = new ServicoService(new ServicoDao());
    }

    /**
     * Preenche a tabela de histórico de vendas com as vendas do último mês ao abrir a janela.
     */
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        preencherTabelaInicio();
    }//GEN-LAST:event_formWindowActivated


    /**
     * Preenche a tabela de histórico de vendas com as vendas do último mês ao abrir a janela.
     */
    private void preencherTabelaInicio() {
        List<Venda> listaDeVendas = vendaService.listarVendasDoUltimoMes();
        BigDecimal valorTotal = BigDecimal.ZERO;

        DefaultTableModel dados = (DefaultTableModel) tabelaHistoricoDeVendas.getModel();
        dados.setNumRows(0);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = listaDeVendas.size() - 1; i >= 0; i--){
            Venda venda = listaDeVendas.get(i);
            String dataFormatada = simpleDateFormat.format(venda.getData());
            dados.addRow(new Object[]{
                    venda.getId(),
                    dataFormatada,
                    venda.getCliente().getNome(),
                    "R$ " + venda.getValorTotal(),
                    venda.getStatus().toString()
            });
            if (venda.getStatus().toString().equals("EFETIVADA")) valorTotal = valorTotal.add(venda.getValorTotal());
        }
        textoValorTotalHistoricoVendas.setText("R$ " + valorTotal.toString().replace(".", ","));
    }

    /**
     * Realiza uma pesquisa de vendas por um período de datas especificado.
     */
    public void botaoPesquisarVendasPorDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarVendasPorDataActionPerformed
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicio = LocalDate.parse(textoDataInicioHistoricoVendas.getText(), formato);
        LocalDate dataFim = LocalDate.parse(textoDataFimHistoricoVendas.getText(), formato);

        if (dataInicio.isBefore(dataFim) || dataInicio.equals(dataFim)) {
            List<Venda> listaDeVendas = vendaService.listarVendasPorPeriodo(dataInicio, dataFim);
            BigDecimal valorTotal = BigDecimal.ZERO;

            DefaultTableModel dados = (DefaultTableModel) tabelaHistoricoDeVendas.getModel();
            dados.setNumRows(0);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            for (int i = listaDeVendas.size() - 1; i >= 0; i--){
                Venda venda = listaDeVendas.get(i);
                String dataFormatada = simpleDateFormat.format(venda.getData());
                dados.addRow(new Object[]{
                        venda.getId(),
                        dataFormatada,
                        venda.getCliente().getNome(),
                        "R$ " + venda.getValorTotal(),
                        venda.getStatus().toString()
                });
                if (venda.getStatus().toString().equals("EFETIVADA")) valorTotal = valorTotal.add(venda.getValorTotal());
            }
            textoValorTotalHistoricoVendas.setText("R$ " + valorTotal.toString().replace(".", ","));
        } else {
            JOptionPane.showMessageDialog(null, "A data inicial deve ser anterior à data final");
        }
    }//GEN-LAST:event_botaoPesquisarVendasPorDataActionPerformed

    /**
     * Abre uma janela de detalhes quando um registro na tabela de histórico de vendas é clicado.
     */
    private void tabelaHistoricoDeVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaHistoricoDeVendasMouseClicked
        HistoricoVendasDetalhadoView viewVendasDetalhes = new HistoricoVendasDetalhadoView();
        viewVendasDetalhes.idVenda = Integer.parseInt(tabelaHistoricoDeVendas.getValueAt(tabelaHistoricoDeVendas.getSelectedRow(), 0).toString());

        viewVendasDetalhes.textoDataHistoricoVendaDetalhada.
                setText(tabelaHistoricoDeVendas.getValueAt(tabelaHistoricoDeVendas.getSelectedRow(), 1).toString());
        viewVendasDetalhes.textoClienteHistoricoVendaDetalhada.
                setText(tabelaHistoricoDeVendas.getValueAt(tabelaHistoricoDeVendas.getSelectedRow(), 2).toString());
        viewVendasDetalhes.textoTotalHistoricoVendaDetalhada.
                setText(tabelaHistoricoDeVendas.getValueAt(tabelaHistoricoDeVendas.getSelectedRow(), 3).toString());

        Integer idVenda = Integer.parseInt(tabelaHistoricoDeVendas.getValueAt(tabelaHistoricoDeVendas.getSelectedRow(), 0).toString());
        List<ItemVenda> itemVendaLista = itemVendaService.listaItensDeUmaVenda(idVenda);
        List<Servico> servicoList = servicoService.listarServicosDeUmaVenda(idVenda);

        DefaultTableModel dadosItensVendidos = (DefaultTableModel) viewVendasDetalhes.tabelaItensVendidosHistoricoVendasDetalhada.getModel();
        DefaultTableModel dadosServicosPrestados = (DefaultTableModel) viewVendasDetalhes.tabelaServicosHistoricoVendasDetalhada.getModel();
        dadosItensVendidos.setNumRows(0);
        dadosServicosPrestados.setNumRows(0);

        for (ItemVenda itemVenda : itemVendaLista) {
            dadosItensVendidos.addRow(new Object[]{
                    itemVenda.getProduto().getCodigo(),
                    itemVenda.getProduto().getDescricao(),
                    itemVenda.getQuantidade(),
                    "R$ " + itemVenda.getProduto().getPreco(),
                    "R$ " + itemVenda.getValorTotal()
            });
        }

        for (Servico servico : servicoList) {
            dadosServicosPrestados.addRow(new Object[]{
                    servico.getDescricao(),
                    "R$ " + servico.getPreco()
            });
        }

        viewVendasDetalhes.setVisible(true);
    }//GEN-LAST:event_tabelaHistoricoDeVendasMouseClicked

    private void textoBuscaClienteHistoricoVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoBuscaClienteHistoricoVendasActionPerformed

    }//GEN-LAST:event_textoBuscaClienteHistoricoVendasActionPerformed

    /**
     * faz uma busca instantânea por vendas que tenham sido realizadas para clientes que tenham no nome os caracteres inseridos no campo de busca
     * @param evt
     */
    private void textoBuscaClienteHistoricoVendasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoBuscaClienteHistoricoVendasKeyPressed
        String nomeCliente = textoBuscaClienteHistoricoVendas.getText().toUpperCase();
        List<Venda> vendas = vendaService.retornarVendasPorNome(nomeCliente);
        BigDecimal valorTotal = BigDecimal.ZERO;

        DefaultTableModel dados = (DefaultTableModel) tabelaHistoricoDeVendas.getModel();
        dados.setNumRows(0);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = vendas.size() - 1; i >= 0; i--){
            Venda venda = vendas.get(i);
            String dataFormatada = simpleDateFormat.format(venda.getData());
            dados.addRow(new Object[]{
                    venda.getId(),
                    dataFormatada,
                    venda.getCliente().getNome(),
                    "R$ " + venda.getValorTotal(),
                    venda.getStatus().toString()
            });
            if (venda.getStatus().toString().equals("EFETIVADA")) valorTotal = valorTotal.add(venda.getValorTotal());
        }
        textoValorTotalHistoricoVendas.setText("R$ " + valorTotal.toString().replace(".", ","));



    }//GEN-LAST:event_textoBuscaClienteHistoricoVendasKeyPressed

    private void botaoLimparVendasPorDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparVendasPorDataActionPerformed
        textoDataInicioHistoricoVendas.setText("");
        textoDataFimHistoricoVendas.setText("");
        preencherTabelaInicio();
    }//GEN-LAST:event_botaoLimparVendasPorDataActionPerformed

    private void botaoImprimirRelatorioConsolidadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoImprimirRelatorioConsolidadoActionPerformed

    }//GEN-LAST:event_botaoImprimirRelatorioConsolidadoActionPerformed

    private void textoDataInicioHistoricoVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoDataInicioHistoricoVendasActionPerformed

    }//GEN-LAST:event_textoDataInicioHistoricoVendasActionPerformed

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
            java.util.logging.Logger.getLogger(HistoricoVendasConsolidadoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistoricoVendasConsolidadoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistoricoVendasConsolidadoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistoricoVendasConsolidadoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HistoricoVendasConsolidadoView().setVisible(true);
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
        painelSelecaoDatasHistoricoVendas = new javax.swing.JPanel();
        campoDataHistoricoVendas = new javax.swing.JLabel();
        textoDataInicioHistoricoVendas = new javax.swing.JFormattedTextField();
        campoDataHistoricoVendas1 = new javax.swing.JLabel();
        textoDataFimHistoricoVendas = new javax.swing.JFormattedTextField();
        botaoPesquisarVendasPorData = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        campoValorTotalHistoricoVendas = new javax.swing.JLabel();
        textoValorTotalHistoricoVendas = new javax.swing.JTextField();
        botaoImprimirRelatorioConsolidado = new javax.swing.JButton();
        campoBuscaClienteHistoricoVendas = new javax.swing.JLabel();
        textoBuscaClienteHistoricoVendas = new javax.swing.JTextField();
        botaoLimparVendasPorData = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaHistoricoDeVendas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Historico de Vendas ");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        painelTituloHistoricoVendas.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Histórico de Vendas");

        javax.swing.GroupLayout painelTituloHistoricoVendasLayout = new javax.swing.GroupLayout(painelTituloHistoricoVendas);
        painelTituloHistoricoVendas.setLayout(painelTituloHistoricoVendasLayout);
        painelTituloHistoricoVendasLayout.setHorizontalGroup(
            painelTituloHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTituloHistoricoVendasLayout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelTituloHistoricoVendasLayout.setVerticalGroup(
            painelTituloHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTituloHistoricoVendasLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        painelSelecaoDatasHistoricoVendas.setBackground(new java.awt.Color(102, 102, 102));
        painelSelecaoDatasHistoricoVendas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consulta por Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        campoDataHistoricoVendas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoDataHistoricoVendas.setForeground(new java.awt.Color(255, 255, 255));
        campoDataHistoricoVendas.setText(" Data Inicial");
        campoDataHistoricoVendas.setBorder(new javax.swing.border.MatteBorder(null));

        try {
            textoDataInicioHistoricoVendas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textoDataInicioHistoricoVendas.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        textoDataInicioHistoricoVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoDataInicioHistoricoVendasActionPerformed(evt);
            }
        });

        campoDataHistoricoVendas1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoDataHistoricoVendas1.setForeground(new java.awt.Color(255, 255, 255));
        campoDataHistoricoVendas1.setText(" Data Final");
        campoDataHistoricoVendas1.setBorder(new javax.swing.border.MatteBorder(null));

        try {
            textoDataFimHistoricoVendas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textoDataFimHistoricoVendas.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N

        botaoPesquisarVendasPorData.setBackground(new java.awt.Color(51, 153, 255));
        botaoPesquisarVendasPorData.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botaoPesquisarVendasPorData.setForeground(new java.awt.Color(0, 0, 0));
        botaoPesquisarVendasPorData.setText("Pesquisar");
        botaoPesquisarVendasPorData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarVendasPorDataActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Para maiores detalhes, selecione a venda que deseja analisar");

        campoValorTotalHistoricoVendas.setBackground(new java.awt.Color(0, 0, 0));
        campoValorTotalHistoricoVendas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        campoValorTotalHistoricoVendas.setForeground(new java.awt.Color(255, 255, 255));
        campoValorTotalHistoricoVendas.setText(" Valor Total");

        textoValorTotalHistoricoVendas.setEditable(false);
        textoValorTotalHistoricoVendas.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        botaoImprimirRelatorioConsolidado.setBackground(new java.awt.Color(153, 204, 255));
        botaoImprimirRelatorioConsolidado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botaoImprimirRelatorioConsolidado.setForeground(new java.awt.Color(0, 0, 0));
        botaoImprimirRelatorioConsolidado.setText("Imprimir Relatório");
        botaoImprimirRelatorioConsolidado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoImprimirRelatorioConsolidadoActionPerformed(evt);
            }
        });

        campoBuscaClienteHistoricoVendas.setBackground(new java.awt.Color(0, 0, 0));
        campoBuscaClienteHistoricoVendas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        campoBuscaClienteHistoricoVendas.setForeground(new java.awt.Color(255, 255, 255));
        campoBuscaClienteHistoricoVendas.setText("Busca Cliente");

        textoBuscaClienteHistoricoVendas.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        textoBuscaClienteHistoricoVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoBuscaClienteHistoricoVendasActionPerformed(evt);
            }
        });
        textoBuscaClienteHistoricoVendas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoBuscaClienteHistoricoVendasKeyPressed(evt);
            }
        });

        botaoLimparVendasPorData.setBackground(new java.awt.Color(255, 51, 51));
        botaoLimparVendasPorData.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botaoLimparVendasPorData.setForeground(new java.awt.Color(0, 0, 0));
        botaoLimparVendasPorData.setText("Limpar");
        botaoLimparVendasPorData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparVendasPorDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelSelecaoDatasHistoricoVendasLayout = new javax.swing.GroupLayout(painelSelecaoDatasHistoricoVendas);
        painelSelecaoDatasHistoricoVendas.setLayout(painelSelecaoDatasHistoricoVendasLayout);
        painelSelecaoDatasHistoricoVendasLayout.setHorizontalGroup(
            painelSelecaoDatasHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSelecaoDatasHistoricoVendasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelSelecaoDatasHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelSelecaoDatasHistoricoVendasLayout.createSequentialGroup()
                        .addComponent(campoDataHistoricoVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoDataInicioHistoricoVendas))
                    .addGroup(painelSelecaoDatasHistoricoVendasLayout.createSequentialGroup()
                        .addGroup(painelSelecaoDatasHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoDataHistoricoVendas1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(painelSelecaoDatasHistoricoVendasLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(botaoLimparVendasPorData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelSelecaoDatasHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoPesquisarVendasPorData, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoDataFimHistoricoVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(painelSelecaoDatasHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoValorTotalHistoricoVendas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoBuscaClienteHistoricoVendas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelSelecaoDatasHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelSelecaoDatasHistoricoVendasLayout.createSequentialGroup()
                        .addComponent(textoValorTotalHistoricoVendas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoImprimirRelatorioConsolidado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textoBuscaClienteHistoricoVendas)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelSelecaoDatasHistoricoVendasLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap())))
        );
        painelSelecaoDatasHistoricoVendasLayout.setVerticalGroup(
            painelSelecaoDatasHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(painelSelecaoDatasHistoricoVendasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelSelecaoDatasHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoValorTotalHistoricoVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoValorTotalHistoricoVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoImprimirRelatorioConsolidado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelSelecaoDatasHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoBuscaClienteHistoricoVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoBuscaClienteHistoricoVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(painelSelecaoDatasHistoricoVendasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(painelSelecaoDatasHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoDataInicioHistoricoVendas, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoDataHistoricoVendas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelSelecaoDatasHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoDataFimHistoricoVendas)
                    .addComponent(campoDataHistoricoVendas1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelSelecaoDatasHistoricoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoPesquisarVendasPorData, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimparVendasPorData, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );

        tabelaHistoricoDeVendas.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        tabelaHistoricoDeVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº venda", "Data", "Cliente", "Total", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaHistoricoDeVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaHistoricoDeVendasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaHistoricoDeVendas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelTituloHistoricoVendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelSelecaoDatasHistoricoVendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelTituloHistoricoVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelSelecaoDatasHistoricoVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoImprimirRelatorioConsolidado;
    private javax.swing.JButton botaoLimparVendasPorData;
    private javax.swing.JButton botaoPesquisarVendasPorData;
    private javax.swing.JLabel campoBuscaClienteHistoricoVendas;
    private javax.swing.JLabel campoDataHistoricoVendas;
    private javax.swing.JLabel campoDataHistoricoVendas1;
    private javax.swing.JLabel campoValorTotalHistoricoVendas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel painelSelecaoDatasHistoricoVendas;
    private javax.swing.JPanel painelTituloHistoricoVendas;
    private javax.swing.JTable tabelaHistoricoDeVendas;
    private javax.swing.JTextField textoBuscaClienteHistoricoVendas;
    private javax.swing.JFormattedTextField textoDataFimHistoricoVendas;
    private javax.swing.JFormattedTextField textoDataInicioHistoricoVendas;
    private javax.swing.JTextField textoValorTotalHistoricoVendas;
    // End of variables declaration//GEN-END:variables
}
