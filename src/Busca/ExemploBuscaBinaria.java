package Busca;

/** Divide o intervalo de busca pela metade a cada comparação. */
public class ExemploBuscaBinaria {
    /**
     * O vetor deve estar ordenado em ordem crescente antes da chamada.
     * Retorna um índice do valor ou -1 se ele não existir.
     * Tempo: O(log n) no pior caso; espaço extra: O(1).
     */
    public static int buscaBinaria(int[] array, int valorProcurado) {
        int inicio = 0;
        int fim = array.length - 1;

        while (inicio <= fim) {
            // Esta forma evita somar dois índices potencialmente muito grandes.
            int meio = inicio + (fim - inicio) / 2;
            if (array[meio] == valorProcurado) {
                return meio;
            }
            if (array[meio] < valorProcurado) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] numeros = {3, 7, 9, 12, 18, 25, 30};
        int valorProcurado = 7;

        int posicao = buscaBinaria(numeros, valorProcurado);
        if (posicao != -1) {
            System.out.println("Valor encontrado no indice "+posicao);
        } else {
            System.out.println("Nenhum elemento encontrado");
        }
    }
}
