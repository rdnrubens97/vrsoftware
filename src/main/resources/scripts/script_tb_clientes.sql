-- Cria a tabela CLIENTES se não existir
CREATE TABLE IF NOT EXISTS tb_clientes (
  id serial primary key,
  nome varchar(100)
);