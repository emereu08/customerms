package com.nttdata.customerms.business.impl;

import com.nttdata.customerms.business.ClienteServicio;
import com.nttdata.customerms.business.excepcion.RecursoNoEncontradoExcepcion;
import com.nttdata.customerms.business.mapper.ClienteMapper;
import com.nttdata.customerms.model.ClienteRequest;
import com.nttdata.customerms.model.ClienteResponse;
import com.nttdata.customerms.model.entity.Cliente;
import com.nttdata.customerms.repository.ClienteRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServicioImplTest {
    @Mock
    private ClienteRepositorio clienteRepositorio;

    @Mock
    private ClienteMapper clienteMapper;

    @Mock
    private RecursoNoEncontradoExcepcion recursoNoEncontradoExcepcion;

    @InjectMocks
    private ClienteServicioImpl clienteServicio;

    @Test
    @DisplayName("when get lista customers ok")
    void whenGetListCustomers(){
        List<ClienteResponse> simulado = new ArrayList<>();
        Cliente clienteSimulado = new Cliente(1, "Diana", "Chavez", "2550", "prueba@prueba.com");
        ClienteResponse clienteResponseSimulado = clienteMapper.getClienteResponse(clienteSimulado);
        simulado.add(clienteResponseSimulado);

        List<ClienteResponse> esperado = new ArrayList<>();
        Cliente clienteEsperado = new Cliente(1, "Diana", "Chavez", "2550", "prueba@prueba.com");
        ClienteResponse clienteResponseEsperado = clienteMapper.getClienteResponse(clienteEsperado);
        esperado.add(clienteResponseEsperado);

        when(clienteRepositorio.findAll().stream()
                .map(m -> clienteMapper.getClienteResponse(m))
                .collect(Collectors.toList())).thenReturn(simulado);

        final List<ClienteResponse> resultado = clienteServicio.listarClientes();

        assertEquals(esperado, resultado);
        assertNotNull(resultado);
    }

    @Test
    @DisplayName("When buscar cliente Por Id")
    void whenBuscarClientePorId(){
        Cliente clienteSimulado = new Cliente(1, "Diana", "Chavez", "2550", "prueba@prueba.com");
        ClienteResponse clienteResponseSimulado = clienteMapper.getClienteResponse(clienteSimulado);

        /*when(clienteServicio.buscarClientePorId(anyInt()))
                .thenReturn(clienteResponseSimulado)
                .thenThrow(new RecursoNoEncontradoExcepcion(anyString()));/*
         */
        when(clienteRepositorio.findById(anyInt())).thenReturn(Optional.of(clienteSimulado));
    }

    @Test
    void whenGuardarClienteOk() throws InstantiationException, IllegalAccessException {
        Cliente cliente = new Cliente();
        cliente.setDni("123456");
        cliente.setNombre("Enzo");
        cliente.setApellido("Mere");
        cliente.setEmail("emere@prueba.com");

        when(clienteRepositorio.save(clienteMapper.getClienteEntity(ClienteRequest.class.newInstance())))
                .thenReturn(cliente);

    }
}
