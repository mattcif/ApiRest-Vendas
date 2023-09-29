package io.github.mattcif.service;

import io.github.mattcif.domain.enums.StatusPedido;
import io.github.mattcif.rest.dto.PedidoDTO;
import io.github.mattcif.domain.entity.Pedido;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
