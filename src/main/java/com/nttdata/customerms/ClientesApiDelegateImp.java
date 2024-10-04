package com.nttdata.customerms;

import com.nttdata.customerms.api.ClientesApiDelegate;
import com.nttdata.customerms.business.ClienteServicio;
import com.nttdata.customerms.model.ClienteRequest;
import com.nttdata.customerms.model.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientesApiDelegateImp implements ClientesApiDelegate {
    @Autowired
    private ClienteServicio clienteServicio;

    @Override
    public ResponseEntity<List<ClienteResponse>> listarClientes(){
        return ResponseEntity.ok(clienteServicio.listarClientes());
    }

    @Override
    public ResponseEntity<ClienteResponse> consultarCliente(Integer id){
        return ResponseEntity.ok(clienteServicio.buscarClientePorId(id));
    }

    @Override
    public ResponseEntity<ClienteResponse> agregarCliente(ClienteRequest clienteRequest){
        return ResponseEntity.ok(clienteServicio.guardarCliente(clienteRequest));
    }

    @Override
    public ResponseEntity<ClienteResponse> actualizarCliente(Integer id, ClienteRequest clienteRequest) {
        return ResponseEntity.ok(clienteServicio.modificarCliente(id, clienteRequest));
    }

    @Override
    public ResponseEntity<Void> eliminarClientePorId(Integer id) {
        this.clienteServicio.eliminarClientePorId(id);
        return ResponseEntity.noContent().build();
    }
}
