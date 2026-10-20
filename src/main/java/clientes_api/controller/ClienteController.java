package clientes_api.controller;

import clientes_api.dto.ClienteRequest;
import clientes_api.dto.ClienteResponse;
import clientes_api.entity.Cliente;
import clientes_api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> crearCliente(@Valid @RequestBody ClienteRequest request){
        ClienteResponse response = clienteService.guardarCliente(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public List<Cliente> getClientes(){
        return clienteService.listarClientes();
    }

    @GetMapping(path = "/{id}")
    public Cliente getClienteById(@PathVariable Long id){
        return clienteService.buscarPorId(id);
    }

    @PutMapping(path = "/{id}")
    public Cliente updateCliente(
            @PathVariable Long id,
            @RequestBody Cliente cliente){
        return clienteService.actualizarCliente(id, cliente);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

}
