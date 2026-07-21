package com.escola.querypractice.verificacao;

import com.escola.querypractice.model.StatusPedido;
import com.escola.querypractice.repository.ClienteRepository;
import com.escola.querypractice.repository.PedidoRepository;
import com.escola.querypractice.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Monta a lista dos 20 exercicios e avalia cada um.
 * (Parte interna - o aluno nao mexe aqui.)
 */
@Service
public class Verificador {

    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final Gabarito gabarito;

    public Verificador(ProdutoRepository produtoRepository,
                       ClienteRepository clienteRepository,
                       PedidoRepository pedidoRepository,
                       Gabarito gabarito) {
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.gabarito = gabarito;
    }

    @Transactional(readOnly = true)
    public List<ResultadoExercicio> avaliarTodos() {
        List<ResultadoExercicio> resultados = new ArrayList<>();
        for (Exercicio ex : construir()) {
            resultados.add(ex.avaliar());
        }
        return resultados;
    }

    @Transactional(readOnly = true)
    public ResultadoExercicio avaliar(int numero) {
        for (Exercicio ex : construir()) {
            if (ex.numero == numero) {
                return ex.avaliar();
            }
        }
        return null;
    }

    // ----------------------------------------------------------------

    private List<Exercicio> construir() {
        List<Exercicio> lista = new ArrayList<>();

        // ===================== FACIL =====================

        lista.add(novo(1, Dificuldade.FACIL,
                "Buscar produtos por nome exato",
                "Retorne todos os produtos cujo nome seja exatamente igual ao informado.",
                "Query Method", "List<Produto> findByNome(String nome)",
                "Comece pelo prefixo findBy + o nome da propriedade.",
                ProdutoRepository.class, produtoRepository,
                "findByNome", new Class<?>[]{String.class}, new Object[]{"Mouse Logitech"},
                ModoComparacao.LISTA,
                () -> gabarito.e1_produtosPorNome("Mouse Logitech")));

        lista.add(novo(2, Dificuldade.FACIL,
                "Produtos com preco acima de um valor",
                "Retorne os produtos com preco MAIOR que R$ 300.",
                "Query Method", "List<Produto> findByPrecoGreaterThan(BigDecimal preco)",
                "O sufixo GreaterThan gera o operador > .",
                ProdutoRepository.class, produtoRepository,
                "findByPrecoGreaterThan", new Class<?>[]{BigDecimal.class}, new Object[]{new BigDecimal("300")},
                ModoComparacao.LISTA,
                () -> gabarito.e2_precoMaiorQue(new BigDecimal("300"))));

        lista.add(novo(3, Dificuldade.FACIL,
                "Produtos ativos",
                "Retorne apenas os produtos com o campo ativo = true.",
                "Query Method", "List<Produto> findByAtivoTrue()",
                "Para booleanos existe o sufixo True (e False).",
                ProdutoRepository.class, produtoRepository,
                "findByAtivoTrue", new Class<?>[]{}, new Object[]{},
                ModoComparacao.LISTA,
                () -> gabarito.e3_ativos()));

        lista.add(novo(4, Dificuldade.FACIL,
                "Clientes por estado",
                "Retorne todos os clientes de um determinado estado (UF).",
                "Query Method", "List<Cliente> findByEstado(String estado)",
                "Teste com o estado \"SP\".",
                ClienteRepository.class, clienteRepository,
                "findByEstado", new Class<?>[]{String.class}, new Object[]{"SP"},
                ModoComparacao.LISTA,
                () -> gabarito.e4_clientesPorEstado("SP")));

        lista.add(novo(5, Dificuldade.FACIL,
                "Produtos de uma categoria (propriedade aninhada)",
                "Retorne os produtos de uma categoria, filtrando pelo NOME da categoria.",
                "Query Method", "List<Produto> findByCategoriaNome(String nome)",
                "Voce pode navegar por relacionamentos: CategoriaNome = categoria.nome.",
                ProdutoRepository.class, produtoRepository,
                "findByCategoriaNome", new Class<?>[]{String.class}, new Object[]{"Livros"},
                ModoComparacao.LISTA,
                () -> gabarito.e5_produtosPorCategoria("Livros")));

        lista.add(novo(6, Dificuldade.FACIL,
                "Busca por parte do nome (ignorando maiusculas)",
                "Retorne produtos cujo nome CONTENHA o termo, sem diferenciar maiuscula/minuscula.",
                "Query Method", "List<Produto> findByNomeContainingIgnoreCase(String termo)",
                "Combine Containing + IgnoreCase. Teste com \"livro\".",
                ProdutoRepository.class, produtoRepository,
                "findByNomeContainingIgnoreCase", new Class<?>[]{String.class}, new Object[]{"livro"},
                ModoComparacao.LISTA,
                () -> gabarito.e6_nomeContem("livro")));

        lista.add(novo(7, Dificuldade.FACIL,
                "Pedidos por status",
                "Retorne todos os pedidos com um determinado status (enum).",
                "Query Method", "List<Pedido> findByStatus(StatusPedido status)",
                "O parametro e do tipo enum StatusPedido. Teste com ENTREGUE.",
                PedidoRepository.class, pedidoRepository,
                "findByStatus", new Class<?>[]{StatusPedido.class}, new Object[]{StatusPedido.ENTREGUE},
                ModoComparacao.LISTA,
                () -> gabarito.e7_pedidosPorStatus(StatusPedido.ENTREGUE)));

        lista.add(novo(8, Dificuldade.FACIL,
                "Contar produtos de uma categoria",
                "Retorne a QUANTIDADE de produtos de uma categoria (pelo nome dela).",
                "Query Method", "long countByCategoriaNome(String nome)",
                "O prefixo countBy devolve um numero (long). Teste com \"Eletronicos\".",
                ProdutoRepository.class, produtoRepository,
                "countByCategoriaNome", new Class<?>[]{String.class}, new Object[]{"Eletronicos"},
                ModoComparacao.ESCALAR,
                () -> gabarito.e8_contarPorCategoria("Eletronicos")));

        // ===================== MEDIO =====================

        lista.add(novo(9, Dificuldade.MEDIO,
                "Faixa de preco ordenada",
                "Retorne produtos com preco ENTRE 100 e 400, ordenados pelo preco (crescente).",
                "Query Method", "List<Produto> findByPrecoBetweenOrderByPrecoAsc(BigDecimal min, BigDecimal max)",
                "Between usa dois parametros; OrderBy...Asc ordena.",
                ProdutoRepository.class, produtoRepository,
                "findByPrecoBetweenOrderByPrecoAsc",
                new Class<?>[]{BigDecimal.class, BigDecimal.class},
                new Object[]{new BigDecimal("100"), new BigDecimal("400")},
                ModoComparacao.LISTA_ORDENADA,
                () -> gabarito.e9_precoEntreOrdenado(new BigDecimal("100"), new BigDecimal("400"))));

        lista.add(novo(10, Dificuldade.MEDIO,
                "Clientes nascidos antes de uma data",
                "Retorne os clientes com data de nascimento anterior a 01/01/1990.",
                "Query Method", "List<Cliente> findByDataNascimentoBefore(LocalDate data)",
                "O sufixo Before compara datas. Parametro do tipo LocalDate.",
                ClienteRepository.class, clienteRepository,
                "findByDataNascimentoBefore", new Class<?>[]{LocalDate.class},
                new Object[]{LocalDate.of(1990, 1, 1)},
                ModoComparacao.LISTA,
                () -> gabarito.e10_nascidosAntes(LocalDate.of(1990, 1, 1))));

        lista.add(novo(11, Dificuldade.MEDIO,
                "Estoque baixo e ativo (dois filtros)",
                "Retorne produtos com estoque MENOR que 10 E que estejam ativos.",
                "Query Method", "List<Produto> findByEstoqueLessThanAndAtivoTrue(Integer estoque)",
                "Combine LessThan + And + AtivoTrue.",
                ProdutoRepository.class, produtoRepository,
                "findByEstoqueLessThanAndAtivoTrue", new Class<?>[]{Integer.class}, new Object[]{10},
                ModoComparacao.LISTA,
                () -> gabarito.e11_estoqueBaixoEAtivo(10)));

        lista.add(novo(12, Dificuldade.MEDIO,
                "Top 3 produtos mais caros",
                "Retorne os 3 produtos mais caros, do mais caro para o mais barato.",
                "Query Method", "List<Produto> findTop3ByOrderByPrecoDesc()",
                "Use Top3 logo apos o find. A ordem importa nesta questao.",
                ProdutoRepository.class, produtoRepository,
                "findTop3ByOrderByPrecoDesc", new Class<?>[]{}, new Object[]{},
                ModoComparacao.LISTA_ORDENADA,
                () -> gabarito.e12_top3MaisCaros()));

        lista.add(novo(13, Dificuldade.MEDIO,
                "@Query: faixa de preco com parametros nomeados",
                "Usando @Query (JPQL), retorne produtos com preco entre :min e :max.",
                "@Query", "List<Produto> buscarPorFaixaDePreco(BigDecimal min, BigDecimal max)",
                "Ex: @Query(\"SELECT p FROM Produto p WHERE p.preco BETWEEN :min AND :max\") e use @Param.",
                ProdutoRepository.class, produtoRepository,
                "buscarPorFaixaDePreco",
                new Class<?>[]{BigDecimal.class, BigDecimal.class},
                new Object[]{new BigDecimal("50"), new BigDecimal("150")},
                ModoComparacao.LISTA,
                () -> gabarito.e13_faixaDePreco(new BigDecimal("50"), new BigDecimal("150"))));

        lista.add(novo(14, Dificuldade.MEDIO,
                "@Query: cidade OU estado",
                "Usando @Query, retorne clientes de uma cidade OU de um estado.",
                "@Query", "List<Cliente> buscarPorCidadeOuEstado(String cidade, String estado)",
                "No JPQL use OR. Teste com cidade \"Curitiba\" ou estado \"RJ\".",
                ClienteRepository.class, clienteRepository,
                "buscarPorCidadeOuEstado",
                new Class<?>[]{String.class, String.class},
                new Object[]{"Curitiba", "RJ"},
                ModoComparacao.LISTA,
                () -> gabarito.e14_cidadeOuEstado("Curitiba", "RJ")));

        lista.add(novo(15, Dificuldade.MEDIO,
                "Pedidos de um cliente ordenados por data",
                "Retorne os pedidos de um cliente (por id), do mais recente para o mais antigo.",
                "Query Method", "List<Pedido> findByClienteIdOrderByDataPedidoDesc(Long clienteId)",
                "Navegue por ClienteId e ordene com OrderByDataPedidoDesc.",
                PedidoRepository.class, pedidoRepository,
                "findByClienteIdOrderByDataPedidoDesc", new Class<?>[]{Long.class}, new Object[]{1L},
                ModoComparacao.LISTA_ORDENADA,
                () -> gabarito.e15_pedidosDoClientePorData(1L)));

        // ===================== DIFICIL =====================

        lista.add(novo(16, Dificuldade.DIFICIL,
                "@Query com JOIN: produtos ja vendidos",
                "Retorne (sem repetir) todos os produtos que aparecem em ao menos um item de pedido.",
                "@Query", "List<Produto> buscarProdutosVendidos()",
                "Ex: SELECT DISTINCT i.produto FROM ItemPedido i. Nao esqueca do DISTINCT!",
                ProdutoRepository.class, produtoRepository,
                "buscarProdutosVendidos", new Class<?>[]{}, new Object[]{},
                ModoComparacao.LISTA,
                () -> gabarito.e16_produtosVendidos()));

        lista.add(novo(17, Dificuldade.DIFICIL,
                "@Query com subconsulta: produtos nunca vendidos",
                "Retorne os produtos que NUNCA apareceram em nenhum item de pedido.",
                "@Query", "List<Produto> buscarProdutosNuncaVendidos()",
                "Use NOT IN com uma subconsulta: WHERE p NOT IN (SELECT i.produto FROM ItemPedido i).",
                ProdutoRepository.class, produtoRepository,
                "buscarProdutosNuncaVendidos", new Class<?>[]{}, new Object[]{},
                ModoComparacao.LISTA,
                () -> gabarito.e17_produtosNuncaVendidos()));

        lista.add(novo(18, Dificuldade.DIFICIL,
                "@Query com agregacao: total gasto por cliente",
                "Retorne a SOMA do valorTotal de todos os pedidos de um cliente (por id).",
                "@Query", "BigDecimal calcularTotalGastoPorCliente(Long clienteId)",
                "Use SUM(p.valorTotal) e WHERE p.cliente.id = :clienteId. Teste com o cliente 1.",
                PedidoRepository.class, pedidoRepository,
                "calcularTotalGastoPorCliente", new Class<?>[]{Long.class}, new Object[]{1L},
                ModoComparacao.ESCALAR,
                () -> gabarito.e18_totalGastoPorCliente(1L)));

        lista.add(novo(19, Dificuldade.DIFICIL,
                "@Query com GROUP BY + HAVING",
                "Retorne os clientes cujo total gasto (soma dos pedidos) seja MAIOR que R$ 1000.",
                "@Query", "List<Cliente> buscarClientesQueGastaramMaisQue(BigDecimal valor)",
                "Ex: SELECT c FROM Cliente c JOIN c.pedidos p GROUP BY c HAVING SUM(p.valorTotal) > :valor",
                ClienteRepository.class, clienteRepository,
                "buscarClientesQueGastaramMaisQue", new Class<?>[]{BigDecimal.class},
                new Object[]{new BigDecimal("1000")},
                ModoComparacao.LISTA,
                () -> gabarito.e19_gastaramMaisQue(new BigDecimal("1000"))));

        lista.add(novo(20, Dificuldade.DIFICIL,
                "@Query nativa: acima da media da categoria",
                "Retorne os produtos cujo preco seja MAIOR que a media de preco da SUA propria categoria.",
                "@Query nativa", "List<Produto> buscarProdutosAcimaDaMediaDaCategoria()",
                "Use nativeQuery = true e uma subconsulta correlacionada com AVG() e categoria_id.",
                ProdutoRepository.class, produtoRepository,
                "buscarProdutosAcimaDaMediaDaCategoria", new Class<?>[]{}, new Object[]{},
                ModoComparacao.LISTA,
                () -> gabarito.e20_acimaDaMediaDaCategoria()));

        return lista;
    }

    private Exercicio novo(int numero, Dificuldade dif, String titulo, String enunciado,
                           String tipo, String assinatura, String dica,
                           Class<?> repo, Object bean, String metodo,
                           Class<?>[] parametros, Object[] argumentos,
                           ModoComparacao modo, Supplier<Object> referencia) {
        Exercicio ex = new Exercicio();
        ex.numero = numero;
        ex.dificuldade = dif;
        ex.titulo = titulo;
        ex.enunciado = enunciado;
        ex.tipo = tipo;
        ex.assinatura = assinatura;
        ex.dica = dica;
        ex.repositorio = repo;
        ex.repositorioBean = bean;
        ex.metodo = metodo;
        ex.parametros = parametros;
        ex.argumentos = argumentos;
        ex.modo = modo;
        ex.referencia = referencia;
        return ex;
    }
}
