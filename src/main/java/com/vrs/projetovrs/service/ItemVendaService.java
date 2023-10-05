package com.vrs.projetovrs.service;

import com.vrs.projetovrs.dao.ItemVendaDao;
import com.vrs.projetovrs.model.ItemVenda;

import java.util.List;

public class ItemVendaService {
    private ItemVendaDao itemVendaDao;
    public ItemVendaService(ItemVendaDao dao) {
        this.itemVendaDao = dao;
    }

    public List<ItemVenda> listaItensDeUmaVenda(Integer idVenda) {
        return itemVendaDao.listaItensDeUmaVenda(idVenda);
    }

}
