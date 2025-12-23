import java.util.LinkedList;
import java.util.Scanner;

public class MediaAlunoHash {

    // Classe interna que representa uma disciplina e sua nota
    static class Entrada {
        String disciplina;
        double nota;

        Entrada(String disciplina, double nota) {
            this.disciplina = disciplina;
            this.nota = nota;
        }
    }

    private int tamanho;
    private LinkedList<Entrada>[] tabela;

    // Construtor da Tabela Hash
    @SuppressWarnings("unchecked")
    public MediaAlunoHash(int tamanho) {
        this.tamanho = tamanho;
        tabela = new LinkedList[tamanho];

        // Inicialização das listas (encadeamento)
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    // Função hash
    private int hash(String chave) {
        return Math.abs(chave.hashCode() % tamanho);
    }

    // Inserir nota na tabela hash
    public void inserirNota(String disciplina, double nota) {
        int indice = hash(disciplina);
        tabela[indice].add(new Entrada(disciplina, nota));
    }

    // Calcular média das notas
    public double calcularMedia() {
        double soma = 0;
        int total = 0;

        for (LinkedList<Entrada> lista : tabela) {
            for (Entrada e : lista) {
                soma += e.nota;
                total++;
            }
        }

        return total > 0 ? soma / total : 0;
    }

    // Método principal
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MediaAlunoHash tabela = new MediaAlunoHash(5);

        System.out.print("Digite o nome do aluno: ");
        String aluno = scanner.nextLine();

        System.out.print("Quantas disciplinas deseja inserir? ");
        int qtd = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        for (int i = 1; i <= qtd; i++) {
            System.out.print("Nome da disciplina " + i + ": ");
            String disciplina = scanner.nextLine();

            System.out.print("Nota da disciplina " + i + ": ");
            double nota = scanner.nextDouble();
            scanner.nextLine();

            tabela.inserirNota(disciplina, nota);
        }

        double media = tabela.calcularMedia();

        System.out.println("\nAluno: " + aluno);
        System.out.println("Média final: " + media);

        if (media >= 10) {
            System.out.println("Situação: APROVADO");
        } else {
            System.out.println("Situação: REPROVADO");
        }

        scanner.close();
    }
}
