package com.vrs.projetovrs.service;

import com.vrs.projetovrs.dao.ClienteDao;
import com.vrs.projetovrs.model.Cliente;

import java.util.List;

public class ClienteService {
    private ClienteDao clienteDao;
    public ClienteService(ClienteDao dao) {
        this.clienteDao = dao;
    }

    public List<Cliente> listarClientes() {
        return clienteDao.listarClientes();
    }

    public List<Cliente> listarClientesPorNome(String nomeDesejado) {
        return clienteDao.listarClientesPorNome(nomeDesejado);
    }

    public Cliente buscarClientePorId(String idCliente) {
        return clienteDao.buscarClientePorId(idCliente);
    }

    public void cadastrarCliente(Cliente cliente) {
        clienteDao.cadastrarCliente(cliente);
    }

    public void editarCliente(Cliente cliente) {
        clienteDao.editarCliente(cliente);
    }

    public void excluirCliente(Integer idCliente) {
        clienteDao.excluirCliente(idCliente);
    }

}
