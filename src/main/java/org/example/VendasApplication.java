package org.example;

import org.example.domain.entity.Cliente;
import org.example.domain.entity.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {
    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            clientes.salvar(new Cliente("Matheus"));
            clientes.salvar(new Cliente("Ari"));

            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
