package com.nttdata.customerms.business.impl;

import com.nttdata.customerms.business.ClienteServicio;
import com.nttdata.customerms.business.excepcion.ErrorInternoExcepcion;
import com.nttdata.customerms.business.excepcion.RecursoNoEncontradoExcepcion;
import com.nttdata.customerms.business.mapper.ClienteMapper;
import com.nttdata.customerms.business.util.Constantes;
import com.nttdata.customerms.model.ClienteRequest;
import com.nttdata.customerms.model.ClienteResponse;
import com.nttdata.customerms.model.entity.Cliente;
import com.nttdata.customerms.repository.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public List<ClienteResponse> listarClientes() {
        return clienteRepositorio.findAll().stream()
                .map(m -> clienteMapper.getClienteResponse(m))
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponse buscarClientePorId(Integer idCliente) {
        Cliente cliente = this.clienteRepositorio.findById(idCliente).orElse(null);
        if(cliente == null)
            throw new RecursoNoEncontradoExcepcion("No existe el id cuenta: "+ idCliente);
        return clienteMapper.getClienteResponse(cliente);
    }

    @Override
    public ClienteResponse guardarCliente(ClienteRequest request) {
        Cliente cliente =  clienteMapper.getClienteEntity(request);

        if(!Cliente.patternMatches(cliente.getEmail(), Constantes.REGEX_PATTERN_EMAIL))
            throw new ErrorInternoExcepcion("Error en el formato de email: "+ cliente.getEmail());

        return clienteMapper.getClienteResponse(clienteRepositorio.save(cliente));
    }

    @Override
    public void eliminarClientePorId(Integer idCliente) {
        ClienteResponse response = this.buscarClientePorId(idCliente);
        if(response == null)
            throw new RecursoNoEncontradoExcepcion("No existe el id cuenta: "+idCliente);
        this.clienteRepositorio.deleteById(idCliente);
    }

    @Override
    public ClienteResponse modificarCliente(Integer idCliente, ClienteRequest request) {
        Cliente cliente = clienteMapper.getClienteOfResponse(this.buscarClientePorId(idCliente));
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setDni(request.getDni());
        cliente.setEmail(request.getEmail());

        if(!Cliente.patternMatches(cliente.getEmail(), Constantes.REGEX_PATTERN_EMAIL))
            throw new ErrorInternoExcepcion("Error en el formato de email: "+ cliente.getEmail());

        return clienteMapper.getClienteResponse(clienteRepositorio.save(cliente));
    }

}
