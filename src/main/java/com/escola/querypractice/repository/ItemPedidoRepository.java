package com.escola.querypractice.repository;

import com.escola.querypractice.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de itens de pedido.
 * Nao ha exercicios aqui, mas ele existe para dar dados aos exercicios
 * de JOIN/subquery (produtos vendidos, nunca vendidos, etc).
 * Voce nao precisa mexer neste arquivo.
 */
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
