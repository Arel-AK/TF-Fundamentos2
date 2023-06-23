import java.io.*;
import java.util.Scanner;

/**
 *@author Ariel Villarroel
 *@author Bernardo Fioreze
 *@author Gustavo Munoz
 *@version 1.0
 *Classe Main
 */
public class Main {
    
    /**
     * Método principal, que inicia a execução do programa de venda de passagens de uma aeronave.
     * Salva mudanças feitas para o arquivo de texto da aeronave escolhida
     * 
     * @param args Os argumentos de linha de comando (não são utilizados neste programa)
     * @throws Exception Exceção lançada em caso de erros durante a execução do programa
     */
    public static void main(String[] args) throws Exception {
        // seleção de aeronave a ser manipulada
        Scanner in = new Scanner(System.in);
        Aeronave a1 = new Aeronave("RJ");
        Aeronave a2 = new Aeronave("SP");
        Aeronave a3 = new Aeronave("BH");
        // carrega os dados das aeronaves
        loadSeatsFromFile(a1);
        loadSeatsFromFile(a2);
        loadSeatsFromFile(a3);

        System.out.println("Selecione a aeronave:");
        System.out.println("1. Aeronave com destino ao Rio de Janeiro (RJ)");
        System.out.println("2. Aeronave com destino a São Paulo (SP)");
        System.out.println("3. Aeronave com destino a Belo Horizonte (BH)");

        int option = in.nextInt();
        in.nextLine(); // Limpar o buffer de entrada

        Aeronave selectedAeronave;
        switch (option) {
            case 1:
                selectedAeronave = a1;
                break;
            case 2:
                selectedAeronave = a2;
                break;
            case 3:
                selectedAeronave = a3;
                break;
            default:
                System.out.println("Opção inválida. Encerrando o programa.");
                return;
        }

        System.out.println("Venda de passagens");
        String command;
        // todo o processo de venda da passagem e comandos disponiveis estao a seguir
        do {
            command = in.nextLine();
            if (command.startsWith("show"))
                selectedAeronave.print();
            else if (command.startsWith("sell")) {
                selectedAeronave.sell(command);
                Comprador c = new Comprador();
                String choice = in.nextLine();
                if (choice.equalsIgnoreCase("sim")) {
                    selectedAeronave.block(command);
                    Assento.preço = Assento.preço + 100;
                    System.out.println("Assento ao lado foi bloqueado");
                    System.out.println("Leva bagagem?");
                } else if (choice.equalsIgnoreCase("nao")) {
                    System.out.println("Assento ao lado não foi bloqueado");
                    System.out.println("Leva bagagem?");
                }
                choice = in.nextLine();
                if (choice.equalsIgnoreCase("sim")) {
                    System.out.printf("O custo por bagagem é R$50,00%n%n");
                    Assento.preço = Assento.preço + 50;
                } else if (choice.equalsIgnoreCase("nao")) {
                }
                System.out.print("Digite 'nome' para receber o nome: ");
                choice = in.nextLine();
                if (choice.equalsIgnoreCase("nome")) {
                    System.out.println("O nome do comprador é: " + c.getNome());
                    System.out.println();
                }
                System.out.print("Digite 'cpf' para receber o cpf: ");
                choice = in.nextLine();
                if (choice.equalsIgnoreCase("cpf")) {
                    System.out.println("O CPF do comprador é: " + c.getCpf());
                    System.out.println();
                }
                System.out.print("Digite 'total' para receber o total da compra: ");
                choice = in.nextLine();
                if(choice.equalsIgnoreCase("total")){
                    c.totalCompra();
                    System.out.println("O total da compra é: R$" + Assento.preço);
                    System.out.println();
                    saveBuyerInformationToFile(c, selectedAeronave);
                }
                selectedAeronave.saveSeatsToFile();
            }
            else if (command.startsWith("listCompradores")) {
                showBuyerInformation(selectedAeronave);
            }
            else if (command.startsWith("emptyAeronave")) {
                emptyAeronave(selectedAeronave);
            }
            else if (!command.startsWith("quit"))
                System.out.println("Comando inválido!");

        } while (!command.equalsIgnoreCase("quit"));
        // salvar mudanças feitas as aeronaves após o quit
        saveSeatsToFile(a1);
        saveSeatsToFile(a2);
        saveSeatsToFile(a3);

        System.out.println("Programa encerrado.");
    }
    
    /**
     * 
     * Carrega do disco todos os dados dos assentos (ocupado/bloqueado) para o programa
     * O arquivo certo é escolhido puxando com o getDestino() a tag certa para preencher o .txt
     *
     */
    public static void loadSeatsFromFile(Aeronave aeronave) {
        try {
            File file = new File("assentos_" + aeronave.getDestino().toLowerCase() + ".txt");
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Assento[][] seats = (Assento[][]) ois.readObject();
                ois.close();
                fis.close();
                aeronave.setSeats(seats);
                System.out.println("Dados dos assentos carregados do arquivo.");
            } else {
                System.out.println("Arquivo de dados dos assentos não encontrado. Assentos vazios criados.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados dos assentos: " + e.getMessage());
        }
    }
    
    /**
     * 
     * Salva no disco todos os dados dos assentos (ocupado/bloqueado) para o programa
     * O arquivo certo é escolhido puxando com o getDestino() a tag certa para preencher o .txt
     *
     */
    public static void saveSeatsToFile(Aeronave aeronave) {
        try {
            File file = new File("assentos_" + aeronave.getDestino().toLowerCase() + ".txt");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(aeronave.getSeats());
            oos.close();
            fos.close();
            System.out.println("Dados dos assentos salvos em arquivo.");
        } catch (Exception e) {
            System.out.println("Erro ao salvar dados dos assentos: " + e.getMessage());
        }
    }

    /**
     * 
     * Salva no disco todos os dados dos compradores (nome, cpf e total da compra) para o programa
     * O arquivo certo é escolhido puxando com o getDestino() a tag certa para preencher o .txt
     *
     */
    private static void saveBuyerInformationToFile(Comprador c, Aeronave aeronave) throws IOException {
        File file = new File(aeronave.getDestino() + "_compradores.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        String nome = c.getNome();
        String cpf = c.getCpf();
        double totalCompra = Assento.preço;
        writer.write(nome + "," + cpf + "," + totalCompra);
        writer.newLine();
        writer.close();
    }
    
    /**
     * 
     * Puxa do disco todos os dados dos compradores (nome, cpf e total da compra) para o programa
     * O arquivo certo é escolhido puxando com o getDestino() a tag certa para preencher o .txt
     * As informações são exibidas caso o comando "listCompradores" seja usado
     */
    private static void showBuyerInformation(Aeronave aeronave) throws IOException {
        File file = new File(aeronave.getDestino() + "_compradores.txt");
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
    
    /**
     * 
     * Puxa do disco todos os dados dos compradores (nome, cpf e total da compra) 
     * Puxa do disco todos os dados dos assentos (ocupado/bloqueado)
     * Localiza o arquivo certo para cada um deles 
     * Ao usar o "emptyAeronave" ativa-se o .delete para remover todas as informações da aeronave escolhida
     * O arquivo certo é escolhido puxando com o getDestino() a tag certa para preencher o .txt
     * 
     */
    private static void emptyAeronave(Aeronave selectedAeronave) {
        for (int i = 0; i < selectedAeronave.getSeats().length; i++) {
            for (int j = 0; j < selectedAeronave.getSeats()[i].length; j++) {
                selectedAeronave.getSeats()[i][j].setOcupado(false);
                selectedAeronave.getSeats()[i][j].setBloqueado(false);
            }
        }
        File seatsFile = new File(selectedAeronave.getDestino() + "_seats.txt");
        File buyersFile = new File(selectedAeronave.getDestino() + "_compradores.txt");
        if (seatsFile.exists()) {
            seatsFile.delete();
        }
        if (buyersFile.exists()) {
            buyersFile.delete();
        }
        System.out.println("Aeronave esvaziada. Todas as informações foram removidas.");
    }
}
