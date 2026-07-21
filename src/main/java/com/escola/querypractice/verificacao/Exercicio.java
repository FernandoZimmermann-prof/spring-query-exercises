package com.escola.querypractice.verificacao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * Definicao de um exercicio + logica de avaliacao.
 *
 * A avaliacao chama, por reflexao, o metodo que o aluno escreveu no
 * repositorio. Se o metodo ainda nao existe, o exercicio aparece como
 * PENDENTE (e a aplicacao continua funcionando normalmente).
 */
class Exercicio {

    int numero;
    Dificuldade dificuldade;
    String titulo;
    String enunciado;
    String tipo;         // "Query Method", "@Query" ou "@Query nativa"
    String assinatura;   // assinatura exata que o aluno deve criar
    String dica;

    Class<?> repositorio;     // ex: ProdutoRepository.class
    Object repositorioBean;   // instancia (proxy) do repositorio
    String metodo;            // nome do metodo a chamar
    Class<?>[] parametros;    // tipos dos parametros
    Object[] argumentos;      // valores usados no teste

    ModoComparacao modo;
    Supplier<Object> referencia; // calcula a resposta correta

    ResultadoExercicio avaliar() {
        Object esperado = referencia.get();
        String esperadoResumo = Comparadores.resumo(esperado);
        String obtidoResumo = "\u2014";
        String status;
        String mensagem = null;

        try {
            Method m = repositorio.getMethod(metodo, parametros);
            Object obtido = m.invoke(repositorioBean, argumentos);
            obtidoResumo = Comparadores.resumo(obtido);
            boolean ok = Comparadores.iguais(obtido, esperado, modo);
            if (ok) {
                status = "PASSOU";
            } else {
                status = "FALHOU";
                mensagem = "O resultado nao confere com o gabarito. Revise sua query.";
            }
        } catch (NoSuchMethodException e) {
            status = "PENDENTE";
            mensagem = "Metodo ainda nao implementado. Crie: " + assinatura;
        } catch (InvocationTargetException e) {
            status = "ERRO";
            Throwable causa = e.getCause() != null ? e.getCause() : e;
            mensagem = "A query quebrou ao executar: " + causa.getMessage();
        } catch (Exception e) {
            status = "ERRO";
            mensagem = e.getMessage();
        }

        return new ResultadoExercicio(
                numero,
                dificuldade.name(),
                titulo,
                tipo,
                assinatura,
                enunciado,
                dica,
                status,
                esperadoResumo,
                obtidoResumo,
                mensagem
        );
    }
}
