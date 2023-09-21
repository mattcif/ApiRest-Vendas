package org.example;

import org.example.domain.entity.Cliente;
import org.example.domain.entity.Pedido;
import org.example.domain.repository.Clientes;
import org.example.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class VendasApplication {
    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos){
        return args -> {
            System.out.println("Salvando clientes");
            Cliente ana = new Cliente("Ana");
            clientes.save(ana);
            clientes.save(new Cliente("Matheus"));
            clientes.save(new Cliente("Ari"));

            Pedido p = new Pedido();
            p.setCliente(ana);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

//            Cliente clienteFetchPedidos = clientes.findClienteFetchPedidos(ana.getId());
//            System.out.println(clienteFetchPedidos);
//            System.out.println(clienteFetchPedidos.getPedidos());
            System.out.println("Listando pedidos cliente");
            pedidos.findByCliente(ana).forEach(System.out::println);

            boolean existe = clientes.existsByNome("Ar");
            System.out.println("existe cliente com o nome 'Ari' " + existe );


        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
