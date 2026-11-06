package Arvores.ArvoreBinariaBusca;

import java.util.Queue;
import java.util.LinkedList;

/**
 * BST de inteiros: menores à esquerda e maiores à direita, sem duplicatas.
 * Inserção, busca e remoção custam O(h), onde h é a altura; no pior caso, O(n).
 * Esta implementação não faz balanceamento automático.
 */
public class ArvoreBinariaBusca {
    public class No{
        int valor;
        No dir;
        No esq;

        public No(int valor) {
            this.valor = valor;
            dir = esq = null;
        }
    }

    No raiz;

    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }

    private No inserir(No no, int valor) {
        if (no == null) {
            no = new No(valor);
            return no;
        }

        if (valor < no.valor) {
            no.esq = inserir(no.esq, valor);
            return no;
        } else if (valor > no.valor) {
            no.dir = inserir(no.dir, valor);
        } else {
            System.out.println("O valor: " + valor + " Já existe na árvore");
        }
        return no;
    }

    public void emOrdem() {
        emOrdem(raiz);
    }

    private void emOrdem(No no) {
        if (no != null) {
            emOrdem(no.esq);
            System.out.print(" [" + no.valor + "] ");
            emOrdem(no.dir);
        }

    }

    public boolean busca(int valor) {
        No atual = raiz;

        while (atual != null) {
            if (valor == atual.valor) {
                return true;
            }

            if (valor < atual.valor) {
                atual = atual.esq;
            } else {
                atual = atual.dir;
            }
        }
        return false;
    }

    /** Remove folha, nó com um filho ou nó com dois filhos usando seu sucessor. */
    public void remover(int valor) {
        if (!busca(valor)) {
            System.out.println("Valor " + valor + " não existe na arvore");
            return;
        }
        raiz = remover(raiz, valor);
    }

    private No remover(No no, int valor) {
        //procurar
        if (valor < no.valor) {
            no.esq = remover(no.esq, valor);
        } else if (valor > no.valor) {
            no.dir = remover(no.dir, valor);
        } else {
            // case 1: nó folha
            if(no.esq == null && no.dir == null){
                return null;
            }
            //case 2.1: possui somente um filho a direita
            if(no.esq == null) {
                return no.dir;
            }
            //case 2.2: possui somente um filho a esquerda
            if(no.dir == null) {
                return no.esq;
            }
            //case 3: possui dois filhos
            No sucessor = encontrarMenor(no.dir);
            //copia o valor do sucessor
            no.valor = sucessor.valor;
            //Remover o sucessor da posição original
            no.dir = remover(no.dir, sucessor.valor);
        }
        return no;
    }

    private No encontrarMenor(No no) {
        while (no.esq != null) {
            no = no.esq;
        }
        return no;
    }

    /** Percurso em largura: uma fila guarda os próximos nós a visitar. */
    public void mostrarPorNivel() {
        if (raiz == null) {
            System.out.println ("Árvore vazia");
            return;
        }
        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            int nivelSize = fila.size();
            for (int i = 0; i < nivelSize; i++) {
                No noAtual = fila.poll();

                if (noAtual != null) {
                    System.out.print(noAtual.valor + " ");
                    fila.add(noAtual.esq);
                    fila.add(noAtual.dir);
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }

    /** Soma a raiz e as duas subárvores; tempo O(n). */
    public int contarNos() {
        return contarNos(raiz);
    }

    private int contarNos(No no) {
        if (no == null) {
            return 0;
        }
        return 1 + contarNos(no.esq) + contarNos(no.dir);
    }

    /** Retorna o menor valor ou null quando a árvore está vazia. */
    public Integer menorValor() {
        if (raiz == null) {
            System.out.println("Árvore vazia");
            return null;
        }
        No menor = encontrarMenor(raiz);
        return menor.valor;
    }

    /** Retorna o maior valor ou null quando a árvore está vazia. */

    public Integer maiorValor() {
        if (raiz == null) {
            System.out.println("Árvore vazia");
            return null;
        }
        No atual = raiz;
        while (atual.dir != null) {
            atual = atual.dir;
        }
        return atual.valor;
    }

    /** A raiz está no nível 0; retorna -1 se o valor não existir. */
    public int nivelDoNo(int valor) {
        return nivelDoNo(raiz, valor, 0);
    }

    private int nivelDoNo(No no, int valor, int nivelAtual) {
        if (no == null) {
            return -1;
        }
        if (valor == no.valor) {
            return nivelAtual;
        }

        if (valor >  no.valor) {
            return nivelDoNo(no.dir, valor, nivelAtual + 1);
        } else {
            return nivelDoNo(no.esq, valor, nivelAtual + 1);
        }
    }

    /** Altura em arestas: árvore vazia = -1, folha = 0. Tempo O(n). */
    public int altura() {
        return altura(raiz);
    }

    private int altura(No no) {
        if (no == null) {
            return -1; //começa em 0
        }
        int alturaEsq = altura(no.esq);
        int alturaDire = altura(no.dir);

        return 1 + Math.max(alturaEsq, alturaDire);
    }
}
