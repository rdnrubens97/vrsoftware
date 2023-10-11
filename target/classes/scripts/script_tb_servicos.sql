-- Cria a tabela SERVICOS se não existir
CREATE TABLE IF NOT EXISTS tb_servicos (
  id serial primary key,
  id_venda int NOT NULL,
  descricao varchar(100) NOT NULL,
  preco numeric(10, 2) NOT NULL,

  CONSTRAINT fk_id_venda FOREIGN KEY (id_venda) REFERENCES tb_vendas(id)
);