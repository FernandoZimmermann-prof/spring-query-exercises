# Pratica de Query Methods e @Query (Spring Data JPA)

Projeto pronto para os alunos praticarem a escrita de queries. **Voce so precisa
mexer nos arquivos de repository** dentro de `repository/`. Todo o resto (entidades,
banco, dados, verificador) ja esta configurado.

Requisito: **Java 17** (JDK). Nao e necessario instalar Maven nem usar terminal:
o IntelliJ ja traz o Maven embutido e faz tudo sozinho.

## Como abrir e rodar (IntelliJ)

1. **File > Open...** e selecione a **pasta do projeto** (a que contem o
   `pom.xml`). Confirme com **Trust Project** se aparecer o aviso.
2. Espere o IntelliJ importar o Maven e baixar as dependencias (barra de
   progresso no rodape). So na primeira vez demora um pouco.
3. Garanta que o SDK do projeto e o Java 17:
   **File > Project Structure > Project > SDK = 17** (e *Language level* 17).
4. Rode a aplicacao de uma destas formas:
   - No menu de execucao (canto superior direito) selecione
     **"QueryPractice (rodar aqui)"** e clique no **▶ verde**; ou
   - Abra `src/main/java/com/escola/querypractice/QueryPracticeApplication.java`
     e clique no **▶ verde** ao lado do metodo `main`.
5. Quando aparecer no console algo como *"Started QueryPracticeApplication"*,
   abra no navegador: **http://localhost:8080**

> Nao precisa de `mvn` no terminal. Se o menu de execucao aparecer vazio, clique
> com o botao direito no `QueryPracticeApplication.java` > **Run**.

## O painel de correcao

Em **http://localhost:8080** voce ve os 20 exercicios. Cada um fica:

- **PASSOU** (verde) – seu metodo devolveu exatamente o resultado esperado
- **FALHOU** (vermelho) – rodou, mas o resultado esta diferente
- **PENDENTE** (cinza) – voce ainda nao criou o metodo
- **ERRO** (laranja) – o metodo existe mas quebrou (ex: `@Query` com erro)

## Fluxo de trabalho

1. Escolha um exercicio (no painel ou na lista em PDF).
2. Abra o repository indicado em
   `src/main/java/com/escola/querypractice/repository/`.
3. Crie o metodo com **o nome e a assinatura exatos** que estao no comentario
   daquele repository.
4. Pare a aplicacao (quadrado vermelho), rode de novo (▶) e clique em
   **Reexecutar** no painel.
5. Fique verde.

> O nome do metodo precisa bater **exatamente**, senao o verificador nao o
> encontra (aparece como PENDENTE).

## Se algo der errado

- **"module not specified" no Run:** clique no dropdown de execucao > **Edit
  Configurations** > em *Module* selecione `query-practice`.
- **Dependencias nao baixaram / erros vermelhos por todo lado:** abra a aba
  **Maven** (lateral direita) e clique no icone de *Reload All Maven Projects*.
- **Porta 8080 ocupada:** feche a instancia anterior (quadrado vermelho) ou
  troque a porta em `src/main/resources/application.properties`
  (`server.port=8081`).

## Endpoints

- `GET /exercicios` – status de todos os exercicios (JSON)
- `GET /exercicios/{numero}` – status de um exercicio
- `GET /h2-console` – console do banco H2 para inspecionar os dados
  - JDBC URL: `jdbc:h2:mem:querydb` · user: `sa` · senha: (em branco)

## Sobre o banco

Banco **H2 em memoria**, que sobe **sempre do zero, com os mesmos dados**
(`src/main/resources/data.sql`) a cada inicializacao. Nao ha risco de "sujar" os
dados: e so reiniciar.

## Modelo de dados

```
Categoria (1) ---< Produto
Cliente   (1) ---< Pedido (1) ---< ItemPedido >--- Produto
```

- **Categoria**: id, nome, descricao
- **Produto**: id, nome, preco, estoque, ativo, dataCadastro, categoria
- **Cliente**: id, nome, email, cidade, estado, dataNascimento
- **Pedido**: id, cliente, dataPedido, status (enum), valorTotal
- **ItemPedido**: id, pedido, produto, quantidade, precoUnitario

## Para o professor

- A **Lista_de_Exercicios.pdf** e o material do aluno e **nao contem respostas**.
- O gabarito fica em **Gabarito_Professor.pdf** e em `GABARITO.md`. Ele e
  calculado em Java puro dentro de `verificacao/Gabarito.java`, de forma
  **independente** do que o aluno escreve — por isso a correcao e confiavel.
- Para entregar sem as respostas, apague `GABARITO.md` e o `Gabarito_Professor.pdf`
  antes de distribuir (deixe apenas a `Lista_de_Exercicios.pdf`).
