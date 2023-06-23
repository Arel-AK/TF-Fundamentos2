import java.util.Scanner;
/**
 * 
 * @author Bernardo Fioreze
 * @author Gustavo Munoz
 * 
 * A classe Comprador representa um comprador de passagens de uma aeronave.
 */
public class Comprador {
    public static String nome;
    public static String cpf;
    Scanner in = new Scanner(System.in);
    
    /**
     * Construtor da classe Comprador.
     * 
     * @param nome O nome do comprador
     * @param cpf  O CPF do comprador
     */
    public Comprador() {
        this.nome = nome;
        this.cpf = cpf;
    }
    
    /**
     * Obtém o nome do comprador.
     * 
     * @return O nome do comprador
     */
    public String getNome() {
        System.out.print("Informe o nome");
        System.out.println();
        nome = in.next();
        return nome;
    }
    
    /**
     * Obtém o CPF do comprador.
     * 
     * @return O CPF do comprador
     */
    public String getCpf() {
        System.out.print("Informe o CPF");
        System.out.println();
        cpf = in.next();
        return cpf;
    }
    
    /**
     * Calcula o total da compra com base no preço do assento.
     * 
     * @return O total da compra
     */
    public double totalCompra()
    {
        return Assento.preço;
    }
}