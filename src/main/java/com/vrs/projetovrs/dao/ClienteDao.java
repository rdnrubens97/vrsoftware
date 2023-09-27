package com.vrs.projetovrs.dao;

import com.vrs.projetovrs.databaseconfig.ConnectionFactory;
import com.vrs.projetovrs.model.Cliente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    private Connection conn;
    public ClienteDao() {
        this.conn = new ConnectionFactory().getConnection();
    }

    /**
     * método responsável por persistir um novo registro de cliente no banco de dados
     * @param cliente passamos como parâmetro uma instância da classe Cliente, de onde buscaremos as informações a serem registradas no banco
     */
    public void cadastrarCliente(Cliente cliente) {
        String sql = "INSERT INTO tb_clientes (nome) VALUES (?) ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e);
        }
    }

    /**
     * lista todos os registros presentes na tabela "tb_clientes" e retorna uma lista de objetos da classe Cliente.
     * @return
     */
    public List<Cliente> listarClientes() {
        String sql = "SELECT * FROM tb_clientes";
        try {
            List<Cliente> lista = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();

                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");

                cliente.setId(id);
                cliente.setNome(nome);
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }

    /**
     * faz a exclusão de determinado registro presente no banco de dados na tabela "tb_clientes".
     *
     * @param id passamos um id como parâmetro, de modo a excluirmos um registro específico.
     */
    public void excluirCliente(Integer id) {
        String sql = "DELETE FROM tb_clientes WHERE id = (?) ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não é possível excluir esse cliente do banco de dados. " +
                    "\nO mesmo está presente em seus registros de venda. \nErro: " + e);
        }
    }

    /**
     * Editamos um registro presente na tabela "tb_clientes"
     * @param cliente passamos um objeto cliente como parâmetro
     */
    public void editarCliente(Cliente cliente) {
        String sql = "UPDATE tb_clientes SET nome = ? WHERE id = ? ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getId());
            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente editado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar: " + e);
        }
    }

    /**
     * listamos os registros de clientes na "tb_clientes" com base em determinado nome passado como parâmetro
     * @param nomeDesejado
     * @return
     */
    public List<Cliente> listarClientesPorNome(String nomeDesejado) {
        String sql = "SELECT * FROM tb_clientes WHERE nome LIKE ?";
        try {
            List<Cliente> lista = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeDesejado.toUpperCase() + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");
                cliente.setId(id);
                cliente.setNome(nome);
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }

    /**
     * Busca um cliente na "tb_clientes" com base em um ID específico.
     *
     * @param id O ID do cliente a ser buscado.
     * @return Um objeto Cliente correspondente ao ID, ou null se não encontrado.
     */
    public Cliente buscarClientePorId(String id) {
        String sql = "SELECT * FROM tb_clientes WHERE id = ?";
        try {
            Integer idCliente;
            String nomeCliente;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                idCliente = rs.getInt("id");
                nomeCliente = rs.getString("nome");

                Cliente cliente = new Cliente();
                cliente.setId(idCliente);
                cliente.setNome(nomeCliente);

                return cliente;
            }
            else {
                JOptionPane.showMessageDialog(null, "Cliente com identificador " + id + " não encontrado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);

        }
        return null;
    }


}
