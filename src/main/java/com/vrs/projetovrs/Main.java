package com.vrs.projetovrs;

import com.vrs.projetovrs.dao.ItemVendaDao;
import com.vrs.projetovrs.dao.ProdutoDao;
import com.vrs.projetovrs.dao.VendaDao;
import com.vrs.projetovrs.model.ItemVenda;
import com.vrs.projetovrs.model.Produto;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ItemVendaDao dao = new ItemVendaDao();
        List<ItemVenda> produto =  dao.listaItensDeUmaVenda(1);
        System.out.println(produto);
    }
}
