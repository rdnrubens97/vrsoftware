package com.vrs.projetovrs.service;

import com.vrs.projetovrs.dao.ClienteDao;
import com.vrs.projetovrs.exception.ValidationException;
import com.vrs.projetovrs.model.Cliente;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClienteService {
    private ClienteDao clienteDao;
    public ClienteService(ClienteDao dao) {
        this.clienteDao = dao;
    }

    public void cadastrarCliente(Cliente cliente) {
        try {
            if (!verificarAtributosMinimosParaCliente(cliente)) {
                throw new ValidationException();
            }
            clienteDao.cadastrarCliente(cliente);
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

    public void editarCliente(Cliente cliente) {
        try {
            clienteDao.editarCliente(cliente);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
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

    // Métodos auxiliares:
    private boolean verificarAtributosMinimosParaCliente(Cliente cliente) {
        String nome = cliente.getNome() != null ? cliente.getNome() : null;
        String contato = cliente.getCelular() != null ? cliente.getCelular() : null;
        if (nome == null || contato == null) {
            return false;
        }
        return true;
    }


}
