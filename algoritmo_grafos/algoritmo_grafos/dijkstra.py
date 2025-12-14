import java.util.*;

public class Dijkstra {

    // Classe auxiliar para armazenar vértice e distância
    static class No implements Comparable<No> {
        String vertice;
        int distancia;

        No(String vertice, int distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }

        @Override
        public int compareTo(No outro) {
            return Integer.compare(this.distancia, outro.distancia);
        }
    }

    // Implementação do algoritmo de Dijkstra
    public static Map<String, Integer> dijkstra(
            Map<String, Map<String, Integer>> grafo,
            String origem) {

        Map<String, Integer> distancias = new HashMap<>();
        PriorityQueue<No> fila = new PriorityQueue<>();

        // Inicialização
        for (String vertice : grafo.keySet()) {
            distancias.put(vertice, Integer.MAX_VALUE);
        }
        distancias.put(origem, 0);
        fila.add(new No(origem, 0));

        // Algoritmo
        while (!fila.isEmpty()) {
            No atual = fila.poll();

            for (Map.Entry<String, Integer> vizinho :
                    grafo.get(atual.vertice).entrySet()) {

                int novaDistancia = atual.distancia + vizinho.getValue();

                if (novaDistancia < distancias.get(vizinho.getKey())) {
                    distancias.put(vizinho.getKey(), novaDistancia);
                    fila.add(new No(vizinho.getKey(), novaDistancia));
                }
            }
        }

        return distancias;
    }

    // Método principal para teste
    public static void main(String[] args) {

        // Criação do grafo
        Map<String, Map<String, Integer>> grafo = new HashMap<>();

        grafo.put("A", Map.of("B", 5, "C", 7));
        grafo.put("B", Map.of("D", 3, "E", 10));
        grafo.put("C", Map.of("D", 2));
        grafo.put("D", Map.of("E", 4));
        grafo.put("E", Map.of());

        // Executar Dijkstra
        Map<String, Integer> distancias = dijkstra(grafo, "A");

        // Mostrar resultados
        System.out.println("Distâncias mínimas a partir do nó A:");
        for (Map.Entry<String, Integer> entrada : distancias.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue() + " km");
        }
    }
}
