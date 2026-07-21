package com.escola.querypractice.verificacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Endpoints para o aluno conferir seus exercicios.
 *
 *   GET /exercicios       -> todos os exercicios com status (PASSOU/FALHOU/...)
 *   GET /exercicios/{n}   -> um exercicio especifico
 *
 * A pagina inicial (http://localhost:8080) mostra tudo isso de forma
 * visual, com verde/vermelho.
 */
@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    private final Verificador verificador;

    public ExercicioController(Verificador verificador) {
        this.verificador = verificador;
    }

    @GetMapping
    public List<ResultadoExercicio> todos() {
        return verificador.avaliarTodos();
    }

    @GetMapping("/{numero}")
    public ResponseEntity<ResultadoExercicio> um(@PathVariable int numero) {
        ResultadoExercicio r = verificador.avaliar(numero);
        return r == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(r);
    }
}
