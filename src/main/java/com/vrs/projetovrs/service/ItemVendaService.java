package com.vrs.projetovrs.service;

import com.vrs.projetovrs.dao.ItemVendaDao;
import com.vrs.projetovrs.model.ItemVenda;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class ItemVendaService {
    private ItemVendaDao itemVendaDao;

    public ItemVendaService(ItemVendaDao dao) {
        this.itemVendaDao = dao;
    }

    public void cadastrarItem(ItemVenda itemVenda) {
        try {
            itemVendaDao.cadastrarItem(itemVenda);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public List<ItemVenda> listaItensDeUmaVenda(Integer idVenda) {
        try {
            return itemVendaDao.listaItensDeUmaVenda(idVenda);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException("Erro: " + e.getMessage());
        }

    }

}
