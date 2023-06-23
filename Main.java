import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        Aeronave a = new Aeronave("RJ");
        loadSeatsFromFile(a);

        System.out.println("Venda de passagens");
        String command;
        do {
            command = in.nextLine();
            if (command.startsWith("show"))
                a.print();
            else if (command.startsWith("sell")){
                a.sell(command);
                Comprador c = new Comprador();
                String choice = in.nextLine();
                if(choice.equalsIgnoreCase("sim")){
                    a.block(command);
                    Assento.preço = Assento.preço + 100;
                    System.out.println("Assento ao lado foi bloqueado");
                    System.out.println("Leva bagagem?");
                }
                else if(choice.equalsIgnoreCase("nao")){
                    System.out.println("Assento ao lado não foi bloqueado");
                    System.out.println("Leva bagagem?");
                }
                choice = in.nextLine();
                if(choice.equalsIgnoreCase("sim")){
                    System.out.printf("O custo por bagagem é R$50,00%n%n");
                    Assento.preço = Assento.preço + 50;
                }
                else if(choice.equalsIgnoreCase("nao")){
                }
                System.out.print("digite 'nome' para receber nome ");
                choice = in.nextLine();
                if(choice.equalsIgnoreCase("nome")){
                    c.getNome();
                    System.out.println("O nome do comprador é: " + Comprador.nome);
                    System.out.println();
                }
                System.out.print("digite 'cpf' para receber cpf ");
                choice = in.nextLine();
                if(choice.equalsIgnoreCase("cpf")){
                    c.getCpf();
                    System.out.println("O cpf do comprador é: " + Comprador.cpf);
                    System.out.println();
                }
                System.out.print("digite 'total' para receber total da compra ");
                choice = in.nextLine();
                if(choice.equalsIgnoreCase("total")){
                    c.totalCompra();
                    System.out.println("O total da compra é: R$" + Assento.preço);
                    System.out.println();
                    saveBuyerInformationToFile(c);
                }
                a.saveSeatsToFile();
            }
            else if (command.startsWith("listCompradores")) {
                showBuyerInformation();
            }
            else if (command.startsWith("emptyAeronave")) {
                emptyAeronave(a);
            }
            else if (!command.startsWith("quit"))
                System.out.println("Comando inválido!");
        } while (!command.startsWith("quit"));

        saveSeatsToFile(a);
    }

    private static void loadSeatsFromFile(Aeronave aeronave) {
        try {
            File file = new File("assentos.txt");
            Scanner scanner = new Scanner(file);

            for (int i = 0; i < aeronave.getSeats().length; i++) {
                String line = scanner.nextLine();
                char[] data = line.toCharArray();

                for (int j = 0; j < aeronave.getSeats()[i].length; j++) {
                    int dataIndex = j * 2; // Índice correto no array de dados

                    aeronave.getSeats()[i][j].setOcupado(data[dataIndex] == '1');

                    // Verifica se o assento está bloqueado
                    if (data[dataIndex + 1] == '1') {
                        aeronave.getSeats()[i][j].setBloqueado(true);
                    }
                }
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de assentos.");
        }
    }

    private static void saveSeatsToFile(Aeronave aeronave) {
        try {
            File file = new File("assentos.txt");
            PrintWriter writer = new PrintWriter(file);

            for (int i = 0; i < aeronave.getSeats().length; i++) {
                for (int j = 0; j < aeronave.getSeats()[i].length; j++) {
                    Assento assento = aeronave.getSeats()[i][j];
                    char seatData = assento.getOcupado() ? '1' : '0';
                    char blockedData = assento.getBloqueado() ? '1' : '0';
                    writer.print(seatData);
                    writer.print(blockedData);
                }
                writer.println();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao gravar o arquivo de assentos.");
        }
    }




    private static void saveBuyerInformationToFile(Comprador c) throws IOException {
        File file = new File("compradores.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        String nome = Comprador.nome;
        String cpf = Comprador.cpf;
        double totalCompra = Assento.preço;
        writer.write(nome + "," + cpf + "," + totalCompra);
        writer.newLine();
        writer.close();
    }

    private static void showBuyerInformation() throws IOException {
        File file = new File("compradores.txt");
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            System.out.println("Lista de Compradores:");
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String nome = values[0];
                String cpf = values[1];
                double totalCompra = Double.parseDouble(values[2]);
                System.out.println("Nome: " + nome);
                System.out.println("CPF: " + cpf);
                System.out.println("Total da Compra: R$" + totalCompra);
                System.out.println();
            }
            reader.close();
        } else {
            System.out.println("Nenhum comprador registrado.");
        }
    }

    private static void emptyAeronave(Aeronave a) {
        for (int i = 0; i < a.getSeats().length; i++) {
            for (int j = 0; j < a.getSeats()[i].length; j++) {
                a.getSeats()[i][j].setOcupado(false);
                a.getSeats()[i][j].setBloqueado(false);
            }
        }
        File seatsFile = new File("assentos.txt");
        File buyersFile = new File("compradores.txt");
        if (seatsFile.exists()) {
            seatsFile.delete();
        }
        if (buyersFile.exists()) {
            buyersFile.delete();
        }
        System.out.println("Aeronave esvaziada. Todas as informações foram removidas.");
    }
}

