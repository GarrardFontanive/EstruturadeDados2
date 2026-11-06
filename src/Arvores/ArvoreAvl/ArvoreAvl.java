package Arvores.ArvoreAvl;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BST que mantém a diferença entre alturas dos ramos entre -1 e 1.
 * Rotações após inserções e remoções mantêm essas operações em O(log n).
 * Valores repetidos são ignorados.
 */
public class ArvoreAvl {

    private class No {
        int valor;
        No esq, dir;
        // Ramo vazio tem altura 0; um ramo com apenas um filho folha tem 1.
        int alturaEsq, alturaDir;

        public No(int valor) {
            this.valor = valor;
            esq = dir = null;
            alturaEsq = alturaDir = 0;

        }

    }

    private No raiz;

    public ArvoreAvl() {
        raiz = null;
    }

    public void inserirNo(int valor) {
        raiz = inserirNo(raiz, valor);
    }

    private No inserirNo(No atual, int valor) {
        if (atual == null) {
            return new No(valor);
        }

        //Insercao na subarvore esquerda (valores menores)
        if (valor < atual.valor) {
            atual.esq = inserirNo(atual.esq, valor);
            atual.alturaEsq = Math.max(atual.esq.alturaEsq, atual.esq.alturaDir) + 1;
        }
        //Insercao na subarvore da direita (valores maiores)
        else if (valor > atual.valor) {
            atual.dir = inserirNo(atual.dir, valor);
            atual.alturaDir = Math.max(atual.dir.alturaEsq, atual.dir.alturaDir) + 1;
        }

        return balancear(atual);

    }

    /** Usa FB = altura direita - altura esquerda em toda a implementação. */
    private No balancear(No atual) {
        int fatorBalanceamento = atual.alturaDir - atual.alturaEsq;

        //Desbalanceamento a direita
        if (fatorBalanceamento == 2) {
            //Se o filho a direita tambem fator positivo
            //(Rotacao simples a esquerda)
            if (atual.dir.alturaDir >= atual.dir.alturaEsq) {
                return rotacaoEsquerda(atual);
            } else {
                atual.dir = rotacaoDireita(atual.dir);
                return rotacaoEsquerda(atual);
            }
        }
        //Desbalanceamento a esquerda
        else if (fatorBalanceamento == -2) {
            //Rotacao simples a direita
            if (atual.esq.alturaEsq >= atual.esq.alturaDir) {
                return rotacaoDireita(atual);
            } else {
                //Caso contrario, rotacao dupla esquerda-direita
                atual.esq = rotacaoEsquerda(atual.esq);
                return rotacaoDireita(atual);
            }
        }

        //Retorna no atual, balanceado se necessario

        return atual;
    }

    private No rotacaoEsquerda(No raiz) {
        No novaRaiz = raiz.dir; //Filho dir se torna a nova raiz
        raiz.dir = novaRaiz.esq;
        novaRaiz.esq = raiz;

        //Atualizar as alturas
        atualizarAlturas(raiz);
        atualizarAlturas(novaRaiz);

        return novaRaiz;
    }

    private No rotacaoDireita(No raiz) {
        No novaRaiz = raiz.esq;//Filho esq se torna a nova raiz
        raiz.esq = novaRaiz.dir;
        novaRaiz.dir = raiz;

        //Atualizar as alturas
        atualizarAlturas(raiz);
        atualizarAlturas(novaRaiz);

        return novaRaiz;
    }

    private void atualizarAlturas(No no) {
        if (no != null) {
            no.alturaEsq = (no.esq == null) ? 0 :
                    Math.max(no.esq.alturaEsq, no.esq.alturaDir) + 1;

            no.alturaDir = (no.dir == null) ? 0 :
                    Math.max(no.dir.alturaEsq, no.dir.alturaDir) + 1;
        }
    }


    public void mostrar() {
        mostrar(raiz);
    }

    private void mostrar(No no) {
        if (no != null) {
            mostrar(no.esq);
            System.out.print(no.valor + " ");
            mostrar(no.dir);
        }
    }


    public void remover(int valor) {
        if (!buscar(valor)) {
            System.out.println("Valor" + valor + " nao existe na arvore");
            return;
        }

        raiz = remover(raiz, valor);

    }

    private No remover(No no, int valor) {
        //Procurar pelo no
        if (valor < no.valor) {
            no.esq = remover(no.esq, valor);
        } else if (valor > no.valor) {
            no.dir = remover(no.dir, valor);
        } else {
            // Caso 1: no folha
            if (no.esq == null && no.dir == null) {
                return null;
            }

            //Caso 2: possui somente um filho a direita
            if (no.esq == null) {
                return no.dir;
            }

            //Caso 2: possui somente um filho a esquerda
            if (no.dir == null) {
                return no.esq;
            }

            //Caso 3: possui dois filho
            No sucessor = encontrarMenor(no.dir);

            //Copia o valor do sucessor
            no.valor = sucessor.valor;

            //Remover o sucessor da posicao original
            no.dir = remover(no.dir, sucessor.valor);
        }

        atualizarAlturas(no);
        return balancear(no);
    }

    private No encontrarMenor(No no) {
        while (no.esq != null) {
            no = no.esq;
        }
        return no;
    }


    public void mostrarPorNivel() {
        if (raiz == null) {
            System.out.println("Arvore vazia");
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
                    System.out.print("- ");
                }
            }

            System.out.println();

        }

    }

    /** Exibe a quantidade total de nós; percorre toda a árvore em O(n). */
    public void contarNos() {
        System.out.println(contarNos(raiz));
    }

    private int contarNos(No no) {

        if (no == null) {
            return 0;
        }
        return 1 + contarNos(no.esq) + contarNos(no.dir);

    }

    public boolean buscar(int valor) {
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

    /** Exibe o mesmo fator de balanceamento usado para decidir as rotações. */
    public void mostrarFB() {
        if (raiz == null) {
            System.out.println("Arvore vazia");
        }
        mostrarFB(raiz);
    }

    public void mostrarFB(No no) {
        if (no != null) {
            mostrarFB(no.esq);

            int fb = no.alturaDir - no.alturaEsq;

            System.out.println("valor: " + no.valor + " fb: " + fb);

            mostrarFB(no.dir);
        }
    }

    /** Exibe em ordem os valores do intervalo inclusivo [minimo, maximo]. */
    public void mostrarIntervalo(int minimo, int maximo) {
        if (minimo > maximo) {
            System.out.println("Intervalo invalido");
            return;
        }

        mostrarIntervalo(raiz, minimo, maximo);
    }

    private void mostrarIntervalo(No no, int minimo, int maximo) {
        if (no != null) {

            mostrarIntervalo(no.esq, minimo, maximo);

            if (no.valor >= minimo && no.valor <= maximo) {
                System.out.print(no.valor + " ");
            }

            mostrarIntervalo(no.dir, minimo, maximo);
        }
    }

    /** Localiza um valor para mostrar seu pai e, quando existir, seu irmão. */
    public void mostrarParentesco(int valor) {
        No atual = raiz;
        No pai = null;

        while (atual != null && atual.valor != valor) {
            pai = atual;

            if (valor < atual.valor) {
                atual = atual.esq;
            } else {
                atual = atual.dir;
            }
        }

        if (atual == null) {
            System.out.println("valor nao encontrado.");
            return;
        }

        if (atual == raiz) {
            System.out.println("o valor corresponde a raiz.");
            return;
        }

        System.out.println("pai: " + pai.valor);

        No irmao;

        if (pai.esq == atual) {
            irmao = pai.dir;
        } else {
            irmao = pai.esq;
        }

        if (irmao != null) {
            System.out.println("irmao: " + irmao.valor);
        } else {
            System.out.println("o valor nao possui irmao.");
        }
    }
}
