import java.io.Serializable;
/**
 * @author Ariel Villarroel
 * @author Bernardo Fioreze
 * 
 * A classe Assento representa um assento de uma aeronave para venda de passagens.
 * 
 * Implements Serializable para carregar os dados dos assentos corretamente
 */
public class Assento implements Serializable
{
    public static double preço = 150.00; // O preço padrão do assento
    private boolean ocupado; // Indica se o assento está ocupado
    private boolean bloqueado; // Indica se o assento está bloqueado
    private String comprador; // O nome do comprador do assento
    
    /**
     * Construtor da classe Assento.
     * Inicializa o assento como desocupado.
     */
    public Assento()
    {
        ocupado = false;
        preço = 150.00;
    }
    
    /**
     * Obtém o status de ocupação do assento.
     * 
     * @return true se o assento está ocupado, false caso contrário
     */
    public boolean getOcupado()
    {
        return ocupado;
    }
    
    /**
     * Define o status de ocupação do assento.
     * 
     * @param ocupado O status de ocupação do assento
     */
    public void setOcupado(boolean ocupado)
    {
        this.ocupado = ocupado;
    }
    
    /**
     * Obtém o status de bloqueio do assento.
     * 
     * @return true se o assento está bloqueado, false caso contrário
     */
    public boolean getBloqueado()
    {
        return bloqueado;
    }
    
    /**
     * Define o status de bloqueio do assento.
     * 
     * @param bloqueado O status de bloqueio do assento
     */
    public void setBloqueado(boolean bloqueado)
    {
        this.bloqueado = bloqueado;
    }
}
