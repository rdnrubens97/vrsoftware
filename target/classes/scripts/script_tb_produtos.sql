-- Cria a tabela PRODUTOS se não existir
CREATE TABLE IF NOT EXISTS tb_produtos (
  id serial primary key,
  codigo varchar UNIQUE, -- Adicionando a restrição UNIQUE
  descricao varchar(100),
  preco numeric(10, 2),
  quantidade_estoque int,
  ncm int
);