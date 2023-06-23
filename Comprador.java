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
        System.out.print("Informe o nome");
        System.out.println();
        nome = in.next();
        return nome;
    }

    public String getCpf() {
        System.out.print("Informe o CPF");
        System.out.println();
        cpf = in.next();
        return cpf;
    }
    public double totalCompra()
    {
        return Assento.preço;
    }
}