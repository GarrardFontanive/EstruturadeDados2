# Estrutura de Dados 2

Projeto de estudo em Java com exemplos de buscas em vetores, árvores e grafos. Cada assunto
possui uma classe executável para acompanhar o algoritmo, alterar os dados de entrada
e comparar os resultados. As implementações usam apenas a biblioteca padrão do Java.

## Como executar

Use um **JDK 17 ou superior**. Confira a instalação com `java -version` e
`javac -version`.

```bash
git clone https://github.com/GarrardFontanive/EstruturadeDados2.git
cd EstruturadeDados2
```

Na raiz do projeto, compile todas as classes (Linux/macOS ou Git Bash):

```bash
mkdir -p out
find src -name '*.java' > out/fontes.txt
javac --release 17 -encoding UTF-8 -d out @out/fontes.txt
java -cp out Main
```

No PowerShell, a compilação equivalente é:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
$fontes = Get-ChildItem src -Recurse -Filter *.java | Select-Object -ExpandProperty FullName
javac --release 17 -encoding UTF-8 -d out $fontes
java -cp out Main
```

O `Main` mostra o índice de estudo. Para executar um assunto, escolha um dos comandos:

```bash
java -cp out Busca.ExemploBuscaSequencial
java -cp out Busca.ExemploBuscaBinaria
java -cp out Busca.ExemploArrays
java -cp out Arvores.ArvoreBinaria.MainBT
java -cp out Arvores.ArvoreBinariaBusca.MainBST
java -cp out Arvores.ArvoreAvl.MainAvl
java -cp out Arvores.ArvoreRubroNegra.MainArvoreRubroNegra
java -cp out Grafos.GrafoMatrizAdjacencia
```

No IntelliJ IDEA, abra a pasta do projeto, selecione um JDK instalado e marque `src`
como **Sources Root**, caso necessário. Execute o método `main` da classe escolhida.
Não é necessário instalar bibliotecas externas, Maven ou Gradle. O diretório `out/`
guarda arquivos gerados e não entra nos commits.

## Organização

```text
src/
├── Main.java                         # Índice dos exemplos
├── Busca/
│   ├── ExemploBuscaSequencial.java   # Busca em vetor sem ordenação obrigatória
│   ├── ExemploBuscaBinaria.java      # Busca em vetor ordenado
│   └── ExemploArrays.java            # Arrays.binarySearch e ponto de inserção
├── Grafos/
│   └── GrafoMatrizAdjacencia.java     # Matriz, direção, pesos e demonstração
└── Arvores/
    ├── ArvoreBinaria/
    │   ├── ArvoreBinaria.java        # Montagem manual e percursos
    │   └── MainBT.java               # Demonstração
    ├── ArvoreBinariaBusca/
    │   ├── ArvoreBinariaBusca.java   # BST: inserir, buscar, remover e consultar
    │   └── MainBST.java              # Demonstração
    ├── ArvoreAvl/
    │   ├── ArvoreAvl.java            # BST com balanceamento por alturas
    │   └── MainAvl.java              # Demonstração
    └── ArvoreRubroNegra/
        ├── ArvoreRubroNegra.java     # BST com balanceamento por cores
        └── MainArvoreRubroNegra.java # Demonstração
```

As classes internas `No` representam os nós de cada árvore. A enumeração `Cor`
pertence à árvore rubro-negra. Elas permanecem junto à estrutura que as utiliza.

## Roteiro de estudo

### 1. Busca sequencial

Leia [ExemploBuscaSequencial.java](src/Busca/ExemploBuscaSequencial.java).
O algoritmo compara o valor procurado com cada posição do vetor. Funciona com
dados desordenados, retorna o primeiro índice encontrado e devolve `-1` na ausência.
O exemplo procura `12` e encontra o índice `4`.

**Pratique:** procure um valor ausente e depois use um vetor vazio. Observe quantas
comparações são necessárias quando o valor está na primeira e na última posição.

### 2. Busca binária

Leia [ExemploBuscaBinaria.java](src/Busca/ExemploBuscaBinaria.java).
Acompanhe `inicio`, `fim` e `meio`: cada comparação elimina metade do intervalo.
O vetor precisa estar ordenado em ordem crescente. O exemplo procura `7` e retorna
o índice `1`; valores ausentes retornam `-1`.

**Pratique:** anote os três índices a cada iteração. Compare o número de comparações
com a busca sequencial usando o mesmo vetor.

### 3. Busca da biblioteca Java

Leia [ExemploArrays.java](src/Busca/ExemploArrays.java). `Arrays.binarySearch`
também exige ordenação. Quando não encontra o valor, retorna
`-(ponto de inserção) - 1`. Procurar `20` produz `-6`: o ponto de inserção é `5`.
As duas buscas implementadas nas aulas usam apenas `-1` para indicar ausência.

**Pratique:** procure valores antes do primeiro e depois do último elemento e
calcule seus pontos de inserção.

### 4. Árvore binária

Leia [ArvoreBinaria.java](src/Arvores/ArvoreBinaria/ArvoreBinaria.java) e execute
[MainBT.java](src/Arvores/ArvoreBinaria/MainBT.java). Os filhos são definidos
manualmente, sem regra de ordenação. O exemplo monta esta árvore:

```text
    A
   / \
  B   C
 / \   \
D   E   F
```

| Operação | Resultado do exemplo |
| --- | --- |
| Pré-ordem: raiz, esquerda, direita | A B D E C F |
| Em ordem: esquerda, raiz, direita | D B E A C F |
| Pós-ordem: esquerda, direita, raiz | D E B F C A |
| Quantidade de folhas | 3: D, E e F |

**Pratique:** acrescente um filho esquerdo em `C` e antecipe os percursos antes de
executar. Observe que em ordem não significa valores ordenados nessa árvore.

### 5. Árvore binária de busca — BST

Leia [ArvoreBinariaBusca.java](src/Arvores/ArvoreBinariaBusca/ArvoreBinariaBusca.java)
e execute [MainBST.java](src/Arvores/ArvoreBinariaBusca/MainBST.java).
Valores menores ficam à esquerda e maiores à direita; duplicatas são rejeitadas.
A remoção trata três situações: folha, um filho e dois filhos. No último caso,
o valor é substituído pelo menor elemento da subárvore direita, seu sucessor.

Após remover `20` e `50`, o exemplo apresenta `30, 40, 67, 70, 80` em ordem:
há **5 nós**, menor valor **30**, maior valor **80**, nível de `40` igual a **2**
e altura igual a **2**. O percurso por nível usa uma fila; `-` representa filho ausente.

A raiz tem nível `0`. A altura conta arestas: árvore vazia tem altura `-1` e uma
folha tem altura `0`. `nivelDoNo` retorna `-1` se o valor estiver ausente;
`menorValor` e `maiorValor` retornam `null` se a árvore estiver vazia.

**Pratique:** insira valores já ordenados e desenhe o resultado. Remova também um
nó com apenas um filho e compare a altura antes e depois.

### 6. Árvore AVL

Leia [ArvoreAvl.java](src/Arvores/ArvoreAvl/ArvoreAvl.java) e execute
[MainAvl.java](src/Arvores/ArvoreAvl/MainAvl.java). A AVL mantém o fator de
balanceamento `FB = altura direita - altura esquerda` entre `-1` e `1`.
Os campos de altura dos ramos usam `0` para ramo vazio e `1` para um ramo com
apenas um filho folha. Quando há desequilíbrio, rotações simples ou duplas
reorganizam as referências e as alturas são atualizadas.

O exemplo insere 11 valores e remove `10`, restando **10 nós**. Depois mostra
os fatores de balanceamento, os valores no intervalo inclusivo `[5, 40]` e
o parentesco de `45`. Valores duplicados são ignorados. `contarNos()` imprime
a contagem; na BST, o método de mesmo nome retorna um inteiro.

**Pratique:** crie árvores novas com as sequências `30, 20, 10`, `10, 20, 30`,
`30, 10, 20` e `10, 30, 20`. Identifique as rotações e confira se a raiz final é `20`.

### 7. Árvore rubro-negra

Leia [ArvoreRubroNegra.java](src/Arvores/ArvoreRubroNegra/ArvoreRubroNegra.java) e
execute [MainArvoreRubroNegra.java](src/Arvores/ArvoreRubroNegra/MainArvoreRubroNegra.java).
O balanceamento usa cores e rotações. A raiz é preta, um nó vermelho não pode
ter filho vermelho e todos os caminhos de um nó até as folhas externas possuem
a mesma quantidade de nós pretos. Referências nulas são consideradas pretas.

Na correção da inserção, acompanhe os casos de tio vermelho, triângulo e linha,
além de seus casos simétricos. O exemplo mostra os valores em ordem com suas
cores, **4 nós vermelhos**, **3 pretos** e buscas por `40` (`true`) e `99` (`false`).
A contagem considera apenas nós armazenados, sem incluir referências nulas.
Esta implementação possui inserção e busca; **remoção não está implementada**.

**Pratique:** insira valores em ordem crescente e acompanhe quando ocorrem
recolorações e rotações. Tente inserir uma duplicata e observe a rejeição.

### 8. Grafos com matriz de adjacência

Leia e execute [GrafoMatrizAdjacencia.java](src/Grafos/GrafoMatrizAdjacencia.java).
O construtor recebe a quantidade de vértices e duas opções: `direcionado` e
`ponderado`. Os vértices são identificados de `0` a `numeroVertices - 1`;
na matriz, a linha representa a origem e a coluna representa o destino.

Em um grafo **não direcionado**, inserir ou remover uma aresta atualiza também
a posição inversa, mantendo a matriz simétrica. Em um grafo **direcionado**,
apenas a posição origem → destino é alterada.

Use `adicionarAresta(origem, destino)` em grafos não ponderados e
`adicionarAresta(origem, destino, peso)` em grafos ponderados. As sobrecargas
avisam quando são usadas no tipo de grafo incorreto. A inserção e a remoção
validam os índices dos vértices antes de acessar a matriz.

Internamente, `Double.POSITIVE_INFINITY` indica ausência de aresta. Na impressão,
grafos não ponderados usam `1` para presença e `0` para ausência; grafos ponderados
mostram o peso ou `inf`. **Peso zero é uma aresta válida**, diferente de ausência.

O `main` da própria classe apresenta dois exemplos com quatro vértices:

```text
Não direcionado e não ponderado:
      0  1  2  3
  0   0  1  1  0
  1   1  0  1  0
  2   1  1  0  1
  3   0  0  1  0

Direcionado e ponderado:
        0    1    2    3
  0   inf  4.0  1.5  inf
  1   inf  inf  inf  2.0
  2   inf  0.0  inf  inf
  3   inf  inf  inf  inf
```

`removerAresta(origem, destino)` restaura a ausência de aresta, inclusive na
posição inversa quando o grafo não é direcionado. A classe ainda não implementa
percursos em largura/profundidade nem listagem de vizinhos.

**Pratique:** remova a aresta entre `0` e `2` do primeiro exemplo e imprima novamente.
No segundo, compare `2 → 1`, de peso zero, com `1 → 2`, que não existe. Tente usar
o vértice `4` para observar a validação dos limites.

## Exercícios de árvores B

As [respostas resumidas dos Exercícios 1](docs/ArvoreB/Exercicios1.md) incluem os
limites de ocupação, intervalos das subárvores e desenhos das inserções e remoções.

## Comparação dos custos

Considere `n` como o número de elementos e `h` como a altura da árvore.

| Algoritmo/estrutura | Busca — pior caso | Inserção/remoção — pior caso | Observação |
| --- | --- | --- | --- |
| Busca sequencial | O(n) | Não se aplica | Vetor pode estar desordenado |
| Busca binária / Arrays.binarySearch | O(log n) | Não se aplica | Vetor previamente ordenado |
| Árvore binária manual | Não implementada | Ligação de filho: O(1); remoção ausente | Percursos e contagem: O(n) |
| BST | O(h), chegando a O(n) | O(h), chegando a O(n) | Pode degenerar em uma cadeia |
| AVL | O(log n) | O(log n) | Balanceamento por alturas |
| Rubro-negra | O(log n) | Inserção: O(log n); remoção ausente | Balanceamento por cores |

O custo de ordenar um vetor não está incluído no custo da busca binária.
As árvores usam O(n) de armazenamento; percursos recursivos usam pilha O(h).

No grafo com `V` vértices, a matriz ocupa O(V²) de espaço. A inicialização e a
impressão custam O(V²); inserir ou remover uma aresta custa O(1), pois acessa
diretamente uma ou duas células da matriz.

## Estudando pelos commits

As classes possuem commits individuais com mensagens em português que explicam
o conceito, o comportamento e os pontos para observar. As classes `No` e a
enumeração `Cor` fazem parte do commit da árvore correspondente. O histórico
anterior do projeto e o commit inicial deste repositório foram preservados.

```bash
git log --oneline --reverse --first-parent
git log --follow -- src/Arvores/ArvoreAvl/ArvoreAvl.java
git show <hash-do-commit>
```

Para estudar, leia a mensagem completa com `git show`, acompanhe o método no
depurador e altere apenas um conjunto de entradas por vez. Os resultados acima
servem como referência para conferir suas primeiras execuções.
