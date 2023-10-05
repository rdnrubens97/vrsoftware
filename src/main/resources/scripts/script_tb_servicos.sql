-- Cria a tabela SERVICOS se não existir
CREATE TABLE IF NOT EXISTS tb_servicos (
  id serial primary key,
  descricao varchar(100),
  preco numeric(10, 2)
);