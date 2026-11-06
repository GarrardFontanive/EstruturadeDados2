package Arvores.ArvoreRubroNegra;

/**
 * BST balanceada por cores: raiz preta, nenhum vermelho com filho vermelho e
 * mesma quantidade de nós pretos em todos os caminhos até referências nulas.
 * Inserção e busca têm tempo O(log n). A remoção ainda não está implementada.
 */
public class ArvoreRubroNegra {
    private enum Cor {
        VERMELHO,
        PRETO
    }

    private class No {
        private int valor;
        private Cor cor;
        private No esq, dir, pai;

        public No(int valor, Cor cor) {
            this.valor = valor;
            this.cor = cor;
            esq = dir = pai = null;
        }
    }

    private No raiz;

    public ArvoreRubroNegra() {
        raiz = null;
    }

    public void inserir(int valor) {
        if (buscar(valor)) {
            System.out.println("O valor " + valor + " já existe na árvore");
            return;
        }

        No novoNo = new No(valor, Cor.VERMELHO);
        raiz = inserirNo(raiz, novoNo);
        corrigirInsercao(novoNo);
    }

    // Insere como em uma árvore binária de busca
    private ArvoreRubroNegra.No inserirNo(No atual, No novoNo) {
        if (atual == null) {
            return novoNo;
        }

        if (novoNo.valor < atual.valor) {
            atual.esq = inserirNo(atual.esq, novoNo);
            atual.esq.pai = atual;
        } else if (novoNo.valor > atual.valor) {
            atual.dir = inserirNo(atual.dir, novoNo);
            atual.dir.pai = atual;
        }

        return atual;
    }

    /** Trata tio vermelho, triângulo e linha; depois garante a raiz preta. */
    public void corrigirInsercao(No no) {
        while (no != raiz && cor(no.pai) == Cor.VERMELHO) {
            No pai = no.pai;
            No avo = pai.pai;

            if (pai == avo.esq) {
                No tio = avo.dir;

                // CASO 1: tio vermelho
                if (cor(tio) == Cor.VERMELHO) {
                    pai.cor = Cor.PRETO;
                    tio.cor = Cor.PRETO;
                    avo.cor = Cor.VERMELHO;
                    no = avo;
                } else {
                    // CASO 2: triângulo
                    if (no == pai.dir) {
                        no = pai;
                        rotacaoEsquerda(no);

                        pai = no.pai;
                        avo = pai.pai;
                    }

                    // CASO 3: linha
                    pai.cor = Cor.PRETO;
                    avo.cor = Cor.VERMELHO;
                    rotacaoDireita(avo);
                }
            } else {
                // Casos simétricos
                No tio = avo.esq;

                // CASO 1: tio vermelho
                if (cor(tio) == Cor.VERMELHO) {
                    pai.cor = Cor.PRETO;
                    tio.cor = Cor.PRETO;
                    avo.cor = Cor.VERMELHO;
                    no = avo;
                } else {
                    // CASO 2: triângulo
                    if (no == pai.esq) {
                        no = pai;
                        rotacaoDireita(no);

                        pai = no.pai;
                        avo = pai.pai;
                    }

                    // CASO 3: linha
                    pai.cor = Cor.PRETO;
                    avo.cor = Cor.VERMELHO;
                    rotacaoEsquerda(avo);
                }
            }
        }

        // A raiz deve ser sempre preta
        raiz.cor = Cor.PRETO;
    }

    private void rotacaoEsquerda(No no) {
        No novaRaiz = no.dir;
        no.dir = novaRaiz.esq;

        if (novaRaiz.esq != null) {
            novaRaiz.esq.pai = no;
        }

        novaRaiz.pai = no.pai;

        if (no.pai == null) {
            raiz = novaRaiz;
        } else if (no == no.pai.esq) {
            no.pai.esq = novaRaiz;
        } else {
            no.pai.dir = novaRaiz;
        }

        novaRaiz.esq = no;
        no.pai = novaRaiz;
    }

    private void rotacaoDireita(No no) {
        No novaRaiz = no.esq;

        no.esq = novaRaiz.dir;

        if (novaRaiz.dir != null) {
            novaRaiz.dir.pai = no;
        }

        novaRaiz.pai = no.pai;

        if (no.pai == null) {
            raiz = novaRaiz;
        } else if (no == no.pai.dir) {
            no.pai.dir = novaRaiz;
        } else {
            no.pai.esq = novaRaiz;
        }

        novaRaiz.dir = no;
        no.pai = novaRaiz;
    }

    public boolean buscar(int valor) {
        return buscarNo(raiz, valor) != null;
    }

    private No buscarNo(No atual, int valor) {
        if (atual == null || atual.valor == valor) {
            return atual;
        }

        if (valor < atual.valor) {
            return buscarNo(atual.esq, valor);
        } else {
            return buscarNo(atual.dir, valor);
        }
    }

    /** Referências nulas representam folhas externas pretas. */
    private Cor cor(No no) {
        return no == null ? Cor.PRETO : no.cor;
    }

    public void mostrar() {
        mostrar(raiz);
    }

    private void mostrar(No raiz) {
        if (raiz != null) {
            mostrar(raiz.esq);
            System.out.println(raiz.valor + " (" + raiz.cor + ")");
            mostrar(raiz.dir);
        }
    }

    /** Conta os nós armazenados; as folhas externas nulas não entram na soma. */
    public void contarNosPorCor() {
        int vermelhos = contarNosPorCor(raiz, Cor.VERMELHO);
        int pretos = contarNosPorCor(raiz, Cor.PRETO);

        System.out.println("Nós vermelhos: " + vermelhos);
        System.out.println("Nós pretos: " + pretos);
    }

    private int contarNosPorCor(No no, Cor cor) {
        if (no == null) {
            return 0;
        }
        int quantidade = 0;
        if (no.cor == cor) {
            quantidade = 1;
        }
        return quantidade
                + contarNosPorCor(no.esq, cor)
                + contarNosPorCor(no.dir, cor);
    }
}
