package org.example.repository;

import org.example.model.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepository {
    public void persistir(Cliente cliente) {
        // acessa a base de dados e salva o cliente
    }
}
