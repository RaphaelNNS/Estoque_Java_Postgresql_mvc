import dao.ClienteDaoList;
import dao.IClienteDao;
import domain.Cliente;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        IClienteDao dao = new ClienteDaoList();
        String op;
        do {
            exibirMenu();
            System.out.println("\nO que deseja fazer?");
            op = exibirMenu();
            switch (op) {
                case "1":{
                    telaCadastro(dao);
                    break;
                }
                case "2":{
                    telaExibirTodos(dao);
                    break;
                }
                case "3":{
                    telaBuscarClienteCpf(dao);
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

    public static String exibirMenu() {
        String[] options = {"Cadastrar", "Exibir todoss os clientes", "Buscar cliente por CPF"," Finalizar"};
        String op = String.valueOf(JOptionPane.showOptionDialog(null, "MENU", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, "1"));
        System.out.println("selecionou" + op);
        System.out.println("\n****Menu****");

        return op;
    }

    private static void telaCadastro(IClienteDao dao) throws Exception {
        System.out.println("Nome do cliente: ");
        String nome = "S";
        System.out.println("Endereco do cliente: ");
        String endereco = "S";
        System.out.println("Estado do cliente ");
        String estado ="S";
        System.out.println("Cidade do cliente: ");
        String cidade = "S";
        System.out.println("Telefone do cliente: ");
        String telefone = "S";
        System.out.println("CPF do cliente: ");
        String cpf = "S";
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

    private static void telaBuscarClienteCpf( IClienteDao dao) throws Exception {
        System.out.println("Digite o CPF: ");
        String codigo = "S";
        Cliente cliente = dao.buscar(codigo);
        if(cliente != null){
            telaCliente(dao, cliente);
            System.out.println(cliente);
        }else System.out.println("Nao ha clientes registrados com este CPF");
    }

    private static void telaCliente(IClienteDao dao, Cliente cliente) throws Exception {
        String op = null;
        do {
            exibirMenuCliente();
            System.out.println(cliente);
            op = "S";
            switch (op) {
                case "1":
                    telaModificarDados(dao, cliente);
                    break;
                case "2":
                    telaExcluirClientes(dao, cliente);
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Opção inválida");
            }

        }while (!op.equals("3"));
    }

    private static void telaExcluirClientes(IClienteDao dao, Cliente cliente) throws Exception {
        String op = null;
        System.out.println(("Tem certeza que quer exlcuir este cliente?").toUpperCase());
        System.out.println("[1 - SIM / 2 - NAO]");
        op = "S";
        if (op.equals("1")) {
            dao.excluir(cliente);
            return;
        }
        else System.out.println("EXCLUSAO CANCELADA");
    }

    private static void telaModificarDados(IClienteDao dao, Cliente cliente) throws Exception {
        String op;
        do {
            exibirMenuModificacao();
            op = "S";
            switch (op) {
                case "1":
                    System.out.println("Digite o nome do cliente: ");
                    cliente.setNome( "S");
                    break;
                case "2":
                    System.out.println("Digite o endereco do cliente: ");
                    cliente.setEndereco( "S");
                    break;
                case "3":
                    System.out.println("Digite a cidade do cliente: ");
                    cliente.setCidade("S");
                    break;
                case "4":
                    System.out.println("Digite o estado do cliente: ");
                    cliente.setEstado("S");
                    break;
                case "5":
                    System.out.println("Digite o telefone do cliente: ");
                    cliente.setTelefone("S");
                    break;
                case "6":
                    System.out.println("Digite o CPF do cliente: ");
                    cliente.setCpf("S");
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