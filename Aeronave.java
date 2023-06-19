import java.util.Scanner;

public class Aeronave
{
    private String destino;
    private Assento[][] seats = new Assento[16][4];
    public Aeronave(String destino)
    {
        this.destino = destino;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {            
                Assento assento = new Assento();
                seats[i][j] = assento;
            }
        }
    }
    public static void sell(String command, Assento  [][] seats) {
        System.out.println(command);
        String choice = command.substring(5); 
        System.out.println(choice);
        
        char letter = choice.charAt(0);
        System.out.println(letter);
        
        int number = Integer.parseInt(choice.substring(1));        
        System.out.println(number);

        int line;
        int column;
        
        switch(letter) {
            case 'A' :
            case 'a' :
                        column = 0;
                        break;
            case 'B' :
            case 'b' :
                        column = 1;
                        break;
            case 'C' :
            case 'c' :
                        column = 2;
                        break;
            case 'D' :
            case 'd' :
                        column = 3;
                        break;
            default:
                        column = -1;
        }
        
        line = number - 1;
        if (seats[line][column].getOcupado())
            System.out.println("Assento OCUPADO!");
        else {
            seats [line][column].setOcupado(true);      
            int next;
            if (column == 0 || column == 2) {
                next = column + 1;
            } else {
                next = column - 1;
            }
            if (!seats[line][next].getOcupado()) {
                System.out.println("Deseja reservar o assento ao lado? (S/n)");
            }
        }
    }
    public void print() {
        System.out.printf("POA -> %s%n", destino);
        System.out.println("03/06/2023 6h TAM 3434\n");
        
        System.out.println("    A  B     C  D");      
        // percorre cada linha
        for (int i = 0; i < seats.length; i++) {
            // mostra uma linha matriz
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < seats[i].length; j++) {            
                if (seats[i][j].getOcupado())
                    System.out.print("[0]");
                else
                    System.out.print("[ ]");
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
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        Aeronave a = new Aeronave("RJ");
        //seats[2][2].setOcupado(true); // simula uma venda anterior
        
        System.out.println("Venda de passagens");
        String command;
        do {
            command = in.nextLine();
            if (command.startsWith("show"))
                a.print();
            //else if (command.startsWith("sell"))                
                //sell(command, seats);
            else
                System.out.println("Comando invalido!");
        } while (!command.startsWith("quit"));
    
    }
}