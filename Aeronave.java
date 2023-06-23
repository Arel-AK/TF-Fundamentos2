import java.io.IOException;
import java.util.Scanner;

/**
 * @author Ariel Villarroel
 * @author Bernardo Fioreze
 * @author Gustavo Munoz
 * 
 * 
 * A classe Aeronave representa contem todos os comandos de manipulação da aeronave
 * Também nessa classe há o metodo print() para fazer o display da matriz de assentos
 * 
 */
public class Aeronave {
    private String destino;
    private Assento[][] seats = new Assento[16][4];
    
    /**
    * Construtor da classe Aeronave
    * 
    * @param destino O destino da aeronave
    */
    public Aeronave(String destino) {
        this.destino = destino;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                Assento assento = new Assento();
                seats[i][j] = assento;
            }
        }
    }
    
    /**
     * Vender um assento com base no comando fornecido pelo usuário.
     * 
     * @param command O comando de venda do assento
     */
    public void sell(String command) {
        
        String choice = command.substring(5); // Extrai a escolha do comando
        char letter = choice.charAt(0); // Extrai a letra da escolha
    
        System.out.printf("escolheu %s%n", choice); // Imprime a escolha

        int number = Integer.parseInt(choice.substring(1));
        int line;
        int column;
        
        // Determina a coluna com base na letra escolhida
        switch (letter) {
            case 'A':
            case 'a':
                column = 0;
                break;
            case 'B':
            case 'b':
                column = 1;
                break;
            case 'C':
            case 'c':
                column = 2;
                break;
            case 'D':
            case 'd':
                column = 3;
                break;
            default:
                column = -1;
        }

        line = number - 1;
        
        // Verifica se o assento está ocupado ou bloqueado
        if (seats[line][column].getOcupado() || seats[line][column].getBloqueado())
            System.out.println("Assento OCUPADO!");
        else {
            seats[line][column].setOcupado(true); // Marca o assento como ocupado
            int next;
            // Determina a próxima coluna da fila
            if (column == 0 || column == 2) {
                next = column + 1;
            } else {
                next = column - 1;
            }
            // Verifica se o assento ao lado está desocupado ou não
            if (!seats[line][next].getOcupado()) {
                System.out.println("Deseja bloquear o assento ao lado por mais R$100,00? (sim/nao)");
            } else {
                System.out.println("O assento ao lado nao pode ser bloquado");
                System.out.println("Leva bagagem?");
            }

        }
    }
    
    /**
    * Bloqueia o assento adjacente com base no comando fornecido.
    *
    * @param command O comando de bloqueio do assento
    */
    public void block(String command) throws IOException {
        String choice = command.substring(5);
        
        char letter = choice.charAt(0);

        int number = Integer.parseInt(choice.substring(1));
        int line;
        int column;

        switch (letter) {
            case 'A':
            case 'a':
                column = 0;
                break;
            case 'B':
            case 'b':
                column = 1;
                break;
            case 'C':
            case 'c':
                column = 2;
                break;
            case 'D':
            case 'd':
                column = 3;
                break;
            default:
                column = -1;
        }

        line = number - 1;

        int next;
        if (column == 0 || column == 2) {
            next = column + 1;
        } else {
            next = column - 1;
        }
        saveSeatsToFile(); // Grava os dados do assento no disco
        // Verifica se o assento adjacente não está ocupado, e o bloqueia se não estiver
        if (!seats[line][next].getOcupado()) {
            seats[line][next].setBloqueado(true);
        }
    }
    
    /**
     * Imprime uma representação visual da aeronave, 
     * mostrando os assentos ocupados e bloqueados.
     */
    public void print() {
        System.out.printf("POA -> %s%n", destino);
        System.out.println("03/06/2023 6h TAM 3434\n");

        System.out.println("    A  B     C  D");

        // Percorre cada linha
        for (int i = 0; i < seats.length; i++) {
            // Mostra uma linha da matriz
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < seats[i].length; j++) {
                Assento assento = seats[i][j];
                if (assento.getOcupado()) {
                    System.out.print("[X]");
                } else if (assento.getBloqueado()) {
                    System.out.print("[B]");
                } else {
                    System.out.print("[ ]");
                }
                if (j == 1) {
                    System.out.print("   ");
                }
            }
            System.out.printf(" %2d%n", i + 1);
            if (i == 11 || i == 12) {
                System.out.println();
            }
        }
        System.out.println("    A  B     C  D");
    }
    
    /**
     * Metodo auxiliar para classe main
    */
    public Assento[][] getSeats() {
        return seats;
    }
    
    /**
     * Metodo auxiliar para classe main
    */
    public void saveSeatsToFile() {
    }

    /**
     * Obtém o destiono da aeronave
     * 
     * @return O destino
     */
    public String getDestino() {
        return destino;
    }
    
    /**
     * Metodo auxiliar para classe main
    */
    public void setSeats(Assento[][] seats) {
        this.seats = seats;
    }
}
