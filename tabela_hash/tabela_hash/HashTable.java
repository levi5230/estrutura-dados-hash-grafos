import java.util.LinkedList;

public class MediaAlunoHash {

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

    @SuppressWarnings("unchecked")
    public MediaAlunoHash(int tamanho) {
        this.tamanho = tamanho;
        tabela = new LinkedList[tamanho];

        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    private int hash(String disciplina) {
        return Math.abs(disciplina.hashCode() % tamanho);
    }

    public void inserirNota(String disciplina, double nota) {
        int indice = hash(disciplina);
        tabela[indice].add(new Entrada(disciplina, nota));
    }

    public double calcularMedia() {
        double soma = 0;
        int total = 0;

        for (LinkedList<Entrada> lista : tabela) {
            for (Entrada e : lista) {
                soma += e.nota;
                total++;
            }
        }
        return soma / total;
    }

    public void verificarSituacao() {
        double media = calcularMedia();
        System.out.println("Média final: " + media);

        if (media >= 10) {
            System.out.println("Situação: APROVADO");
        } else {
            System.out.println("Situação: REPROVADO");
        }
    }

    public static void main(String[] args) {
        MediaAlunoHash aluno = new MediaAlunoHash(5);

        aluno.inserirNota("Matemática", 12);
        aluno.inserirNota("Programação", 15);
        aluno.inserirNota("Estrutura de Dados", 14);
        aluno.inserirNota("Redes", 9);

        aluno.verificarSituacao();
    }
}
