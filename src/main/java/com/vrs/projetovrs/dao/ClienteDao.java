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
        String sql = "INSERT INTO tb_clientes (nome, documento, email, celular, cep, endereco, numero, complemento, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getDocumento());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getCelular());
            stmt.setString(5, cliente.getCep());
            stmt.setString(6, cliente.getEndereco());
            stmt.setString(7, cliente.getNumero());
            stmt.setString(8, cliente.getComplemento());
            stmt.setString(9, cliente.getBairro());
            stmt.setString(10, cliente.getCidade());
            stmt.setString(11, cliente.getEstado());
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
                String documento = rs.getString("documento");
                String email = rs.getString("email");
                String celular = rs.getString("celular");
                String cep = rs.getString("cep");
                String endereco = rs.getString("endereco");
                String numero = rs.getString("numero");
                String complemento = rs.getString("complemento");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");

                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setDocumento(documento);
                cliente.setEmail(email);
                cliente.setCelular(celular);
                cliente.setCep(cep);
                cliente.setEndereco(endereco);
                cliente.setNumero(numero);
                cliente.setComplemento(complemento);
                cliente.setBairro(bairro);
                cliente.setCidade(cidade);
                cliente.setEstado(estado);
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
        String sql = "UPDATE tb_clientes SET nome = ?, documento = ?, email = ?, celular = ?, cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ? WHERE id = ? ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getDocumento());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getCelular());
            stmt.setString(5, cliente.getCep());
            stmt.setString(6, cliente.getEndereco());
            stmt.setString(7, cliente.getNumero());
            stmt.setString(8, cliente.getComplemento());
            stmt.setString(9, cliente.getBairro());
            stmt.setString(10, cliente.getCidade());
            stmt.setString(11, cliente.getEstado());
            stmt.setInt(12, cliente.getId());
            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente editado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar: " + e);
        }
    }

//    public Integer buscarIdClientePorCodigo(String codigo) {
//        String sql = "SELECT id FROM";
//    }

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
                String documento = rs.getString("documento");
                String email = rs.getString("email");
                String celular = rs.getString("celular");
                String cep = rs.getString("cep");
                String endereco = rs.getString("endereco");
                String numero = rs.getString("numero");
                String complemento = rs.getString("complemento");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");

                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setDocumento(documento);
                cliente.setEmail(email);
                cliente.setCelular(celular);
                cliente.setCep(cep);
                cliente.setEndereco(endereco);
                cliente.setNumero(numero);
                cliente.setComplemento(complemento);
                cliente.setBairro(bairro);
                cliente.setCidade(cidade);
                cliente.setEstado(estado);

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
            String nome, documento, email, celular, cep, endereco, numero, complemento, bairro, cidade, estado;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                idCliente = rs.getInt("id");
                nome = rs.getString("nome");
                documento = rs.getString("documento");
                email = rs.getString("email");
                celular = rs.getString("celular");
                cep = rs.getString("cep");
                endereco = rs.getString("endereco");
                numero = rs.getString("numero");
                complemento = rs.getString("complemento");
                bairro = rs.getString("bairro");
                cidade = rs.getString("cidade");
                estado = rs.getString("estado");

                Cliente cliente = new Cliente();
                cliente.setId(idCliente);
                cliente.setNome(nome);
                cliente.setDocumento(documento);
                cliente.setEmail(email);
                cliente.setCelular(celular);
                cliente.setCep(cep);
                cliente.setEndereco(endereco);
                cliente.setNumero(numero);
                cliente.setComplemento(complemento);
                cliente.setBairro(bairro);
                cliente.setCidade(cidade);
                cliente.setEstado(estado);

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
