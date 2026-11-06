package Arvores.ArvoreRubroNegra;

/** Exibe valores ordenados, suas cores e buscas com e sem resultado. */
public class MainArvoreRubroNegra {
    public static void main(String[] args) {
        int [] valores = {50,30,70,20,40,60,80};
        ArvoreRubroNegra arvore = new ArvoreRubroNegra();
        for(int v : valores){
            arvore.inserir(v);
        }

        System.out.println("Percurso em ordem");
        arvore.mostrar();
        arvore.contarNosPorCor();
        System.out.println("Buscando 40: " + arvore.buscar(40));
        System.out.println("Buscando 99: " + arvore.buscar(99));
    }
}
