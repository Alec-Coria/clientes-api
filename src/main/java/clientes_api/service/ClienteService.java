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

        return this.toResponse(clienteGuardado);
    }

    public List<ClienteResponse> listarClientes() {
        return clienteRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ClienteResponse buscarPorId(Long id) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente no encontrado"
                ));

        return this.toResponse(clienteExistente);
    }

    public ClienteResponse actualizarCliente(Long id, ClienteRequest datosNuevos) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente no encontrado"
                ));
        clienteExistente.setName(datosNuevos.name());
        clienteExistente.setEmail(datosNuevos.email());
        clienteExistente.setTelefono(datosNuevos.telefono());

        return this.toResponse(clienteRepository.save(clienteExistente));
    }

    public void eliminarCliente(Long id){
        clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente no encontrado"
                ));

        clienteRepository.deleteById(id);
    }

    private ClienteResponse toResponse (Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getName(),
                cliente.getEmail(),
                cliente.getTelefono()
        );
    }

}
