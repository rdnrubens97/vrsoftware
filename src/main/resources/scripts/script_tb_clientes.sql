-- Cria a tabela CLIENTES se não existir
CREATE TABLE IF NOT EXISTS tb_clientes (
  id serial primary key,
  nome varchar(100),
  documento varchar(100),
  email varchar(100),
  celular varchar(100),
  cep varchar(100),
  endereco varchar(100),
  numero varchar(100),
  complemento varchar(100),
  bairro varchar(100),
  cidade varchar(100),
  estado varchar(100)
);