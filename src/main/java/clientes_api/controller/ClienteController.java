package clientes_api.controller;

import clientes_api.entity.Cliente;
import clientes_api.service.ClienteService;
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
    public Cliente crearCliente(@RequestBody Cliente cliente){
        return this.clienteService.guardarCliente(cliente);
    }

    @GetMapping
    public List<Cliente> getClientes(){
        return this.clienteService.listarClientes();
    }

    @GetMapping(path = "/{id}")
    public Optional<Cliente> getClienteById(@PathVariable Long id){
        return clienteService.buscarPorId(id);
    }

}
