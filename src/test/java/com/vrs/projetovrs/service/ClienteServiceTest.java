package com.vrs.projetovrs.service;

import com.vrs.projetovrs.dao.ClienteDao;
import com.vrs.projetovrs.exception.ValidationException;
import com.vrs.projetovrs.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteServiceTest {
    private ClienteDao clienteDao;
    private ClienteService clienteService;

    @BeforeEach
    private void setUp() {
        clienteDao = Mockito.mock(ClienteDao.class);
        clienteService = new ClienteService(clienteDao);
    }

    @Test
    public void deveRegistrarUmClienteCorretamente() throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNome("Nome Teste");
        cliente.setCelular("11111111111");

        // Configura um ArgumentCaptor para capturar o cliente passado para o cadastrarCliente
        ArgumentCaptor<Cliente> clienteCaptor = ArgumentCaptor.forClass(Cliente.class);
        // Configura o mock para capturar o argumento e definir um ID ficticio
        Mockito.doAnswer(invocation -> {
            Cliente clienteCadastrado = clienteCaptor.getValue();
            clienteCadastrado.setId(123);
            return null;
        }).when(clienteDao).cadastrarCliente(clienteCaptor.capture());
        Assertions.assertDoesNotThrow(() -> clienteService.cadastrarCliente(cliente));
        Assertions.assertEquals(123, cliente.getId());
    }

    @Test
    public void deveRejeitarClienteSemContato() throws SQLException {
        Cliente clienteSemContato = new Cliente();
        clienteSemContato.setNome("Nome Teste");

        // Configura um ArgumentCaptor para capturar o cliente passado para o cadastrarCliente
        ArgumentCaptor<Cliente> clienteCaptor = ArgumentCaptor.forClass(Cliente.class);
        // Configura o mock para capturar o argumento e definir um ID ficticio
        Mockito.doAnswer(invocation -> {
            Cliente clienteCadastrado = clienteCaptor.getValue();
            clienteCadastrado.setId(123);
            return null;
        }).when(clienteDao).cadastrarCliente(clienteCaptor.capture());
        Assertions.assertThrows(ValidationException.class, () -> clienteService.cadastrarCliente(clienteSemContato));
        Assertions.assertNull(clienteSemContato.getId());
    }

    @Test
    public void deveRejeitarClienteSemNome() throws SQLException {
        Cliente clienteSemNome = new Cliente();
        clienteSemNome.setCelular("11111111111");

        // Configura um ArgumentCaptor para capturar o cliente passado para o cadastrarCliente
        ArgumentCaptor<Cliente> clienteCaptor = ArgumentCaptor.forClass(Cliente.class);
        // Configura o mock para capturar o argumento e definir um ID ficticio
        Mockito.doAnswer(invocation -> {
            Cliente clienteCadastrado = clienteCaptor.getValue();
            clienteCadastrado.setId(123);
            return null;
        }).when(clienteDao).cadastrarCliente(clienteCaptor.capture());
        Assertions.assertThrows(ValidationException.class, () -> clienteService.cadastrarCliente(clienteSemNome));
        Assertions.assertNull(clienteSemNome.getId());
    }

    @Test
    public void deveExcluirClienteCorretamente() throws SQLException {
        Integer idCliente = 123;
        ArgumentCaptor<Integer> integerIdCaptor = ArgumentCaptor.forClass(Integer.class);
        clienteService.excluirCliente(idCliente);
        Mockito.verify(clienteDao, Mockito.times(1)).excluirCliente(integerIdCaptor.capture());
        Assertions.assertEquals(idCliente, integerIdCaptor.getValue());
    }

    @Test
    public void deveEditarClienteCorretamente() throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(123);
        cliente.setNome("Nome Inicial");
        cliente.setCelular("11111111111");

        Cliente clienteEditado = new Cliente();
        clienteEditado.setId(123);
        clienteEditado.setNome("Novo Nome");
        clienteEditado.setCelular("11111111111");
        Mockito.when(clienteDao.buscarClientePorId("123")).thenReturn(clienteEditado);

        ArgumentCaptor<Cliente> clienteCaptor = ArgumentCaptor.forClass(Cliente.class);

        clienteService.editarCliente(cliente);
        Mockito.verify(clienteDao, Mockito.times(1)).editarCliente(clienteCaptor.capture());
        Assertions.assertEquals(cliente, clienteCaptor.getValue());

        cliente.setNome("Novo Nome");

        clienteService.editarCliente(cliente);
        Mockito.verify(clienteDao, Mockito.times(2)).editarCliente(clienteCaptor.capture());

        Cliente clienteRetornado = clienteService.buscarClientePorId("123");
        Assertions.assertEquals(clienteRetornado, clienteEditado);
    }

    @Test
    public void deveListarClientesCorretamente() throws SQLException {
        List<Cliente> clientesSimulados = new ArrayList<>();
        Cliente cliente1 = new Cliente();
        cliente1.setId(1);
        cliente1.setNome("Cliente 1");
        cliente1.setCelular("11111111111");
        clientesSimulados.add(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setId(2);
        cliente2.setNome("Cliente 2");
        cliente2.setCelular("22222222222");
        clientesSimulados.add(cliente2);

        Mockito.when(clienteDao.listarClientes()).thenReturn(clientesSimulados);
        List<Cliente> listaClientes = clienteService.listarClientes();
        Assertions.assertEquals(clientesSimulados, listaClientes);
    }


}
