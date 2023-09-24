package org.example.service;

import org.example.domain.enums.StatusPedido;
import org.example.rest.dto.PedidoDTO;
import org.example.domain.entity.Pedido;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
