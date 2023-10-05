-- Cria a tabela MAO_DE_OBRA se não existir
CREATE TABLE IF NOT EXISTS tb_maodeobra (
  id serial primary key,
  id_venda int,
  id_servico int,
  valor_total numeric(10, 2)

  CONSTRAINT fk_id_venda FOREIGN KEY (id_venda) REFERENCES tb_vendas(id),
  CONSTRAINT fk_id_servico FOREIGN KEY (id_servico) REFERENCES tb_servico(id)
);