package io.github.mattcif.service.impl;

import io.github.mattcif.service.PedidoService;
import lombok.RequiredArgsConstructor;
import io.github.mattcif.domain.entity.Cliente;
import io.github.mattcif.domain.entity.ItemPedido;
import io.github.mattcif.domain.entity.Pedido;
import io.github.mattcif.domain.entity.Produto;
import io.github.mattcif.domain.enums.StatusPedido;
import io.github.mattcif.domain.repository.Clientes;
import io.github.mattcif.domain.repository.ItemsPedido;
import io.github.mattcif.domain.repository.Pedidos;
import io.github.mattcif.domain.repository.Produtos;
import io.github.mattcif.exception.PedidoNaoEncontradoException;
import io.github.mattcif.exception.RegraNegocioException;
import io.github.mattcif.rest.dto.ItemPedidoDTO;
import io.github.mattcif.rest.dto.PedidoDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PedidoServiceimpl implements PedidoService {


    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;


    @Override
    @Transactional // garante que o salvamento só ocorrera se todas as operações forem executadas com sucesso
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.
                findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));


        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemPedidos = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);

        return pedido;
    }
    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItems(Pedido pedido,List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar o pedido sem itens");
        }
        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));



                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }


}
