package com.vrs.projetovrs.service;

import com.vrs.projetovrs.dao.VendaDao;
import com.vrs.projetovrs.model.Venda;
import com.vrs.projetovrs.model.status.Status;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class VendaService {
    private VendaDao vendaDao;
    public VendaService(VendaDao dao) {
        this.vendaDao = dao;
    }

    public void cadastrarVenda(Venda venda) {
        try {
            vendaDao.cadastrarVenda(venda);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public List<Venda> listarVendasDoUltimoMes() {
        try {
            return vendaDao.listarVendasDoUltimoMes();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public List<Venda> listarVendasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        try {
            return vendaDao.listarVendasPorPeriodo(dataInicio, dataFim);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public void estornarVendaPorId(Integer idVenda, Status status) {
        try {
            vendaDao.estornarVendaPorId(idVenda, status);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public Integer retornarIdDaUltimaVenda() {
        try {
            return vendaDao.retornarIdDaUltimaVenda();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public List<Venda> retornarVendasPorNome(String nome) {
        try {
            return vendaDao.retornarVendasPorNome(nome);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

}
