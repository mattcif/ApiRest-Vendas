package org.example.rest.controller;

import org.example.domain.entity.Pedido;
import org.example.rest.dto.PedidoDTO;
import org.example.service.PedidoService;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/pedidos")
public class PedidoController {
    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();

    }
}
