-- Cria a tabela PRODUTOS se não existir
CREATE TABLE IF NOT EXISTS tb_produtos (
  id serial primary key,
  codigo varchar UNIQUE NOT NULL, -- Adicionando a restrição UNIQUE
  descricao varchar(100) NOT NULL,
  preco numeric(10, 2) NOT NULL,
  quantidade_estoque int NOT NULL,
  ncm int
);