package Arvores.ArvoreBinaria;

/**
 * Árvore de caracteres montada manualmente, com até dois filhos por nó.
 * Não impõe ordenação: esquerda e direita são posições, não comparações.
 */
public class ArvoreBinaria {
    /** Nó que armazena o valor e as referências para os filhos. */
    public class No {
        private char valor;
        private No esquerdo;
        private No direito;

        public No(char valor) {
            this.valor = valor;
        }
    }
    private No raiz;

    public No getRaiz() {
        return raiz;
    }

    /** Define uma nova raiz; se já existia uma árvore, ela é substituída. */
    public No criarRaiz(char valor) {
        raiz = new No(valor);
        return raiz;
    }

    public No adicionarEsquerda(No pai, char valor) {
        if(pai == null) {
            System.out.println("Não foi possivel inserir: nó pai inexistente");
            return null;
        }

        if(pai.esquerdo != null) {
            System.out.println("Nó pai já possui filho esquerdo");
            return null;
        }

        pai.esquerdo = new No(valor);
        return pai.esquerdo;
    }

    public No adicionarDireita(No pai, char valor) {
        if(pai == null) {
            System.out.println("Não foi possivel inserir: nó pai inexistente");
            return null;
        }

        if(pai.direito != null) {
            System.out.println("Nó pai já possui filho direito");
            return null;
        }

        pai.direito = new No(valor);
        return pai.direito;
    }

    /** Visita raiz, esquerda e direita. Os três percursos têm tempo O(n). */
    public void preOrdem() {
        preOrdem(raiz);
        System.out.println();
    }
    public void preOrdem(No no) {
        if(no == null) {
            return;
        }
        System.out.print(no.valor+" ");
        preOrdem(no.esquerdo);
        preOrdem(no.direito);
    }

    /** Visita esquerda, raiz e direita; aqui não garante valores ordenados. */
    public void emOrdem() {
        emOrdem(raiz);
        System.out.println();
    }

    public void emOrdem(No no) {
        if(no == null) {
            return;
        }
        emOrdem(no.esquerdo);
        System.out.print(no.valor+" ");
        emOrdem(no.direito);
    }

    /** Visita esquerda, direita e raiz. */
    public void posOrdem() {
        posOrdem(raiz);
        System.out.println();
    }

    public void posOrdem(No no) {
        if(no == null) {
            return;
        }
        posOrdem(no.esquerdo);
        posOrdem(no.direito);
        System.out.print(no.valor + " ");
    }

    /** Conta apenas nós sem filhos; a árvore vazia possui zero folhas. */
    public int quantidadeFolhas() {
        return quantidadeFolhas(raiz);
    }

    private int quantidadeFolhas(No no) {
        if(no == null) {
            return 0;
        }

        if(no.esquerdo == null && no.direito == null) {
            return 1;
        }

        return quantidadeFolhas(no.esquerdo) +
                quantidadeFolhas(no.direito);
    }

}
