# Exercícios 1 de árvores B — respostas resumidas

**1. Ordem 6**

a) Máximo de chaves: **5** (`6 − 1`).

b) Mínimo de chaves, exceto raiz: **2** (`⌊(6 − 1)/2⌋`).

c) Máximo de filhos: **6**.

d) Mínimo de filhos de um nó interno não raiz: **3**. Se for a raiz, **2**.

**2. Nó [15 | 30 | 50]**

Possui **4 filhos**, com os intervalos: **x < 15**, **15 < x < 30**, **30 < x < 50** e **x > 50**, da esquerda para a direita.

**3. Inserções em ordem 4**

As divisões ocorrem ao inserir **1, 4 e 13**:

```text
Inserção de 1: [1 | 3 | 8 | 14] transborda; sobe 8.
          [8]
         /   \
    [1 | 3]  [14]

Inserção de 4: [1 | 3 | 4 | 6] transborda; sobe 4.
             [4 | 8]
           /    |    \
      [1 | 3]  [6]  [10 | 14 | 16]

Inserção de 13: [10 | 13 | 14 | 16] transborda; sobe 14.
                 [4 | 8 | 14]
              /      |      |      \
         [1 | 3] [6 | 7] [10 | 13] [16]
```

**4. Inserções em ordem 5**

Há dois transbordamentos:

```text
Ao inserir 50:
[10 | 20 | 30 | 40 | 50] → sobe 30.

             [30]
            /    \
     [10 | 20]  [40 | 50]

Ao inserir 80, antes da correção:
             [30]
            /    \
     [10 | 20]  [40 | 50 | 60 | 70 | 80]

Sobe 60; resultado final:
                [30 | 60]
              /     |     \
     [10 | 20] [40 | 50] [70 | 80]
```

**5. Remoções em ordem 5**

**Remover 10:** a folha fica com apenas [20]. Como a irmã [40 | 50] está no mínimo, ocorre **fusão**, incluindo o separador 30.

```text
                  [60]
                 /    \
 [20 | 30 | 40 | 50]  [70 | 80 | 90]
```

**Remover 40:** **remoção direta**; a folha continua acima do mínimo.

```text
                [60]
               /    \
 [20 | 30 | 50]      [70 | 80 | 90]
```

**Remover 60:** **troca pelo sucessor 70**, seguida da remoção direta de 70 na folha.

```text
                [70]
               /    \
 [20 | 30 | 50]      [80 | 90]
```

**6. Chaves da raiz**

**a)** Ao inserir 115, a folha [105 | 107 | 109 | 112 | 115] transborda e promove **109**. A raiz fica **[109 | 119 | 231]**.

**b)** Adotando fusão com o irmão esquerdo quando ambos estão no mínimo, como no exemplo dos slides:

- Remover **221**: [220] fica insuficiente e se funde com [112 | 115] e o separador 119. Raiz: **[109 | 231]**.
- Remover **400**: [232] fica insuficiente; ocorre redistribuição com a irmã [112 | 115 | 119 | 220]. **231 desce e 220 sobe**. Raiz final: **[109 | 220]**.

O enunciado não fixa o lado da fusão: se, na remoção de 221, for escolhida a irmã direita, a raiz final será **[109 | 119]**, também válida.
