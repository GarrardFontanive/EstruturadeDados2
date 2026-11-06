package Busca;

import java.util.Arrays;

/** Compara a busca implementada na aula com a oferecida pela biblioteca Java. */
public class ExemploArrays {
    public static void main(String[] args) {
        // Arrays.binarySearch também exige um vetor ordenado.
        int[] numeros = {3, 7, 9, 12, 18, 25, 30};

        int posicao = Arrays.binarySearch(numeros, 20);
        System.out.println("Resultado: " + posicao);
        if (posicao < 0) {
            // Ausência: resultado = -(ponto de inserção) - 1, e não apenas -1.
            int pontoInsercao = -posicao - 1;
            System.out.println("Valor 20 ausente; ponto de inserção: " + pontoInsercao);
        }
    }
}
