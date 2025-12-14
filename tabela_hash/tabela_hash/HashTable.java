import java.util.LinkedList;

public class HashTable {

    // Classe para armazenar pares chave-valor
    static class Entrada {
        String chave;
        String valor;

        Entrada(String chave, String valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }

    private int tamanho;
    private LinkedList<Entrada>[] tabela;

    @SuppressWarnings("unchecked")
    public HashTable(int tamanho) {
        this.tamanho = tamanho;
        tabela = new LinkedList[tamanho];

        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    // Função hash
    private int hash(String chave) {
        return Math.abs(chave.hashCode() % tamanho);
    }

    // Inserir elemento
    public void inserir(String chave, String valor) {
        int indice = hash(chave);
        tabela[indice].add(new Entrada(chave, valor));
    }

    // Buscar elemento
    public String buscar(String chave) {
        int indice = hash(chave);
        for (Entrada e : tabela[indice]) {
            if (e.chave.equals(chave)) {
                return e.valor;
            }
        }
        return null;
    }

    // Método principal para teste
    public static void main(String[] args) {
        HashTable tabela = new HashTable(5);

        tabela.inserir("Aluno1", "Levi");
        tabela.inserir("Aluno2", "João");
        tabela.inserir("Aluno3", "Maria");

        System.out.println("Busca Aluno1: " + tabela.buscar("Aluno1"));
        System.out.println("Busca Aluno3: " + tabela.buscar("Aluno3"));
    }
}
