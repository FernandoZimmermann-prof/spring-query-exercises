package com.escola.querypractice.verificacao;

public enum ModoComparacao {
    /** Compara as listas ignorando a ordem (usa o conjunto de IDs). */
    LISTA,
    /** Compara as listas exigindo a mesma ordem (para exercicios com ORDER BY / Top N). */
    LISTA_ORDENADA,
    /** Compara valores unicos (count, soma, etc). */
    ESCALAR
}
