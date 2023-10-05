package com.vrs.projetovrs.service;

import com.vrs.projetovrs.dao.VendaDao;
import com.vrs.projetovrs.model.ItemVenda;
import com.vrs.projetovrs.model.Venda;
import com.vrs.projetovrs.model.status.Status;

import java.time.LocalDate;
import java.util.List;

public class VendaService {
    private VendaDao vendaDao;
    public VendaService(VendaDao dao) {
        this.vendaDao = dao;
    }

    public void cadastrarVenda(Venda venda) {
        vendaDao.cadastrarVenda(venda);
    }

    public List<Venda> listarVendasDoUltimoMes() {
        return vendaDao.listarVendasDoUltimoMes();
    }

    public List<Venda> listarVendasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return vendaDao.listarVendasPorPeriodo(dataInicio, dataFim);
    }

    public void estornarVendaPorId(Integer idVenda, Status status) {
        vendaDao.estornarVendaPorId(idVenda, status);
    }

    public Integer retornaIdDaUltimaVenda() {
        return vendaDao.retornaIdDaUltimaVenda();
    }

}
