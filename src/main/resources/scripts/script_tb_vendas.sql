-- Cria a tabela VENDAS se não existir
CREATE TABLE IF NOT EXISTS tb_vendas (
  id serial primary key,
  data_venda timestamp,
  id_cliente int,
  valor_total numeric(10, 2),
  status varchar,

  FOREIGN KEY (id_cliente) REFERENCES tb_clientes(id)
);