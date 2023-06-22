public class Assento
{ 
    public static double preço = 150.00;
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
    public boolean getBloqueado()
    {
        return bloqueado;
    }
    public void setBloqueado(boolean bloqueado)
    {
        this.bloqueado = bloqueado;
    }
}