-- Cria a tabela VENDAS se não existir
CREATE TABLE IF NOT EXISTS tb_vendas (
  id serial primary key,
  data_venda timestamp NOT NULL,
  id_cliente int NOT NULL,
  valor_total numeric(10, 2) NOT NULL,
  status varchar NOT NULL,

  FOREIGN KEY (id_cliente) REFERENCES tb_clientes(id)
);