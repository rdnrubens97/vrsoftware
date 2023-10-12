package com.vrs.projetovrs.service;

import com.vrs.projetovrs.dao.ServicoDao;
import com.vrs.projetovrs.exception.ValidationException;
import com.vrs.projetovrs.model.Servico;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ServicoServiceTest {
    private ServicoDao servicoDao;
    private ServicoService servicoService;

    @BeforeEach
    public void setUp() {
        servicoDao = Mockito.mock(ServicoDao.class);
        servicoService = new ServicoService(servicoDao);
    }


    // Este método de teste verifica se um Serviço pode ser salvo corretamente.
    @Test
    public void deveSalvarServicoCorretamente() throws SQLException {
        // Cria um objeto Servico com valores válidos
        Servico servico = new Servico();
        servico.setIdVenda(1);
        servico.setDescricao("descrição válida");
        servico.setPreco(BigDecimal.valueOf(100));

        // Configura um ArgumentCaptor para capturar o serviço passado para o salvarServico
        ArgumentCaptor<Servico> servicoCaptor = ArgumentCaptor.forClass(Servico.class);

        // Configura o mock para capturar o argumento e definir um ID fictício
        Mockito.doAnswer(invocation -> {
            Servico servicoSalvo = servicoCaptor.getValue();
            servicoSalvo.setId(123); // ID fictício
            return null; // Não há retorno, pois o método é void
        }).when(servicoDao).salvarServico(servicoCaptor.capture());

        // Verifica se a chamada para salvarServico não lança exceção
        Assertions.assertDoesNotThrow(() -> servicoService.salvarServico(servico));
        // Verifica se o ID do serviço foi definido após a chamada
        Assertions.assertNotNull(servico.getId());
        // Verifica se o método salvarServico foi chamado exatamente uma vez
        Mockito.verify(servicoDao, Mockito.times(1)).salvarServico(servico);
    }

    // Este método de teste verifica se o serviço rejeita parâmetros nulos.
    @ParameterizedTest
    @MethodSource(value = "dataProvider")
    public void deveRejeitarServicoComAlgumParametroNulo(Integer idVenda, String descricao, BigDecimal preco, String mensagem) {
        // Cria um objeto Servico com os valores fornecidos como argumentos
        Servico servico = new Servico();
        servico.setId(1);
        servico.setIdVenda(idVenda);
        servico.setDescricao(descricao);
        servico.setPreco(preco);

        // Verifica se a chamada para salvarServico lança uma exceção ValidationException
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
                servicoService.salvarServico(servico)
        );

        // Verifica se a mensagem de exceção corresponde à mensagem esperada
        Assertions.assertEquals(mensagem, exception.getMessage());
    }

    @Test
    public void deveListarServicosDeUmaVendaCorretamente() throws SQLException {
        // ID de venda de teste
        Integer idVenda = 1;
        // Crie uma lista de serviços de exemplo que você espera que o DAO retorne
        List<Servico> servicosDeExemplo = new ArrayList<>();

        Servico servico = new Servico();
        servico.setId(1);
        servico.setIdVenda(idVenda);
        servico.setDescricao("servico teste");
        servico.setPreco(BigDecimal.valueOf(80));

        servicosDeExemplo.add(servico);

        // Configure o mock do servicoDao para retornar a lista de serviços de exemplo
        Mockito.when(servicoDao.listarServicosDeUmaVenda(idVenda)).thenReturn(servicosDeExemplo);

        // Chame o método listarServicosDeUmaVenda
        List<Servico> servicos = servicoService.listarServicosDeUmaVenda(idVenda);
        List<Servico> servicosDeOutraVenda = servicoService.listarServicosDeUmaVenda(2);

        // Verifique se o resultado é igual à lista de serviços de exemplo
        Assertions.assertEquals(servicosDeExemplo, servicos);
        Assertions.assertTrue(servicosDeExemplo.equals(servicos));
        Assertions.assertFalse(servicosDeExemplo.equals(servicosDeOutraVenda));
    }

// Este método fornece dados de teste para o método deveRejeitarServicoComAlgumParametroNulo
    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(null, "descrição", BigDecimal.ONE, "Campos inválidos"),
                Arguments.of(1, null, BigDecimal.ONE, "Campos inválidos"),
                Arguments.of(1, "descrição", null, "Campos inválidos")
        );
    }

}
