package Grafos;

import java.util.Arrays;

/**
 * Grafo com vértices de 0 a numeroVertices - 1, representado por uma matriz.
 * Permite escolher direção e uso de pesos; infinito representa ausência de aresta.
 */
public class GrafoMatrizAdjacencia {
    private static final double SEM_ARESTA = Double.POSITIVE_INFINITY;

    private double[][] matriz;
    private final int numeroVertices;
    private final boolean direcionado;
    private final boolean ponderado;

    public GrafoMatrizAdjacencia(int numeroVertices,
                                 boolean direcionado,
                                 boolean ponderado) {
        this.numeroVertices = numeroVertices;
        this.direcionado = direcionado;
        this.ponderado = ponderado;
        this.matriz = new double[numeroVertices][numeroVertices];

        //Preencher cada linha com "sem aresta"
        for (int i = 0; i < numeroVertices; i++) {
            Arrays.fill(matriz[i], SEM_ARESTA);
        }
    }

    public int getNumeroVertices() {
        return numeroVertices;
    }

    public boolean verticeValido(int vertice) {
        if (vertice < 0 || vertice >= numeroVertices) {
            System.out.println("Vertice invalido " + vertice);
            return false;
        }
        return true;
    }

    public void adicionarAresta(int origem, int destino) {
        if (ponderado) {
            System.out.println("Grafo ponderado: informe o peso da aresta");
            return;
        }
        gravarAresta(origem, destino, 1);
    }

    public void adicionarAresta(int origem, int destino, double peso) {
        if (!ponderado) {
            System.out.println("Grafo não ponderado");
            return;
        }
        // Peso zero também representa uma aresta existente.
        gravarAresta(origem, destino, peso);
    }

    public void gravarAresta(int origem, int destino, double valor) {
        if (!verticeValido(origem) || !verticeValido(destino)) {
            return;
        }
        matriz[origem][destino] = valor;
        if (!direcionado) {
            matriz[destino][origem] = valor;
        }
    }

    public void removerAresta(int origem, int destino) {
        gravarAresta(origem, destino, SEM_ARESTA);
    }

    private boolean temAresta(int origem, int destino) {
        return matriz[origem][destino] != SEM_ARESTA;
    }

    private String formatarCelula(int i, int j) {
        if (!ponderado) {
            return temAresta(i, j) ? "1" : "0";
        }
        if (!temAresta(i, j)) {
            return "inf";
        }
        return Double.toString(matriz[i][j]);
    }

    public void imprimir() {
        System.out.println("Matriz Adjacencia (linha = origem, coluna = destino):");
        System.out.printf("%6s","");
        for (int j =0; j < numeroVertices; j++) {
            System.out.printf("%6s",j);
        }
        System.out.println();
        for (int i = 0; i < numeroVertices; i++) {
            System.out.printf("%6s",i);
            for (int j =0; j < numeroVertices; j++) {
                System.out.printf("%6s",formatarCelula(i, j));
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        GrafoMatrizAdjacencia gma1 = new GrafoMatrizAdjacencia(4, false, false);
        gma1.adicionarAresta(0,1);
        gma1.adicionarAresta(0,2);
        gma1.adicionarAresta(1,2);
        gma1.adicionarAresta(2,3);
        //gma1.mostarVizinho(2);
        gma1.imprimir();

        GrafoMatrizAdjacencia gma2 = new GrafoMatrizAdjacencia(4, true, true);
        gma2.adicionarAresta(0,1, 4);
        gma2.adicionarAresta(0,2, 1.5);
        gma2.adicionarAresta(2,1, 0);
        gma2.adicionarAresta(1,3, 2);
        //gma2.mostarVizinho(2);
        gma2.imprimir();

    }
}
