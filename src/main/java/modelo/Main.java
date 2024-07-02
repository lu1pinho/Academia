package modelo;

public class Main {
    public static void main(String[] args) {
        // Nome, CPF, Nascimento, Email e Telefone
        Aluno11 aluno11 = new Aluno11("luis Gustavo", "043.776.101-04", "27/03/2005", "oluisgustavoalves@gmail.com", "6399211-9872", "Prime Mensal");

        StringGet(aluno11);
    }

    public static void StringGet(Aluno11 aluno11){
        String retorno = "Nome: " + aluno11.getNome()+ "\nCPF: " + aluno11.getCpf() + "\nNascimento: "+ aluno11.getCpf() + "\nEmail: " + aluno11.getEmail() + "\nFone: " + aluno11.getTelefone() + "\nPlano: " + aluno11.getPlano() + "\nData Inicio: "+ aluno11.getData_inicio() + "\nData Fim: "+ aluno11.getData_fim();
        System.out.println(retorno);
    }

}
