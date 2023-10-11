-- Cria a tabela ITENS_VENDAS se não existir
CREATE TABLE IF NOT EXISTS tb_itensvendas (
  id serial primary key,
  id_venda int NOT NULL,
  id_produto int NOT NULL,
  quantidade int NOT NULL,
  preco numeric(10, 2) NOT NULL,
  valor_total numeric(10, 2) NOT NULL,

  CONSTRAINT fk_id_venda FOREIGN KEY (id_venda) REFERENCES tb_vendas(id),
  CONSTRAINT fk_id_produto FOREIGN KEY (id_produto) REFERENCES tb_produtos(id)
);