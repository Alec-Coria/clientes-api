package clientes_api.dto;

public record ClienteResponse (
        Long id,
        String name,
        String email,
        String telefono
) { }
