import dao.ClienteDaoDB;
import dao.ClienteDaoList;
import dao.IClienteDao;
import domain.Cliente;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        IClienteDao dao = new ClienteDaoDB();
        String op;
        do {
            op = exibirMenu();
            switch (op) {
                case "0":{
                    telaCadastro(dao);
                    break;
                }
                case "1":{
                    telaExibirTodos(dao);
                    System.out.println("tela exibir todos");
                    //telaExibirTodos(dao);
                    break;
                }
                case "2":{
                    System.out.println("tela buscar cliente por cpf");
                    //telaBuscarClienteCpf(dao);
                    break;
                }
                case "3":
                case "-1": {
                    System.out.println("\nEncerrando o programa...");
                    break;
                }
                default:{
                    System.out.println("\nCRIAR POP UP DE ERRO!");
                    break;
                }
            }
        }while(!op.equals("3") && !op.equals("-1"));
    }

    public static String exibirMenu() {
        String[] options = {"Cadastrar", "Exibir todos os clientes", "Buscar cliente por CPF"," Finalizar"};
        String op = String.valueOf(JOptionPane.showOptionDialog(null, "MENU", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, "1"));
        return op;
    }

    private static void telaCadastro(IClienteDao dao){
        String nome = TelaInput("Nome do cliente: ");
        String endereco = TelaInput("Endereço do cliente: ");
        String estado = TelaInput("Estado do cliente: ");
        String cidade = TelaInput("Cidade do cliente: ");
        String telefone = TelaInput("Telefone do cliente: ");
        String cpf = TelaInput("CPF do cliente: ");
        try {
            dao.cadastrar(new Cliente(nome, endereco, estado, cidade, telefone, cpf));
        }catch (Exception e){
            telaErro("Erro ao cadastrar o cliente");
        }
    }

    private static String TelaInput(String inputMessage) {
        return JOptionPane.showInputDialog(null, inputMessage);
    }

    private static void telaErro(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "erro", JOptionPane.ERROR_MESSAGE);
    }

    private static void telaExibirTodos(IClienteDao dao) {
        try {
            List<Cliente> listaDeClientes = new ArrayList<>();
            System.out.println(dao.length());
            if (1 > 0) {
                listaDeClientes.addAll(dao.buscarTodos());
                System.out.println("\n");
            }
            else {
                System.out.println("\nNão há clientes cadastrados!");
            }

            JOptionPane.showMessageDialog(null, listaDeClientes.toString(), "erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            telaErro("Erro ao exibir clientes");
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