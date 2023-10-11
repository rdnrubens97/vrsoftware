package com.vrs.projetovrs.service;

import com.vrs.projetovrs.builder.ServicoBuilder;
import com.vrs.projetovrs.dao.ServicoDao;
import com.vrs.projetovrs.exception.ValidationException;
import com.vrs.projetovrs.model.Servico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.stream.Stream;

public class ServicoServiceTest {
    private ServicoDao servicoDao;
    private ServicoService servicoService;


    //Ainda implementando os primeiros métodos de testes e fazendo os devidos ajustes

    @BeforeEach
    public void setUp() {
        servicoDao = Mockito.mock(ServicoDao.class);
        servicoService = new ServicoService(servicoDao);
    }

    @Test
    public void testSalvarServico() throws SQLException {
        Servico servico = ServicoBuilder.umServico().agora();
        servicoService.salvarServico(servico);
        Mockito.verify(servicoDao).salvarServico(servico);

    }

    @ParameterizedTest
    @MethodSource(value = "dataProvider")
    public void deveRejeitarServicoComAlgumParametroNulo(Integer id, Integer idVenda, String descricao, BigDecimal preco, String mensagem) {
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
                ServicoBuilder.umServico().
                        comId(id).
                        comIdVenda(idVenda).
                        comDescricao(descricao).
                        comPreco(preco).
                        agora());
        Assertions.assertEquals(mensagem, exception.getMessage());
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(1, 1, "descrição", BigDecimal.ONE, "Campos inválidos"),
                Arguments.of(1, 1, "descrição", BigDecimal.ONE, "Campos inválidos")
        );
    }

}
