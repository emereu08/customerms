package com.nttdata.customerms.business.mapper;

import com.nttdata.customerms.model.ClienteRequest;
import com.nttdata.customerms.model.ClienteResponse;
import com.nttdata.customerms.model.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {


    public Cliente getClienteEntity(ClienteRequest request){
        Cliente entity = new Cliente();
        entity.setNombre(request.getNombre());
        entity.setApellido(request.getApellido());
        entity.setDni(request.getDni());
        entity.setEmail(request.getEmail());
        return entity;
    }

    public ClienteResponse getClienteResponse(Cliente cliente){
        ClienteResponse response = new ClienteResponse();
        response.setId(cliente.getId());
        response.setNombre(cliente.getNombre());
        response.setApellido(cliente.getApellido());
        response.setDni(cliente.getDni());
        response.setEmail(cliente.getEmail());
        return response;
    }

    public Cliente getClienteOfResponse(ClienteResponse response){
        Cliente entity = new Cliente();
        entity.setId(response.getId());
        entity.setNombre(response.getNombre());
        entity.setApellido(response.getApellido());
        entity.setDni(response.getDni());
        entity.setEmail(response.getEmail());
        return entity;
    }

}
