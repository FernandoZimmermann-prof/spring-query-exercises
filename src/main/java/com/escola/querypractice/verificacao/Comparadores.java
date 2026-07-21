package com.escola.querypractice.verificacao;

import com.escola.querypractice.model.EntidadeBase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utilitarios para comparar o resultado do aluno com o gabarito.
 * (Parte interna do verificador - o aluno nao precisa mexer aqui.)
 */
final class Comparadores {

    private Comparadores() {
    }

    static boolean iguais(Object obtido, Object esperado, ModoComparacao modo) {
        switch (modo) {
            case ESCALAR:
                return escalarIgual(obtido, esperado);
            case LISTA_ORDENADA:
                return ids(obtido).equals(ids(esperado));
            case LISTA:
            default:
                List<Long> a = new ArrayList<>(ids(obtido));
                List<Long> b = new ArrayList<>(ids(esperado));
                Collections.sort(a);
                Collections.sort(b);
                return a.equals(b);
        }
    }

    private static List<Long> ids(Object o) {
        if (o == null) {
            return Collections.emptyList();
        }
        if (!(o instanceof List<?> lista)) {
            throw new IllegalStateException("Esperava uma List de entidades, mas veio: " + o.getClass());
        }
        return lista.stream()
                .map(Comparadores::idDe)
                .collect(Collectors.toList());
    }

    private static Long idDe(Object e) {
        if (e instanceof EntidadeBase base) {
            return base.getId();
        }
        throw new IllegalStateException("O item da lista nao e uma entidade conhecida: "
                + (e == null ? "null" : e.getClass()));
    }

    private static boolean escalarIgual(Object obtido, Object esperado) {
        if (obtido == null || esperado == null) {
            return obtido == esperado;
        }
        if (obtido instanceof BigDecimal || esperado instanceof BigDecimal) {
            return paraBigDecimal(obtido).compareTo(paraBigDecimal(esperado)) == 0;
        }
        if (obtido instanceof Number nObt && esperado instanceof Number nEsp) {
            return nObt.longValue() == nEsp.longValue();
        }
        return obtido.equals(esperado);
    }

    private static BigDecimal paraBigDecimal(Object o) {
        if (o instanceof BigDecimal bd) {
            return bd;
        }
        if (o instanceof Number n) {
            return BigDecimal.valueOf(n.doubleValue());
        }
        return new BigDecimal(o.toString());
    }

    static String resumo(Object o) {
        if (o == null) {
            return "null";
        }
        if (o instanceof List<?> lista) {
            List<Long> ids = lista.stream().map(Comparadores::idDe).collect(Collectors.toList());
            String plural = lista.size() == 1 ? " item" : " itens";
            return "IDs " + ids + " (" + lista.size() + plural + ")";
        }
        return String.valueOf(o);
    }
}
