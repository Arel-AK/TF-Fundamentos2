import java.util.Scanner;
public class Comprador {
    public static String nome;
    public static String cpf;
    Scanner in = new Scanner(System.in);
    public Comprador() {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        System.out.println("Nome do Cliente:");
        nome = in.next();
        return nome;
    }

    public String getCpf() {
        System.out.println("CPF: ");
        cpf = in.next();
        return cpf;
    }
    public double totalCompra()
    {
        return Assento.preço;
    }
}