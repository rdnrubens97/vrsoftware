package com.vrs.projetovrs.service;

import com.vrs.projetovrs.dao.ServicoDao;
import com.vrs.projetovrs.exception.ValidationException;
import com.vrs.projetovrs.model.Servico;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ServicoService {
    private ServicoDao servicoDao;
    public ServicoService(ServicoDao servicoDao) {
        this.servicoDao = servicoDao;
    }

    public void salvarServico(Servico servico) {
        try {
            if (!verificaSeENulo(servico)) {
                throw new ValidationException();
            }
            servicoDao.salvarServico(servico);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Servico> listarServicosDeUmaVenda(Integer idVenda) {
        try {
            return servicoDao.listarServicosDeUmaVenda(idVenda);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + idVenda);
            throw new RuntimeException(e.getMessage());
        }
    }

    // Métodos de suporte

    private boolean verificaSeENulo(Servico servico) {
        Integer idVenda = servico.getIdVenda();
        String descricao = servico.getDescricao();
        BigDecimal preco = servico.getPreco();
        if (idVenda == null || descricao == null || preco == null ) {
            return false;
        }
        return true;
    }

}
