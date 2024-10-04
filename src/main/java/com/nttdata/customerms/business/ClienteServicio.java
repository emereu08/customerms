package com.nttdata.customerms.business;

import com.nttdata.customerms.model.ClienteRequest;
import com.nttdata.customerms.model.ClienteResponse;

import java.util.List;

public interface ClienteServicio {
    public List<ClienteResponse> listarClientes();

    public ClienteResponse buscarClientePorId(Integer idCliente);

    public ClienteResponse guardarCliente(ClienteRequest request);

    public void eliminarClientePorId(Integer idCliente);

    public ClienteResponse modificarCliente(Integer idCliente, ClienteRequest request);
}
