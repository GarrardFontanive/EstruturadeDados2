package Grafos;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

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

    public void buscaEmLargura(int origem) {
        if (!verticeValido(origem)) {
            System.out.println("Vertice Invalido");
            return;
        }

        boolean[] visitado = new boolean[numeroVertices];
        int[] dist = new int[numeroVertices];
        int[] pai = new int[numeroVertices];
        Queue<Integer> fila = new ArrayDeque<>();

        for (int i = 0; i < numeroVertices; i++) {
            dist[i] = -1;
            pai[i] = -1;
        }

        dist[origem] = 0;
        //O pai da origem permanece -1

        //Marcar o vértice como descoberto antes de colocá-lo na fila
        visitado[origem] = true;
        fila.add(origem);

        System.out.print("BFS a partir do vértice " + origem + ": ");
        while (!fila.isEmpty()) {
            int atual = fila.poll();
            System.out.print(atual + " ");

            //Examina as arestas que saem do Véertice atual.
            for(Aresta aresta: listaAdjacencia.get(atual)) {
                int vizinho = aresta.destino;
                if(!visitado[vizinho]) {
                    //Evitar que o mesmo vértice entre na fila novamente
                    visitado[vizinho] = true;

                    //A distância é medida em um número de arestas
                    dist[vizinho] = dist[atual] + 1;
                    pai[vizinho] = atual;

                    fila.add(vizinho);
                }
            }
        }
        System.out.println();
        System.out.println("Distâncias e pais: ");

        for(int i = 0; i < numeroVertices; i++) {
            System.out.println(
                    "Vértice" + i + ": dist = " + dist[i] + ", pai = " + pai[i]);
        }
    }

    public void buscarEmProfundidade(int origem) {
        if (!verticeValido(origem)) {
            System.out.println("Vertice Invalido");
            return;
        }

        boolean[] visitado = new boolean[numeroVertices];
        Deque<Integer> pilha = new ArrayDeque<>();
        pilha.push(origem);

        System.out.print("DFS a partir do vertice " + origem + ": ");
        while (!pilha.isEmpty()) {
            int atual = pilha.pop();

            if(!visitado[atual]) {
                visitado[atual] = true;
                System.out.print(atual + " ");

                ArrayList<Aresta> vizinhos = listaAdjacencia.get(atual);

                //Empilha em ordem inversa apara visitar o primeiro o início da lista
                for (int i = vizinhos.size() - 1; i >= 0; i--) {
                    int vizinho = vizinhos.get(i).destino;

                    if(!visitado[vizinho]) {
                        pilha.push(vizinho);
                    }
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListaAdjacencia grafo = new ListaAdjacencia(4, false);

        grafo.adicionarAresta(0, 1, 5);
        grafo.adicionarAresta(0, 2, 0);
        grafo.adicionarAresta(1, 2, 3);

        grafo.imprimirListaAdjacencia();

        System.out.println();
        grafo.removerAresta(0, 2);
        grafo.imprimirListaAdjacencia();

        ListaAdjacencia grafoBusca = new ListaAdjacencia(7, false);

        grafoBusca.adicionarAresta(0,1,1);
        grafoBusca.adicionarAresta(0,2,1);
        grafoBusca.adicionarAresta(1,3,1);
        grafoBusca.adicionarAresta(1,4,1);
        grafoBusca.adicionarAresta(2,4,1);
        grafoBusca.adicionarAresta(4,5,1);
        System.out.println();
        grafoBusca.buscaEmLargura(0);
        System.out.println();
        grafoBusca.buscarEmProfundidade(0);
    }
}