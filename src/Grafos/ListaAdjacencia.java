package Grafos;
import java.util.ArrayList;

public class ListaAdjacencia {

    private class Aresta {
        private int destino;
        private int peso;

        public Aresta(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    private ArrayList<ArrayList<Aresta>> listaAdjacencia;
    private int numeroVertices;
    private boolean direcionando;

    public ListaAdjacencia(int numeroVertices, boolean direcionando){}



    public static void main(String[] args) {
    }
}
