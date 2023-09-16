package org.example.service;

import org.example.model.Cliente;
import org.example.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private ClienteRepository repository;
    @Autowired
    public ClienteService(ClienteRepository repository){
        this.repository = repository;
    }
    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
//        Jeito errado, instanciar o objeto diretamente no método, um método que faz liganção com a db, pode sobrecarregar
//        o sistema
//        O Correto é injetar o repositório no construtor, em versões atuais do Spring a annotatio Autowired nem é necessária
//        ClienteRepository clienteRepository = new ClienteRepository();
        this.repository.persistir(cliente);
    }
    public void validarCliente(Cliente cliente){}
}
