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
    private boolean direcionado;

    public ListaAdjacencia(int numeroVertices, boolean direcionando) {
        this.numeroVertices = numeroVertices;
        this.direcionado = direcionando;
        this.listaAdjacencia = new ArrayList<>(numeroVertices);

        //cria uma lista vazia para cada vértice
        for (int i = 0; i < numeroVertices; i++) {
            listaAdjacencia.add(new ArrayList<>());
        }
    }

    public void adicionarAresta(int origem, int destino, int peso) {
        if (!verticeValido(origem) || !verticeValido(destino)) {
            System.out.println("Vértice invalido");
            return;
        }

        adicionarNaLista(origem, destino, peso);
        if (direcionado && origem != destino) {
            adicionarNaLista(destino, origem, peso);
        }
    }

    private boolean verticeValido(int vertice) {
        return vertice >= 0 && vertice < numeroVertices;
    }

    private void adicionarNaLista(int origem, int destino, int peso) {
        for(Aresta aresta :listaAdjacencia.get(origem)) {
            if (aresta.destino == destino) {
                aresta.peso = peso;
                return;
            }
        }
        listaAdjacencia.get(origem).add(new Aresta(destino,peso));
    }

    public void removerAresta(int origem, int destino) {
        if (!verticeValido(origem) || !verticeValido(destino)) {
            System.out.println("Vértice invalido");
            return;
        }

        removerDaLista(origem, destino);
        if(!direcionado && origem != destino) {
            removerDaLista(destino, origem);
        }
    }

    private void removerDaLista(int origem, int destino) {
        ArrayList<Aresta> aresta = listaAdjacencia.get(origem);

        for (int i = 0; i < aresta.size(); i++) {
            if (aresta.get(i).destino == destino) {
                aresta.remove(i);
                return;
            }
        }
    }

    public void imprimirListaAdjacencia() {
        System.out.println("Lista Adjacencia");
        for (int i = 0; i < numeroVertices; i++) {
            System.out.print(i + ": ");
            if (listaAdjacencia.get(i).isEmpty()) {
                System.out.print("Lista vazia");
            } else {
                for (Aresta aresta : listaAdjacencia.get(i)) {
                    System.out.print(
                            aresta.destino + "(peso: " + aresta.peso + ")");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
    }
}
