package Grafos;
import java.util.ArrayList;

public class ListaAdjacencia {

    private class Aresta{
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

    public ListaAdjacencia(int numeroVertices, boolean direcionado) {
        this.numeroVertices = numeroVertices;
        this.direcionado = direcionado;
        this.listaAdjacencia = new ArrayList<>(numeroVertices);
        //Cria uma lisra vazia para cada vertice
        for (int i = 0; i < numeroVertices; i++) {
            listaAdjacencia.add(new ArrayList<>());
        }
    }

    private boolean verticeValido(int vertice) {
        return vertice >= 0 && vertice < numeroVertices;
    }
    public void adicionarAresta(int origem, int destino, int peso) {
        if(!verticeValido(origem) || !verticeValido(destino)) {
            System.out.println("Vertice Invalido");
            return;
        }
        adicionarNaLista(origem, destino, peso);
        if(!direcionado && origem != destino) {
            adicionarNaLista(destino, origem, peso);
        }
    }

    private void adicionarNaLista(int origem, int destino, int peso) {
        for(Aresta aresta: listaAdjacencia.get(origem)) {
            if (aresta.destino == destino) {
                //Se ja existe, atualiza o peso.
                aresta.peso = peso;
                return;
            }
        }
        listaAdjacencia.get(origem).add(new Aresta(destino, peso));
    }

    public void removerAresta(int origem, int destino) {
        if(!verticeValido(origem) || !verticeValido(destino)) {
            System.out.println("Vertice Invalido");
            return;
        }
        removerDaLista(origem, destino);
        if(!direcionado && origem != destino) {
            removerDaLista(destino, origem);
        }
    }

    private void removerDaLista(int origem, int destino) {
        ArrayList<Aresta> arestas = listaAdjacencia.get(origem);
        for(int i = 0; i < arestas.size(); i++) {
            if(arestas.get(i).destino == destino) {
                arestas.remove(i);
                return;
            }
        }
    }

    public void imprimirListaAdjacencia() {
        System.out.println("Lista Adjacencia");
        for(int i=0; i < numeroVertices; i++) {
            System.out.print(i + ": ");
            if(listaAdjacencia.get(i).isEmpty()) {
                System.out.print("Lista Vazia");
            } else {
                for(Aresta aresta: listaAdjacencia.get(i)) {
                    System.out.print(aresta.destino + " (peso " + aresta.peso + ") ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ListaAdjacencia grafo = new ListaAdjacencia(4, false);

        grafo.adicionarAresta(0, 1, 5);
        grafo.adicionarAresta(0, 2, 0);
        grafo.adicionarAresta(1, 2, 3);

        grafo.imprimirListaAdjacencia();

    }
}

