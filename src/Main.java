import dao.ClienteDaoDB;
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
                    break;
                }
                case "2":{
                    telaBuscarClienteCpf(dao);
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

    public static String telaEscolha(String message, String title, String[] options){
         return String.valueOf(JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, "1"));
    }

    private static void telaCadastro(IClienteDao dao){
        String nome = telaInput("Nome do cliente: ", null);
        String endereco = telaInput("Endereço do cliente: ", null);
        String estado = telaInput("Estado do cliente: ", null);
        String cidade = telaInput("Cidade do cliente: ", null);
        String telefone = telaInput("Telefone do cliente: ", null);
        String cpf = telaInput("CPF do cliente: ", null);
        try {
            dao.cadastrar(new Cliente(nome, endereco, estado, cidade, telefone, cpf));
        }catch (Exception e){
            telaErro("Erro ao cadastrar o cliente");
        }
    }

    private static String telaInput(String inputMessage, String initialString) {
        return String.valueOf(JOptionPane.showInputDialog(null, inputMessage, initialString));
    }

    private static void telaErro(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "erro", JOptionPane.ERROR_MESSAGE);
    }

    private static void telaExibirTodos(IClienteDao dao) {
        try {
            List<Cliente> listaDeClientes = new ArrayList<>();
            String listaFormatada = "";
            if (dao.length() > 0) {
                for (Cliente c: dao.buscarTodos()) {
                    listaFormatada = listaFormatada + c.toString();
                }
                JOptionPane.showMessageDialog(null, listaFormatada, "erro", JOptionPane.ERROR_MESSAGE);
            }
            else {
                telaErro("Não há clientes cadastrados");
            }
        } catch (Exception e) {
            telaErro("Erro ao exibir clientes");
        }

    }

    private static void telaBuscarClienteCpf( IClienteDao dao){
        try {
            String codigo = telaInput("Digite o CPF: ", null);
            Cliente cliente = dao.buscar(codigo);
            if(cliente != null){
                telaCliente(dao, cliente);
                System.out.println(cliente);
            }else telaErro("Nao ha clientes registrados com este CPF");
        }catch (Exception e){
            telaErro("Erro ao exibir cliente");
        }

    }

    private static void telaCliente(IClienteDao dao, Cliente cliente){
        String op = null;
        String[] option = {"modificar dados", "excluir cliente", "voltar"};
        do {
            try{
                op = telaEscolha(cliente.toString(), "Menu cliente", option);
                switch (op) {
                    case "0":
                        telaModificarDados(dao, cliente);
                        break;
                    case "1":
                        telaExcluirClientes(dao, cliente);
                        break;
                    case "2":
                        return;
                    default:
                        System.out.println("Opção inválida");
                }
            }catch (Exception e){
                telaErro("Erro em Menu de cliente");
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

    private static void telaModificarDados(IClienteDao dao, Cliente cliente){
        String op;
        String[] options = {"nome", "endereço", "cidade", "estado", "telefone", "CPF", "ATUALIZAR"};
        do {
            op = telaEscolha(null, "MENU ATUALIZAR", options);
            switch (op) {
                case "0":
                    cliente.setNome(telaInput("nome: ", cliente.getNome()));
                    break;
                case "1":
                    cliente.setEndereco(telaInput("endereco: ", cliente.getEndereco()));
                    break;
                case "2":
                    cliente.setCidade(telaInput("cidade: ", cliente.getCidade()));
                    break;
                case "3":
                    cliente.setEstado(telaInput("estado: ", cliente.getEstado()));
                    break;
                case "4":
                    cliente.setTelefone(telaInput("telefone: ", cliente.getTelefone()));
                    break;
                case "5":
                    cliente.setCpf(telaInput("CPF: ", cliente.getCpf()));
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null, "Os dados dos clientes foram atualizados!");
                    break;
                default:
                    telaErro("Opção invalida");
            }
        } while (!op.equals("6"));

        try {
            dao.atualizar(cliente);
        }catch (Exception e){
            telaErro("Erro ao atulizar");
        }
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