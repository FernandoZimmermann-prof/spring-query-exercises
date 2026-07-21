package com.escola.querypractice.verificacao;

import com.escola.querypractice.model.*;
import com.escola.querypractice.repository.ClienteRepository;
import com.escola.querypractice.repository.ItemPedidoRepository;
import com.escola.querypractice.repository.PedidoRepository;
import com.escola.querypractice.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Gabarito: calcula a RESPOSTA CORRETA de cada exercicio usando apenas
 * Java puro sobre findAll() (nunca depende dos metodos que o aluno escreve).
 * O verificador compara a resposta do aluno com o que sai daqui.
 *
 * O aluno nao precisa (e nao deve) olhar este arquivo. :)
 */
@Service
public class Gabarito {

    private final ProdutoRepository produtos;
    private final ClienteRepository clientes;
    private final PedidoRepository pedidos;
    private final ItemPedidoRepository itens;

    public Gabarito(ProdutoRepository produtos, ClienteRepository clientes,
                    PedidoRepository pedidos, ItemPedidoRepository itens) {
        this.produtos = produtos;
        this.clientes = clientes;
        this.pedidos = pedidos;
        this.itens = itens;
    }

    // ---------- FACIL ----------

    List<Produto> e1_produtosPorNome(String nome) {
        return produtos.findAll().stream()
                .filter(p -> Objects.equals(p.getNome(), nome))
                .collect(Collectors.toList());
    }

    List<Produto> e2_precoMaiorQue(BigDecimal preco) {
        return produtos.findAll().stream()
                .filter(p -> p.getPreco().compareTo(preco) > 0)
                .collect(Collectors.toList());
    }

    List<Produto> e3_ativos() {
        return produtos.findAll().stream()
                .filter(p -> Boolean.TRUE.equals(p.getAtivo()))
                .collect(Collectors.toList());
    }

    List<Cliente> e4_clientesPorEstado(String estado) {
        return clientes.findAll().stream()
                .filter(c -> Objects.equals(c.getEstado(), estado))
                .collect(Collectors.toList());
    }

    List<Produto> e5_produtosPorCategoria(String nomeCategoria) {
        return produtos.findAll().stream()
                .filter(p -> p.getCategoria() != null
                        && Objects.equals(p.getCategoria().getNome(), nomeCategoria))
                .collect(Collectors.toList());
    }

    List<Produto> e6_nomeContem(String termo) {
        String alvo = termo.toLowerCase();
        return produtos.findAll().stream()
                .filter(p -> p.getNome().toLowerCase().contains(alvo))
                .collect(Collectors.toList());
    }

    List<Pedido> e7_pedidosPorStatus(StatusPedido status) {
        return pedidos.findAll().stream()
                .filter(p -> p.getStatus() == status)
                .collect(Collectors.toList());
    }

    long e8_contarPorCategoria(String nomeCategoria) {
        return produtos.findAll().stream()
                .filter(p -> p.getCategoria() != null
                        && Objects.equals(p.getCategoria().getNome(), nomeCategoria))
                .count();
    }

    // ---------- MEDIO ----------

    List<Produto> e9_precoEntreOrdenado(BigDecimal min, BigDecimal max) {
        return produtos.findAll().stream()
                .filter(p -> p.getPreco().compareTo(min) >= 0 && p.getPreco().compareTo(max) <= 0)
                .sorted(Comparator.comparing(Produto::getPreco))
                .collect(Collectors.toList());
    }

    List<Cliente> e10_nascidosAntes(LocalDate data) {
        return clientes.findAll().stream()
                .filter(c -> c.getDataNascimento().isBefore(data))
                .collect(Collectors.toList());
    }

    List<Produto> e11_estoqueBaixoEAtivo(Integer limite) {
        return produtos.findAll().stream()
                .filter(p -> p.getEstoque() < limite && Boolean.TRUE.equals(p.getAtivo()))
                .collect(Collectors.toList());
    }

    List<Produto> e12_top3MaisCaros() {
        return produtos.findAll().stream()
                .sorted(Comparator.comparing(Produto::getPreco).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    List<Produto> e13_faixaDePreco(BigDecimal min, BigDecimal max) {
        return produtos.findAll().stream()
                .filter(p -> p.getPreco().compareTo(min) >= 0 && p.getPreco().compareTo(max) <= 0)
                .collect(Collectors.toList());
    }

    List<Cliente> e14_cidadeOuEstado(String cidade, String estado) {
        return clientes.findAll().stream()
                .filter(c -> Objects.equals(c.getCidade(), cidade) || Objects.equals(c.getEstado(), estado))
                .collect(Collectors.toList());
    }

    List<Pedido> e15_pedidosDoClientePorData(Long clienteId) {
        return pedidos.findAll().stream()
                .filter(p -> p.getCliente() != null && Objects.equals(p.getCliente().getId(), clienteId))
                .sorted(Comparator.comparing(Pedido::getDataPedido).reversed())
                .collect(Collectors.toList());
    }

    // ---------- DIFICIL ----------

    List<Produto> e16_produtosVendidos() {
        Set<Long> vendidos = itens.findAll().stream()
                .map(i -> i.getProduto().getId())
                .collect(Collectors.toSet());
        return produtos.findAll().stream()
                .filter(p -> vendidos.contains(p.getId()))
                .collect(Collectors.toList());
    }

    List<Produto> e17_produtosNuncaVendidos() {
        Set<Long> vendidos = itens.findAll().stream()
                .map(i -> i.getProduto().getId())
                .collect(Collectors.toSet());
        return produtos.findAll().stream()
                .filter(p -> !vendidos.contains(p.getId()))
                .collect(Collectors.toList());
    }

    BigDecimal e18_totalGastoPorCliente(Long clienteId) {
        return pedidos.findAll().stream()
                .filter(p -> p.getCliente() != null && Objects.equals(p.getCliente().getId(), clienteId))
                .map(Pedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    List<Cliente> e19_gastaramMaisQue(BigDecimal valor) {
        Map<Long, BigDecimal> totalPorCliente = new HashMap<>();
        for (Pedido p : pedidos.findAll()) {
            if (p.getCliente() == null) {
                continue;
            }
            totalPorCliente.merge(p.getCliente().getId(), p.getValorTotal(), BigDecimal::add);
        }
        return clientes.findAll().stream()
                .filter(c -> totalPorCliente.getOrDefault(c.getId(), BigDecimal.ZERO).compareTo(valor) > 0)
                .collect(Collectors.toList());
    }

    List<Produto> e20_acimaDaMediaDaCategoria() {
        Map<Long, List<Produto>> porCategoria = produtos.findAll().stream()
                .filter(p -> p.getCategoria() != null)
                .collect(Collectors.groupingBy(p -> p.getCategoria().getId()));

        Map<Long, BigDecimal> mediaPorCategoria = new HashMap<>();
        for (Map.Entry<Long, List<Produto>> e : porCategoria.entrySet()) {
            BigDecimal soma = e.getValue().stream()
                    .map(Produto::getPreco)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal media = soma.divide(BigDecimal.valueOf(e.getValue().size()), 10, RoundingMode.HALF_UP);
            mediaPorCategoria.put(e.getKey(), media);
        }

        return produtos.findAll().stream()
                .filter(p -> p.getCategoria() != null)
                .filter(p -> p.getPreco().compareTo(mediaPorCategoria.get(p.getCategoria().getId())) > 0)
                .collect(Collectors.toList());
    }
}
