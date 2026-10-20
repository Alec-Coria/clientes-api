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
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteService.guardarCliente(request));
    }

    @GetMapping
    public List<ClienteResponse> getClientes(){
        return clienteService.listarClientes();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClienteResponse> getClienteById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.buscarPorId(id));
    }

    @PutMapping(path = "/{id}")
    public ClienteResponse updateCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequest cliente){
        return clienteService.actualizarCliente(id, cliente);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

}
