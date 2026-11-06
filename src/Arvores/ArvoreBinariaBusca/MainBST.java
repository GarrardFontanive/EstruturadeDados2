package Arvores.ArvoreBinariaBusca;

/** Demonstra operações da BST e as convenções de altura e nível. */
public class MainBST {
    public static void main(String[] args) {
        ArvoreBinariaBusca bst = new ArvoreBinariaBusca();
        bst.inserir(50);
        bst.inserir(30);
        bst.inserir(70);
        bst.inserir(20);
        bst.inserir(40);
        bst.inserir(67);
        bst.inserir(80);

        System.out.println("Tentando inserir valor repetido: ");
        bst.inserir(67);

        System.out.println();
        System.out.println("Em ordem: ");
        bst.emOrdem();

        System.out.println("\n");
        System.out.println("Buscando os valores: 20 e 77 ");
        System.out.println("Buscando 20: " + bst.busca(20));
        System.out.println("Buscando 77: " + bst.busca(77));

        System.out.println();
        System.out.println("Removendo um nó folha");
        bst.remover(20);
        bst.emOrdem();

        System.out.println();
        System.out.println("Removendo um nó com dois filhos");
        bst.remover(50);
        bst.emOrdem();

        System.out.println();
        System.out.println("Mostrando por nivel");
        bst.mostrarPorNivel();

        System.out.println();
        System.out.println("Atv: 1. Contar nós: " + bst.contarNos());

        System.out.println();
        System.out.println("Atv: 2. Menor valor: " + bst.menorValor());

        System.out.println();
        System.out.println("Atv: 3. Maior valor: " + bst.maiorValor());

        System.out.println();
        System.out.println("Atv: 4. Nível do nó 40: " + bst.nivelDoNo(40));

        System.out.println();
        System.out.println("Atv: 5. Altura da árvore: " + bst.altura());

    }
}
