package org.example.service.impl;

import org.example.domain.repository.Pedidos;
import org.example.service.PedidoService;
import org.springframework.stereotype.Service;


@Service
public class PedidoServiceimpl implements PedidoService {
    private Pedidos repository;

    public PedidoServiceimpl(Pedidos repository) {
        this.repository = repository;
    }
}
