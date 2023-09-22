package org.example.service;

import org.example.rest.dto.PedidoDTO;
import org.example.domain.entity.Pedido;
public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
