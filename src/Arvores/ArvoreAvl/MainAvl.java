package Arvores.ArvoreAvl;

/** Demonstra inserção, remoção, fatores de balanceamento e consultas da AVL. */
public class MainAvl {
    public static void main(String[] args) {
        ArvoreAvl avl = new ArvoreAvl();

        int[] valores = {30, 20, 40, 10, 25, 22, 50, 5, 35, 45, 60};
        for(int v: valores) {
            avl.inserirNo(v);
        }
        avl.mostrar();
        avl.remover(10);
        System.out.println();
        System.out.println("valor 10 removido");
        avl.mostrar();
        System.out.println();
        System.out.print("Quantidade de nós: ");
        avl.contarNos();
        System.out.println("FB = altura direita - altura esquerda");
        avl.mostrarFB();
        System.out.println("Valores no intervalo [5, 40]");
        avl.mostrarIntervalo(5,40);
        System.out.println();
        System.out.println("Parentesco");
        avl.mostrarParentesco(45);
    }
}
