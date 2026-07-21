package com.escola.querypractice.repository;

import com.escola.querypractice.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * =====================================================================
 *  AQUI E ONDE VOCE TRABALHA!  (Produtos)
 * =====================================================================
 *
 * Adicione os metodos dos exercicios abaixo, usando o nome e a
 * assinatura EXATOS indicados. O nome precisa bater certinho, senao
 * o verificador nao encontra o seu metodo.
 *
 * Depois de escrever, rode a aplicacao e abra http://localhost:8080
 * para ver se o exercicio ficou VERDE (passou) ou VERMELHO (falhou).
 *
 * Imports que voce provavelmente vai precisar:
 *   import java.util.List;
 *   import java.math.BigDecimal;
 *   import org.springframework.data.jpa.repository.Query;
 *   import org.springframework.data.repository.query.Param;
 *
 * ---------------------------------------------------------------------
 *  EXERCICIOS QUE USAM ESTE REPOSITORIO
 * ---------------------------------------------------------------------
 *  FACIL
 *   E1
 *   E2
 *   E3
 *   E5
 *   E6
 *   E8
 *
 *  MEDIO
 *   E9
 *   E11
 *   E12
 *   E13
 *
 *  DIFICIL
 *   E16
 *   E17
 *   E20
 * ---------------------------------------------------------------------
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // TODO: escreva aqui os metodos dos exercicios de produto.

}
