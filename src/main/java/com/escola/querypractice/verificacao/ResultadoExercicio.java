package com.escola.querypractice.verificacao;

/**
 * Resultado da avaliacao de um exercicio, devolvido como JSON.
 *
 * status pode ser:
 *   PASSOU    -> seu metodo devolveu exatamente o esperado
 *   FALHOU    -> seu metodo rodou, mas o resultado esta diferente do gabarito
 *   PENDENTE  -> voce ainda nao criou o metodo no repositorio
 *   ERRO      -> o metodo existe mas quebrou ao executar (ex: @Query invalida)
 */
public record ResultadoExercicio(
        int numero,
        String dificuldade,
        String titulo,
        String tipo,
        String assinatura,
        String enunciado,
        String dica,
        String status,
        String esperado,
        String obtido,
        String mensagem
) {
}
