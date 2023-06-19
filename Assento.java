public class Assento
{ 
    private double preço;
    private boolean ocupado;
    private boolean bloqueado;
    private String comprador;
    public Assento()
    {
        ocupado = false;
        preço = 150.00;
    }
    public boolean getOcupado()
    {
        return ocupado;
    }
    public void setOcupado(boolean ocupado)
    {
        this.ocupado = ocupado;
    }
}