package Busca;

/** Busca elemento por elemento, mesmo quando o vetor não está ordenado. */
public class ExemploBuscaSequencial {
    /**
     * Retorna o primeiro índice encontrado ou -1 quando o valor não existe.
     * Tempo: O(n) no pior caso; espaço extra: O(1).
     */
    public static int buscaSequencial(int[] array, int valorProcurado) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == valorProcurado) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] numeros = {18, 7, 25, 3, 12, 30, 9};
        int valorProcurado = 12;

        int posicao = buscaSequencial(numeros, valorProcurado);
        if (posicao != -1) {
            System.out.println("Valor encontrado no indice "+posicao);
        } else {
            System.out.println("Valor não encontrado");
        }

    }
}
