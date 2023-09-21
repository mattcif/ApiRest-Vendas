package org.example;

import org.example.domain.entity.Cliente;
import org.example.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {
    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("Salvando clientes");
            clientes.save(new Cliente("Matheus"));
            clientes.save(new Cliente("Ari"));

            boolean existe = clientes.existsByNome("Ar");
            System.out.println("existe cliente com o nome 'Ari' " + existe );


        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
