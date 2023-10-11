package com.vrs.projetovrs.service;

import com.vrs.projetovrs.dao.ClienteDao;
import com.vrs.projetovrs.model.Cliente;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class ClienteService {
    private ClienteDao clienteDao;
    public ClienteService(ClienteDao dao) {
        this.clienteDao = dao;
    }

    public List<Cliente> listarClientes() {
        try {
            return clienteDao.listarClientes();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public List<Cliente> listarClientesPorNome(String nomeDesejado) {
        try {
            return clienteDao.listarClientesPorNome(nomeDesejado);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public Cliente buscarClientePorId(String idCliente) {
        try {
            return clienteDao.buscarClientePorId(idCliente);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public void cadastrarCliente(Cliente cliente) {
        try {
            clienteDao.cadastrarCliente(cliente);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public void editarCliente(Cliente cliente) {
        try {
            clienteDao.editarCliente(cliente);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public void excluirCliente(Integer idCliente) {
        try {
            clienteDao.excluirCliente(idCliente);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

}
