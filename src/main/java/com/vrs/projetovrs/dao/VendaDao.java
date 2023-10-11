package com.vrs.projetovrs.dao;

import com.vrs.projetovrs.databaseconfig.ConnectionFactory;
import com.vrs.projetovrs.model.Cliente;
import com.vrs.projetovrs.model.Venda;
import com.vrs.projetovrs.model.status.Status;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendaDao {
    private Connection conn;

    public VendaDao() {
        this.conn = new ConnectionFactory().getConnection();
    }

    /**
     * Cadastra uma venda no banco de dados.
     *
     * @param venda A venda a ser cadastrada.
     */
    public void cadastrarVenda(Venda venda) throws SQLException {
        String sql = "INSERT INTO tb_vendas (data_venda, id_cliente, valor_total, status) VALUES (?, ?, ?, ?) ";

        PreparedStatement stmt = conn.prepareStatement(sql);

        java.sql.Date dataSql = new java.sql.Date(venda.getData().getTime());

        stmt.setDate(1, dataSql);
        stmt.setInt(2, venda.getCliente().getId());
        stmt.setBigDecimal(3, venda.getValorTotal());
        stmt.setString(4, venda.getStatus().toString());

        stmt.executeUpdate();
        stmt.close();

    }

    /**
     * Retorna o ID da última venda cadastrada no banco de dados.
     *
     * @return O ID da última venda ou null se não houver vendas cadastradas.
     */
    public Integer retornarIdDaUltimaVenda() throws SQLException {
        String sql = "SELECT max(id) as max_id FROM tb_vendas"; // Use "as" para nomear a coluna resultante

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int maxId = rs.getInt("max_id");
            return maxId;
        }
        return null;
    }

    /**
     * Lista vendas dentro de um período específico.
     *
     * @param dataInicio A data de início do período.
     * @param dataFim    A data de fim do período.
     * @return Uma lista de vendas dentro do período especificado.
     */
    public List<Venda> listarVendasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) throws SQLException {
        String sql = """
                SELECT v.id, v.data_venda, c.nome, v.valor_total, v.status
                FROM tb_vendas AS v
                INNER JOIN tb_clientes AS c ON (v.id_cliente = c.id)
                WHERE v.data_venda BETWEEN ? AND ?;                                
                """;

        List<Venda> lista = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setDate(1, java.sql.Date.valueOf(dataInicio));
        stmt.setDate(2, java.sql.Date.valueOf(dataFim));

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Venda venda = new Venda();
            Cliente cliente = new Cliente();

            venda.setId(rs.getInt("id"));
            venda.setData(rs.getDate("data_venda"));
            cliente.setNome(rs.getString("nome"));
            venda.setValorTotal(rs.getBigDecimal("valor_total"));
            venda.setStatus(Status.valueOf(rs.getString("status")));
            venda.setCliente(cliente);

            lista.add(venda);
        }
        return lista;

    }

    public List<Venda> retornarVendasPorNome(String nome) throws SQLException {
        String sql = """
                SELECT tb_vendas.*, tb_clientes.nome as cliente_nome
                FROM tb_vendas
                INNER JOIN tb_clientes ON tb_vendas.id_cliente = tb_clientes.id
                WHERE tb_clientes.nome LIKE ?;
                """;
        List<Venda> vendas = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + nome + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Venda venda = new Venda();
            Cliente cliente = new Cliente();

            venda.setId(rs.getInt("id"));
            venda.setData(rs.getDate("data_venda"));
            cliente.setNome(rs.getString("cliente_nome"));
            venda.setValorTotal(rs.getBigDecimal("valor_total"));
            venda.setStatus(Status.valueOf(rs.getString("status")));
            venda.setCliente(cliente);

            vendas.add(venda);
        }
        return vendas;
    }


    public List<Venda> listarVendasDoUltimoMes() throws SQLException {
        LocalDate dataFim = LocalDate.now();
        LocalDate dataInicio = dataFim.minusMonths(1);

        String sql = """
                SELECT v.id, v.data_venda, c.nome, v.valor_total, v.status
                FROM tb_vendas AS v
                INNER JOIN tb_clientes AS c ON (v.id_cliente = c.id)
                WHERE v.data_venda BETWEEN ? AND ?;                                
                """;


        List<Venda> lista = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setDate(1, java.sql.Date.valueOf(dataInicio));
        stmt.setDate(2, java.sql.Date.valueOf(dataFim));

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Venda venda = new Venda();
            Cliente cliente = new Cliente();

            venda.setId(rs.getInt("id"));
            venda.setData(rs.getDate("data_venda"));
            cliente.setNome(rs.getString("nome"));
            venda.setValorTotal(rs.getBigDecimal("valor_total"));
            venda.setStatus(Status.valueOf(rs.getString("status")));
            venda.setCliente(cliente);

            lista.add(venda);
        }
        return lista;

    }

    /**
     * Consulta o valor total das vendas em uma data específica.
     *
     * @param data A data para a qual se deseja consultar o valor total das vendas.
     * @return O valor total das vendas na data especificada.
     */
    public BigDecimal consultarTotalVendaPorData(LocalDate data) throws SQLException {
        String sql = "SELECT sum(valor_total) as total FROM tb_vendas WHERE data_venda = ?";
        BigDecimal totalVendido = BigDecimal.ZERO;


        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setTimestamp(1, Timestamp.valueOf(data.atStartOfDay()));

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            totalVendido = rs.getBigDecimal("total");
        }

        return totalVendido;

    }

    /**
     * Realiza o estorno de uma venda com base no seu ID e atualiza seu status.
     *
     * @param idVenda O ID da venda a ser estornada.
     * @param status  O novo status da venda após o estorno.
     */
    public void estornarVendaPorId(Integer idVenda, Status status) throws SQLException {
        devolverEstoqueEstorno(idVenda);
        eliminarRegistrosDaTbItensVendasParaEstorno(idVenda);
        atualizarStatusVendaAposEstorno(status, idVenda);
    }

    // Métodos privados utilizados internamente:

    /**
     * Devolve o estoque dos produtos relacionados a um estorno de venda.
     * Se a venda foi realizada, houve a diminuição de seu estoque no banco de dados.
     * No momento do estorno, adicionamos a quantidade que foi retirada do estoque no momento da venda
     *
     * @param idVenda O ID da venda a ser estornada.
     */
    private void devolverEstoqueEstorno(Integer idVenda) throws SQLException {
        String sql = """
                UPDATE tb_produtos AS p
                SET quantidade_estoque = quantidade_estoque + COALESCE(
                    (SELECT SUM(i.quantidade)
                     FROM tb_itensvendas AS i
                     WHERE i.id_venda = ?
                     AND i.id_produto = p.id
                    ), 0
                );
                """;

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idVenda);
        stmt.executeUpdate();
        stmt.close();

    }

    /**
     * Elimina os registros dos itens de venda relacionados a uma venda que sera estornada
     *
     * @param idVenda O ID da venda a ser estornada.
     */
    private void eliminarRegistrosDaTbItensVendasParaEstorno(Integer idVenda) throws SQLException {
        String sql = "DELETE FROM tb_itensvendas WHERE id_venda = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idVenda);
        stmt.executeUpdate();
        stmt.close();

    }

    /**
     * Atualiza o status de uma venda após o estorno.
     *
     * @param status  O novo status da venda.
     * @param idVenda O ID da venda a ser atualizada.
     */
    private void atualizarStatusVendaAposEstorno(Status status, Integer idVenda) throws SQLException {
        String sql = "UPDATE tb_vendas SET status = ? WHERE id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, status.toString());
        stmt.setInt(2, idVenda);
        stmt.executeUpdate();
        stmt.close();

    }


}
