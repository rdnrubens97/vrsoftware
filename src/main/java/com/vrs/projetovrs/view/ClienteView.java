/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.vrs.projetovrs.view;

import com.vrs.projetovrs.dao.ClienteDao;
import com.vrs.projetovrs.model.Cliente;
import com.vrs.projetovrs.service.ClienteService;
import com.vrs.projetovrs.util.Utilities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * @author Rubens
 */
public class ClienteView extends javax.swing.JFrame {

    /**
     * variável global que é determinada de acordo com um cliente selecionado na 'tabelaExibicaoClientes'
     * tendo armazenado o id de determinado registro, podemos executar os métodos de excluir e editar registros
     */
    private Integer idSelecionado;
    private static final int ESTADO_INICIAL = 0;
    private static final int ESTADO_EDITANDO = 1;
    private static final int ESTADO_NOVO = 2;
    private int estadoAtual = ESTADO_INICIAL;
    private ClienteService clienteService;

    /**
     * Creates new form ClienteView
     */
    public ClienteView() {
        initComponents();
        gerenciadorDeBotoes();
        setLocationRelativeTo(null);
        clienteService = new ClienteService(new ClienteDao());
    }

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // Carrega a lista de clientes na tela de busca clientes
        listarClienteNaTabela();
    }//GEN-LAST:event_formWindowActivated

    /**
     * Este método gerencia o estado dos botões e campos da interface de usuário com base no estado atual da aplicação.
     * Ele é usado para controlar a habilitação, desabilitação e configuração de campos e botões, dependendo do contexto.
     */
    private void gerenciadorDeBotoes() {
        switch (estadoAtual) {
            case ESTADO_INICIAL:
                botaoNovo.setEnabled(true);
                botaoSalvar.setEnabled(false);
                botaoEditar.setEnabled(false);
                botaoExcluir.setEnabled(false);
                botaoLimpar.setEnabled(false);
                textoNomeCadastro.setEditable(false);
                textoNomeCadastro.setBackground(Color.LIGHT_GRAY);
                textoCelularCadastro.setEditable(false);
                textoCelularCadastro.setBackground(Color.LIGHT_GRAY);
                textoDocumentoCadastro.setEditable(false);
                textoDocumentoCadastro.setBackground(Color.LIGHT_GRAY);
                textoEmailCadastro.setEditable(false);
                textoEmailCadastro.setBackground(Color.LIGHT_GRAY);
                textoCepCadastro.setEditable(false);
                textoCepCadastro.setBackground(Color.LIGHT_GRAY);
                textoBairroCadastro.setEditable(false);
                textoBairroCadastro.setBackground(Color.LIGHT_GRAY);
                textoEstadoCadastro.setEnabled(false);
                textoEstadoCadastro.setBackground(Color.LIGHT_GRAY);
                textoEnderecoCadastro.setEditable(false);
                textoEnderecoCadastro.setBackground(Color.LIGHT_GRAY);
                textoNumeroCadastro.setEditable(false);
                textoNumeroCadastro.setBackground(Color.LIGHT_GRAY);
                textoComplementoCadastro.setEditable(false);
                textoComplementoCadastro.setBackground(Color.LIGHT_GRAY);
                textoCidadeCadastro.setEditable(false);
                textoCidadeCadastro.setBackground(Color.LIGHT_GRAY);
                break;
            case ESTADO_EDITANDO:
                botaoNovo.setEnabled(false);
                botaoSalvar.setEnabled(false);
                botaoEditar.setEnabled(true);
                botaoExcluir.setEnabled(true);
                botaoLimpar.setEnabled(true);
                textoNomeCadastro.setEditable(true);
                textoNomeCadastro.setBackground(Color.WHITE);
                textoCelularCadastro.setEditable(true);
                textoCelularCadastro.setBackground(Color.WHITE);
                textoDocumentoCadastro.setEditable(true);
                textoDocumentoCadastro.setBackground(Color.WHITE);
                textoEmailCadastro.setEditable(true);
                textoEmailCadastro.setBackground(Color.WHITE);
                textoCepCadastro.setEditable(true);
                textoCepCadastro.setBackground(Color.WHITE);
                textoBairroCadastro.setEditable(true);
                textoBairroCadastro.setBackground(Color.WHITE);
                textoEstadoCadastro.setEnabled(true);
                textoEstadoCadastro.setBackground(Color.WHITE);
                textoEnderecoCadastro.setEditable(true);
                textoEnderecoCadastro.setBackground(Color.WHITE);
                textoNumeroCadastro.setEditable(true);
                textoNumeroCadastro.setBackground(Color.WHITE);
                textoComplementoCadastro.setEditable(true);
                textoComplementoCadastro.setBackground(Color.WHITE);
                textoCidadeCadastro.setEditable(true);
                textoCidadeCadastro.setBackground(Color.WHITE);
                break;
            case ESTADO_NOVO:
                painelComAbasCliente.setSelectedIndex(1);
                botaoNovo.setEnabled(false);
                botaoSalvar.setEnabled(true);
                botaoEditar.setEnabled(false);
                botaoExcluir.setEnabled(false);
                botaoLimpar.setEnabled(true);
                textoNomeCadastro.setEditable(true);
                textoNomeCadastro.setBackground(Color.WHITE);
                textoCelularCadastro.setEditable(true);
                textoCelularCadastro.setBackground(Color.WHITE);
                textoDocumentoCadastro.setEditable(true);
                textoDocumentoCadastro.setBackground(Color.WHITE);
                textoEmailCadastro.setEditable(true);
                textoEmailCadastro.setBackground(Color.WHITE);
                textoCepCadastro.setEditable(true);
                textoCepCadastro.setBackground(Color.WHITE);
                textoBairroCadastro.setEditable(true);
                textoBairroCadastro.setBackground(Color.WHITE);
                textoEstadoCadastro.setEnabled(true);
                textoEstadoCadastro.setBackground(Color.WHITE);
                textoEnderecoCadastro.setEditable(true);
                textoEnderecoCadastro.setBackground(Color.WHITE);
                textoNumeroCadastro.setEditable(true);
                textoNumeroCadastro.setBackground(Color.WHITE);
                textoComplementoCadastro.setEditable(true);
                textoComplementoCadastro.setBackground(Color.WHITE);
                textoCidadeCadastro.setEditable(true);
                textoCidadeCadastro.setBackground(Color.WHITE);
                break;
            default:
        }
    }

    /**
     * Limpa os campos e redefine o estado atual para o estado inicial.
     *
     * @param evt
     */
    private void botaoLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoLimparMouseClicked
        this.estadoAtual = ESTADO_INICIAL;
        gerenciadorDeBotoes();
        this.idSelecionado = null;
        new Utilities().limpaCampos(painelDadosCliente);
    }//GEN-LAST:event_botaoLimparMouseClicked

    /**
     * lista todos os clientes registrados no banco de dados ta tabela de visualização clientes (tabelaExibicaoClientesLista)
     */
    public void listarClienteNaTabela() {
        List<Cliente> lista = clienteService.listarClientes();
        DefaultTableModel dados = (DefaultTableModel) tabelaExibicaoClientesLista.getModel();
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

    /**
     * Realiza uma busca instantânea por clientes que tenham no nome os caracteres inseridos no campo de busca
     * da aba consulta clientes.
     *
     * @param evt
     */
    private void textoNomePesquisaKeyPressed(java.awt.event.KeyEvent evt) {
        String nomeDesejado = textoNomePesquisa.getText();
        List<Cliente> lista = clienteService.listarClientesPorNome(nomeDesejado);
        DefaultTableModel dados = (DefaultTableModel) tabelaExibicaoClientesLista.getModel();
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

    /**
     * Seleciona um cliente na tabela de exibição de clientes e preenche o campo 'textoNomeCadastro'
     * na janela 'dados do cliente' para permitir edição ou exclusão do cliente selecionado.
     *
     * @param evt
     */
    private void tabelaExibicaoClientesListaMouseClicked(MouseEvent evt) {
        this.idSelecionado = null;
        new Utilities().limpaCampos(painelDadosCliente);

        painelComAbasCliente.setSelectedIndex(1);
        this.idSelecionado = Integer.parseInt(tabelaExibicaoClientesLista.getValueAt(tabelaExibicaoClientesLista.getSelectedRow(), 0).toString());
        Cliente cliente = clienteService.buscarClientePorId(idSelecionado.toString());

        textoNomeCadastro.setText(cliente.getNome());
        textoDocumentoCadastro.setText(setTextoSeNaoNuloOuVazio(cliente.getDocumento()));
        textoEmailCadastro.setText(setTextoSeNaoNuloOuVazio(cliente.getEmail()));
        textoCelularCadastro.setText(setTextoSeNaoNuloOuVazio(cliente.getCelular()));
        textoCepCadastro.setText(setTextoSeNaoNuloOuVazio(cliente.getCep()));
        textoEnderecoCadastro.setText(setTextoSeNaoNuloOuVazio(cliente.getEndereco()));
        textoNumeroCadastro.setText(setTextoSeNaoNuloOuVazio(cliente.getNumero()));
        textoComplementoCadastro.setText(setTextoSeNaoNuloOuVazio(cliente.getComplemento()));
        textoBairroCadastro.setText(setTextoSeNaoNuloOuVazio(cliente.getBairro()));
        textoCidadeCadastro.setText(setTextoSeNaoNuloOuVazio(cliente.getCidade()));
        textoEstadoCadastro.setSelectedItem(setTextoSeNaoNuloOuVazio(cliente.getEstado()));

        estadoAtual = ESTADO_EDITANDO;
        gerenciadorDeBotoes();
    }

    /**
     * Inicia a criação de um novo cliente.
     *
     * @param evt
     */
    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        estadoAtual = ESTADO_NOVO;
        gerenciadorDeBotoes();
    }//GEN-LAST:event_botaoNovoActionPerformed

    /**
     * realiza a chamada de uma sequência de métodos responsáveis por salvar um novo registro no banco de dados
     *
     * @param evt
     */
    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        String nome = textoNomeCadastro.getText().trim();
        String celular = textoCelularCadastro.getText();

        if (!nome.isEmpty() && !celular.isEmpty()) {
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setDocumento(textoDocumentoCadastro.getText());
            cliente.setEmail(textoEmailCadastro.getText());
            cliente.setCelular(celular);
            cliente.setCep(textoCepCadastro.getText());
            cliente.setEndereco(textoEnderecoCadastro.getText());
            cliente.setNumero(textoNumeroCadastro.getText());
            cliente.setComplemento(textoComplementoCadastro.getText());
            cliente.setBairro(textoBairroCadastro.getText());
            cliente.setCidade(textoCidadeCadastro.getText());
            cliente.setEstado(textoEstadoCadastro.getSelectedItem().toString());
            clienteService.cadastrarCliente(cliente);
            new Utilities().limpaCampos(painelDadosCliente);
            estadoAtual = ESTADO_INICIAL;
            gerenciadorDeBotoes();
            painelComAbasCliente.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(null, "Os campos nome e celular são obrigatórios");
        }

    }

    /**
     * realiza a chamada de uma sequência de métodos responsáveis pela edição de um registro presente no banco de dados
     * @param evt
     */
    private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.idSelecionado != null) {
            Cliente cliente = new Cliente();
            cliente.setId(this.idSelecionado);
            cliente.setNome(textoNomeCadastro.getText());
            cliente.setDocumento(textoDocumentoCadastro.getText());
            cliente.setEmail(textoEmailCadastro.getText());
            cliente.setCelular(textoCelularCadastro.getText());
            cliente.setCep(textoCepCadastro.getText());
            cliente.setEndereco(textoEnderecoCadastro.getText());
            cliente.setNumero(textoNumeroCadastro.getText());
            cliente.setComplemento(textoComplementoCadastro.getText());
            cliente.setBairro(textoBairroCadastro.getText());
            cliente.setCidade(textoCidadeCadastro.getText());
            cliente.setEstado(textoEstadoCadastro.getSelectedItem().toString());
            clienteService.editarCliente(cliente);
        }
        new Utilities().limpaCampos(painelDadosCliente);
        this.idSelecionado = null;
        estadoAtual = ESTADO_INICIAL;
        gerenciadorDeBotoes();
        painelComAbasCliente.setSelectedIndex(0);
    }

    /**
     * realiza a chamada de uma sequência de métodos responsáveis pela exclusão de um registro presente no banco de dados
     * @param evt
     */
    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.idSelecionado != null) {
            clienteService.excluirCliente(idSelecionado);
        }
        new Utilities().limpaCampos(painelDadosCliente);
        this.idSelecionado = null;
        estadoAtual = ESTADO_INICIAL;
        gerenciadorDeBotoes();
        painelComAbasCliente.setSelectedIndex(0);
    }

    /**
     * Limpa os campos e redefine o estado atual para o estado inicial.
     *
     * @param evt
     */
    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.estadoAtual = ESTADO_INICIAL;
        gerenciadorDeBotoes();
        this.idSelecionado = null;
        new Utilities().limpaCampos(painelDadosCliente);
        painelComAbasCliente.setSelectedIndex(0);
    }

    private String setTextoSeNaoNuloOuVazio(String texto) {
        if (texto != null && !texto.isEmpty()) {
            return texto;
        } else {
            return "";
        }
    }

    private void textoBairroCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoBairroCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoBairroCadastroActionPerformed

    private void textoNomeCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoNomeCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoNomeCadastroActionPerformed

    private void textoEnderecoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoEnderecoCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoEnderecoCadastroActionPerformed

    private void textoEmailCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoEmailCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoEmailCadastroActionPerformed

    private void textoNumeroCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoNumeroCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoNumeroCadastroActionPerformed

    private void textoComplementoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoComplementoCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoComplementoCadastroActionPerformed

    private void textoCidadeCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoCidadeCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoCidadeCadastroActionPerformed

    private void textoDocumentoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoDocumentoCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoDocumentoCadastroActionPerformed

    private void textoNomePesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoNomePesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoNomePesquisaActionPerformed

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
            java.util.logging.Logger.getLogger(ClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteView().setVisible(true);
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
        painelComAbasCliente = new javax.swing.JTabbedPane();
        painelConsultaClientes = new javax.swing.JPanel();
        campoNomePesquisa = new javax.swing.JLabel();
        textoNomePesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaExibicaoClientesLista = new javax.swing.JTable();
        painelDadosCliente = new javax.swing.JPanel();
        campoEmailCadastro = new javax.swing.JLabel();
        textoBairroCadastro = new javax.swing.JTextField();
        campoNomeCadastro = new javax.swing.JLabel();
        textoNomeCadastro = new javax.swing.JTextField();
        campoCelularCadastro = new javax.swing.JLabel();
        campoDocumentoCadastro = new javax.swing.JLabel();
        textoEnderecoCadastro = new javax.swing.JTextField();
        campoNumeroCadastro = new javax.swing.JLabel();
        campoBairroCadastro = new javax.swing.JLabel();
        textoEmailCadastro = new javax.swing.JTextField();
        campoEstadoCadastro = new javax.swing.JLabel();
        campoEnderecoCadastro = new javax.swing.JLabel();
        textoNumeroCadastro = new javax.swing.JTextField();
        campoComplementoCadastro = new javax.swing.JLabel();
        textoComplementoCadastro = new javax.swing.JTextField();
        campoCidadeCadastro = new javax.swing.JLabel();
        textoCidadeCadastro = new javax.swing.JTextField();
        campoCepCadastro1 = new javax.swing.JLabel();
        textoCepCadastro = new javax.swing.JFormattedTextField();
        textoCelularCadastro = new javax.swing.JFormattedTextField();
        textoDocumentoCadastro = new javax.swing.JFormattedTextField();
        textoEstadoCadastro = new javax.swing.JComboBox<>();
        botaoNovo = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        botaoEditar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro Clientes");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Cadastro de Clientes");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        painelComAbasCliente.setBackground(new java.awt.Color(255, 255, 255));
        painelComAbasCliente.setForeground(new java.awt.Color(0, 0, 0));
        painelComAbasCliente.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        painelConsultaClientes.setBackground(new java.awt.Color(204, 204, 204));

        campoNomePesquisa.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        campoNomePesquisa.setForeground(new java.awt.Color(0, 0, 0));
        campoNomePesquisa.setText("Nome:");
        campoNomePesquisa.setBorder(new javax.swing.border.MatteBorder(null));

        textoNomePesquisa.setBackground(new java.awt.Color(255, 255, 255));
        textoNomePesquisa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoNomePesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoNomePesquisaActionPerformed(evt);
            }
        });
        textoNomePesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoNomePesquisaKeyPressed(evt);
            }
        });

        tabelaExibicaoClientesLista.setBackground(new java.awt.Color(255, 255, 255));
        tabelaExibicaoClientesLista.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tabelaExibicaoClientesLista.setForeground(new java.awt.Color(0, 0, 0));
        tabelaExibicaoClientesLista.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaExibicaoClientesLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaExibicaoClientesListaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaExibicaoClientesLista);
        if (tabelaExibicaoClientesLista.getColumnModel().getColumnCount() > 0) {
            tabelaExibicaoClientesLista.getColumnModel().getColumn(0).setMaxWidth(100);
            tabelaExibicaoClientesLista.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        javax.swing.GroupLayout painelConsultaClientesLayout = new javax.swing.GroupLayout(painelConsultaClientes);
        painelConsultaClientes.setLayout(painelConsultaClientesLayout);
        painelConsultaClientesLayout.setHorizontalGroup(
            painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(campoNomePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoNomePesquisa)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
        );
        painelConsultaClientesLayout.setVerticalGroup(
            painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelConsultaClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoNomePesquisa)
                    .addComponent(campoNomePesquisa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
        );

        painelComAbasCliente.addTab("Consulta de Clientes", painelConsultaClientes);

        painelDadosCliente.setBackground(new java.awt.Color(204, 204, 204));
        painelDadosCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        campoEmailCadastro.setBackground(new java.awt.Color(255, 255, 255));
        campoEmailCadastro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoEmailCadastro.setForeground(new java.awt.Color(0, 0, 0));
        campoEmailCadastro.setText("E-mail");
        campoEmailCadastro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        textoBairroCadastro.setBackground(new java.awt.Color(255, 255, 255));
        textoBairroCadastro.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        textoBairroCadastro.setForeground(new java.awt.Color(0, 0, 0));
        textoBairroCadastro.setBorder(new javax.swing.border.MatteBorder(null));
        textoBairroCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoBairroCadastroActionPerformed(evt);
            }
        });

        campoNomeCadastro.setBackground(new java.awt.Color(255, 255, 255));
        campoNomeCadastro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoNomeCadastro.setForeground(new java.awt.Color(0, 0, 0));
        campoNomeCadastro.setText("Nome");
        campoNomeCadastro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        textoNomeCadastro.setBackground(new java.awt.Color(255, 255, 255));
        textoNomeCadastro.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        textoNomeCadastro.setForeground(new java.awt.Color(0, 0, 0));
        textoNomeCadastro.setBorder(new javax.swing.border.MatteBorder(null));
        textoNomeCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoNomeCadastroActionPerformed(evt);
            }
        });

        campoCelularCadastro.setBackground(new java.awt.Color(255, 255, 255));
        campoCelularCadastro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoCelularCadastro.setForeground(new java.awt.Color(0, 0, 0));
        campoCelularCadastro.setText("Celular");
        campoCelularCadastro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        campoDocumentoCadastro.setBackground(new java.awt.Color(255, 255, 255));
        campoDocumentoCadastro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoDocumentoCadastro.setForeground(new java.awt.Color(0, 0, 0));
        campoDocumentoCadastro.setText("Documento");
        campoDocumentoCadastro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        textoEnderecoCadastro.setBackground(new java.awt.Color(255, 255, 255));
        textoEnderecoCadastro.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        textoEnderecoCadastro.setForeground(new java.awt.Color(0, 0, 0));
        textoEnderecoCadastro.setBorder(new javax.swing.border.MatteBorder(null));
        textoEnderecoCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoEnderecoCadastroActionPerformed(evt);
            }
        });

        campoNumeroCadastro.setBackground(new java.awt.Color(255, 255, 255));
        campoNumeroCadastro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoNumeroCadastro.setForeground(new java.awt.Color(0, 0, 0));
        campoNumeroCadastro.setText("Número");
        campoNumeroCadastro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        campoBairroCadastro.setBackground(new java.awt.Color(255, 255, 255));
        campoBairroCadastro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoBairroCadastro.setForeground(new java.awt.Color(0, 0, 0));
        campoBairroCadastro.setText("Bairro");
        campoBairroCadastro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        textoEmailCadastro.setBackground(new java.awt.Color(255, 255, 255));
        textoEmailCadastro.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        textoEmailCadastro.setForeground(new java.awt.Color(0, 0, 0));
        textoEmailCadastro.setBorder(new javax.swing.border.MatteBorder(null));
        textoEmailCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoEmailCadastroActionPerformed(evt);
            }
        });

        campoEstadoCadastro.setBackground(new java.awt.Color(255, 255, 255));
        campoEstadoCadastro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoEstadoCadastro.setForeground(new java.awt.Color(0, 0, 0));
        campoEstadoCadastro.setText("UF");
        campoEstadoCadastro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        campoEnderecoCadastro.setBackground(new java.awt.Color(255, 255, 255));
        campoEnderecoCadastro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoEnderecoCadastro.setForeground(new java.awt.Color(0, 0, 0));
        campoEnderecoCadastro.setText("Endereço");
        campoEnderecoCadastro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        textoNumeroCadastro.setBackground(new java.awt.Color(255, 255, 255));
        textoNumeroCadastro.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        textoNumeroCadastro.setForeground(new java.awt.Color(0, 0, 0));
        textoNumeroCadastro.setBorder(new javax.swing.border.MatteBorder(null));
        textoNumeroCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoNumeroCadastroActionPerformed(evt);
            }
        });

        campoComplementoCadastro.setBackground(new java.awt.Color(255, 255, 255));
        campoComplementoCadastro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoComplementoCadastro.setForeground(new java.awt.Color(0, 0, 0));
        campoComplementoCadastro.setText("Complemento");
        campoComplementoCadastro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        textoComplementoCadastro.setBackground(new java.awt.Color(255, 255, 255));
        textoComplementoCadastro.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        textoComplementoCadastro.setForeground(new java.awt.Color(0, 0, 0));
        textoComplementoCadastro.setBorder(new javax.swing.border.MatteBorder(null));
        textoComplementoCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoComplementoCadastroActionPerformed(evt);
            }
        });

        campoCidadeCadastro.setBackground(new java.awt.Color(255, 255, 255));
        campoCidadeCadastro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoCidadeCadastro.setForeground(new java.awt.Color(0, 0, 0));
        campoCidadeCadastro.setText("Cidade");
        campoCidadeCadastro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        textoCidadeCadastro.setBackground(new java.awt.Color(255, 255, 255));
        textoCidadeCadastro.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        textoCidadeCadastro.setForeground(new java.awt.Color(0, 0, 0));
        textoCidadeCadastro.setBorder(new javax.swing.border.MatteBorder(null));
        textoCidadeCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoCidadeCadastroActionPerformed(evt);
            }
        });

        campoCepCadastro1.setBackground(new java.awt.Color(255, 255, 255));
        campoCepCadastro1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoCepCadastro1.setForeground(new java.awt.Color(0, 0, 0));
        campoCepCadastro1.setText("CEP");
        campoCepCadastro1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        textoCepCadastro.setBackground(new java.awt.Color(255, 255, 255));
        textoCepCadastro.setForeground(new java.awt.Color(0, 0, 0));
        try {
            textoCepCadastro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##### - ###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textoCepCadastro.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N

        textoCelularCadastro.setBackground(new java.awt.Color(255, 255, 255));
        textoCelularCadastro.setForeground(new java.awt.Color(0, 0, 0));
        try {
            textoCelularCadastro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textoCelularCadastro.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N

        textoDocumentoCadastro.setBackground(new java.awt.Color(255, 255, 255));
        textoDocumentoCadastro.setForeground(new java.awt.Color(0, 0, 0));
        try {
            textoDocumentoCadastro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textoDocumentoCadastro.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        textoDocumentoCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoDocumentoCadastroActionPerformed(evt);
            }
        });

        textoEstadoCadastro.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        textoEstadoCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SP", "RS", "RJ", "MG", "ES", "DF", "GO", "MT", "MS", "SC", "PR", "BA", "SE", "AL", "AM", "RO", "PE", "RR", "AC", "PB", "PA", "AP", "RN", "CE", "PI", "TO", "MA" }));

        javax.swing.GroupLayout painelDadosClienteLayout = new javax.swing.GroupLayout(painelDadosCliente);
        painelDadosCliente.setLayout(painelDadosClienteLayout);
        painelDadosClienteLayout.setHorizontalGroup(
            painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosClienteLayout.createSequentialGroup()
                        .addComponent(campoNomeCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoNomeCadastro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoCelularCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoCelularCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDadosClienteLayout.createSequentialGroup()
                        .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDadosClienteLayout.createSequentialGroup()
                                .addComponent(campoCepCadastro1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoCepCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoBairroCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoBairroCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoEstadoCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6))
                            .addGroup(painelDadosClienteLayout.createSequentialGroup()
                                .addComponent(campoEnderecoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoEnderecoCadastro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoNumeroCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoNumeroCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(textoEstadoCadastro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(painelDadosClienteLayout.createSequentialGroup()
                        .addComponent(campoComplementoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoComplementoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoCidadeCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoCidadeCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 166, Short.MAX_VALUE))
                    .addGroup(painelDadosClienteLayout.createSequentialGroup()
                        .addComponent(campoDocumentoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoDocumentoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoEmailCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoEmailCadastro)))
                .addContainerGap())
        );
        painelDadosClienteLayout.setVerticalGroup(
            painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosClienteLayout.createSequentialGroup()
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoCelularCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textoCelularCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addGroup(painelDadosClienteLayout.createSequentialGroup()
                        .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoNomeCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(textoNomeCadastro))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoDocumentoCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textoDocumentoCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(campoEmailCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textoEmailCadastro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoCepCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addComponent(campoCepCadastro1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelDadosClienteLayout.createSequentialGroup()
                        .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoBairroCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoBairroCadastro)
                            .addComponent(campoEstadoCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoEstadoCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(14, 14, 14)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoEnderecoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoEnderecoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNumeroCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNumeroCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoComplementoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoComplementoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCidadeCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoCidadeCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        painelComAbasCliente.addTab("Cadastro Cliente", painelDadosCliente);

        botaoNovo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botaoNovo.setText("NOVO");
        botaoNovo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoActionPerformed(evt);
            }
        });

        botaoSalvar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botaoSalvar.setText("SALVAR");
        botaoSalvar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botaoEditar.setText("EDITAR");
        botaoEditar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarActionPerformed(evt);
            }
        });

        botaoExcluir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botaoExcluir.setText("EXCLUIR");
        botaoExcluir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        botaoLimpar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botaoLimpar.setText("LIMPAR");
        botaoLimpar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botaoLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoLimparMouseClicked(evt);
            }
        });
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelComAbasCliente)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelComAbasCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botaoEditar, botaoExcluir, botaoLimpar, botaoSalvar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEditar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JLabel campoBairroCadastro;
    private javax.swing.JLabel campoCelularCadastro;
    private javax.swing.JLabel campoCepCadastro1;
    private javax.swing.JLabel campoCidadeCadastro;
    private javax.swing.JLabel campoComplementoCadastro;
    private javax.swing.JLabel campoDocumentoCadastro;
    private javax.swing.JLabel campoEmailCadastro;
    private javax.swing.JLabel campoEnderecoCadastro;
    private javax.swing.JLabel campoEstadoCadastro;
    private javax.swing.JLabel campoNomeCadastro;
    private javax.swing.JLabel campoNomePesquisa;
    private javax.swing.JLabel campoNumeroCadastro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTabbedPane painelComAbasCliente;
    private javax.swing.JPanel painelConsultaClientes;
    private javax.swing.JPanel painelDadosCliente;
    public javax.swing.JTable tabelaExibicaoClientesLista;
    private javax.swing.JTextField textoBairroCadastro;
    private javax.swing.JFormattedTextField textoCelularCadastro;
    private javax.swing.JFormattedTextField textoCepCadastro;
    private javax.swing.JTextField textoCidadeCadastro;
    private javax.swing.JTextField textoComplementoCadastro;
    private javax.swing.JFormattedTextField textoDocumentoCadastro;
    private javax.swing.JTextField textoEmailCadastro;
    private javax.swing.JTextField textoEnderecoCadastro;
    private javax.swing.JComboBox<String> textoEstadoCadastro;
    private javax.swing.JTextField textoNomeCadastro;
    private javax.swing.JTextField textoNomePesquisa;
    private javax.swing.JTextField textoNumeroCadastro;
    // End of variables declaration//GEN-END:variables
}
