import dao.ClienteDaoDB;
import dao.ClienteDaoList;
import dao.IClienteDao;
import domain.Cliente;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        IClienteDao dao = new ClienteDaoList();
        Scanner sc = new Scanner(System.in);
        String op;
        do {
            exibirMenu();
            System.out.println("\nO que deseja fazer?");
            op = sc.nextLine();
            sc.nextLine();
            switch (op) {
                case "1":{
                    telaCadastro(sc, dao);
                    break;
                }
                case "2":{
                    telaExibirTodos(dao);
                    break;
                }
                case "3":{
                    telaBuscarClienteCpf(sc, dao);
                    break;
                }
                case "4":{
                    System.out.println("\nEncerrando o programa...");
                    break;
                }
                default:{
                    System.out.println("\nOpção inválida!");
                    break;
                }
            }
        }while(!op.equals("4"));

    }

    public static void exibirMenu() {
        System.out.println("\n****Menu****");
        System.out.println("\n1- Cadastrar");
        System.out.println("\n2- Exibir todos os clientes");
        System.out.println("\n3- Buscar cliente por CPF");
        System.out.println("\n4- Finalizar\n");
    }

    private static void telaCadastro(Scanner sc, IClienteDao dao) throws Exception {
        System.out.println("Nome do cliente: ");
        String nome = sc.nextLine();
        sc.nextLine();
        System.out.println("Endereço do cliente: ");
        String endereco = sc.nextLine();
        sc.nextLine();
        System.out.println("Estado do cliente ");
        String estado = sc.nextLine();
        sc.nextLine();
        System.out.println("Cidade do cliente: ");
        String cidade = sc.nextLine();
        sc.nextLine();
        System.out.println("Telefone do cliente: ");
        String telefone = sc.nextLine();
        sc.nextLine();
        System.out.println("CPF do cliente: ");
        String cpf = sc.nextLine();
        sc.nextLine();
        dao.cadastrar(new Cliente(nome, endereco, estado, cidade, telefone, cpf));
    }

    private static void telaExibirTodos(IClienteDao dao) throws Exception {
        if (dao.length() > 0) {
            for (Cliente c: dao.buscarTodos()){
                System.out.println(c);
            }
            System.out.println("\n");
        }
        else {
            System.out.println("\nNão há clientes cadastrados!");
        }

    }

    private static void telaBuscarClienteCpf(Scanner sc, IClienteDao dao) throws Exception {
        System.out.println("Digite o CPF: ");
        String codigo = sc.nextLine();
        sc.nextLine();
        Cliente cliente = dao.buscar(codigo);
        if(cliente != null){
            telaCliente(sc, dao, cliente);
            System.out.println(cliente);
        }else System.out.println("Nao ha clientes registrados com este CPF");
    }

    private static void telaCliente(Scanner sc, IClienteDao dao, Cliente cliente) throws Exception {
        String op = null;
        do {
            exibirMenuCliente();
            System.out.println(cliente);
            op = sc.nextLine();
            sc.nextLine();
            switch (op) {
                case "1":
                    telaModificarDados(sc, dao, cliente);
                    break;
                case "2":
                    telaExcluirClientes(sc, dao, cliente);
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Opção inválida");
            }

        }while (!op.equals("3"));
    }

    private static void telaExcluirClientes(Scanner sc, IClienteDao dao, Cliente cliente) throws Exception {
        String op = null;
        System.out.println(("Tem certeza que quer exlcuir este cliente?").toUpperCase());
        System.out.println("[1 - SIM / 2 - NAO]");
        op = sc.nextLine();
        sc.nextLine();
        if (op.equals("1")) {
            dao.excluir(cliente);
            return;
        }
        else System.out.println("EXCLUSAO CANCELADA");
    }

    private static void telaModificarDados(Scanner sc, IClienteDao dao, Cliente cliente) throws Exception {
        String op;
        do {
            exibirMenuModificacao();
            op = sc.nextLine();
            sc.nextLine();
            switch (op) {
                case "1":
                    System.out.println("Digite o nome do cliente: ");
                    cliente.setNome(sc.nextLine());
                    sc.nextLine();
                    break;
                case "2":
                    System.out.println("Digite o endereco do cliente: ");
                    cliente.setEndereco(sc.nextLine());
                    sc.nextLine();
                    break;
                case "3":
                    System.out.println("Digite a cidade do cliente: ");
                    cliente.setCidade(sc.nextLine());
                    sc.nextLine();
                    break;
                case "4":
                    System.out.println("Digite o estado do cliente: ");
                    cliente.setEstado(sc.nextLine());
                    sc.nextLine();
                    break;
                case "5":
                    System.out.println("Digite o telefone do cliente: ");
                    cliente.setTelefone(sc.nextLine());
                    sc.nextLine();
                    break;
                case "6":
                    System.out.println("Digite o CPF do cliente: ");
                    cliente.setCpf(sc.nextLine());
                    sc.nextLine();
                    break;
                case "7":
                    System.out.println("Dados do cliente foram atualizados");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (!op.equals("7"));


        dao.atualizar(cliente);
    }

    private static void exibirMenuModificacao() {
        System.out.println("Qual atributo deseja modificar do cliente?");
        System.out.println("1 - nome\n2 - endereco\n3 - cidade\n4 - estado\n5 - telefone\n6 - CPF\n                  7 - salvar");
    }

    private static void exibirMenuCliente() {
        System.out.println("\n**** MenuCliente ****");
        System.out.println("\n1- Modificar dados");
        System.out.println("\n2- Excluir cliente");
        System.out.println("\n3- Voltar");
    }

}