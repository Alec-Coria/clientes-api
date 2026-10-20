package clientes_api.service;

import clientes_api.dto.ClienteRequest;
import clientes_api.dto.ClienteResponse;
import clientes_api.entity.Cliente;
import clientes_api.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponse guardarCliente(ClienteRequest clienteRequest) {
        Cliente client = new Cliente();
        client.setName(clienteRequest.name());
        client.setEmail(clienteRequest.email());
        client.setTelefono(clienteRequest.telefono());

        Cliente clienteGuardado = clienteRepository.save(client);

        return new ClienteResponse(
                clienteGuardado.getId(),
                clienteGuardado.getName(),
                clienteGuardado.getEmail(),
                clienteGuardado.getTelefono()
        );
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente no encontrado"
                ));
    }

    public Cliente actualizarCliente(Long id, Cliente datosNuevos) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente no encontrado"
                ));
        clienteExistente.setName(datosNuevos.getName());
        clienteExistente.setEmail(datosNuevos.getEmail());
        clienteExistente.setTelefono(datosNuevos.getTelefono());

        return clienteRepository.save(clienteExistente);
    }

    public void eliminarCliente(Long id){
        clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente no encontrado"
                ));

        clienteRepository.deleteById(id);
    }

}
