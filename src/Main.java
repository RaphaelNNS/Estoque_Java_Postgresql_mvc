import dao.ClienteDaoDB;
import dao.ClienteDaoList;
import dao.IClienteDao;
import domain.Cliente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void exibirMenu() {
        System.out.println("\n****Menu****");
        System.out.println("\n1- Cadastrar");
        System.out.println("\n2- Exibir todas as empresas");
        System.out.println("\n3- Buscar cliente por CPF");
        System.out.println("\n4- Finalizar\n");
    }

    public static void main(String[] args) throws Exception {
        IClienteDao dao = new ClienteDaoDB();
        Scanner sc = new Scanner(System.in);
        int op;
        do {
            exibirMenu();
            System.out.println("\nO que deseja fazer?");
            op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1:{
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
                    System.out.println("Telefone do cliente: ");
                    String cpf = sc.nextLine();
                    sc.nextLine();
                    dao.cadastrar(new Cliente(nome, endereco, estado, cidade, telefone, cpf));
                    break;
                }
                case 2:{
                    if (dao.length() > 0) {
                        System.out.println(dao.buscarTodos());
                        System.out.println("\n");
                    }
                    else {
                        System.out.println("\nNão há empresa cadastrada!");
                    }
                    break;
                }
                case 3:{
                    System.out.println("Digite o CPF: ");
                    String codigo = sc.nextLine();
                    sc.nextLine();
                    Cliente cliente = dao.buscar(codigo);
                    if(cliente != null){
                        System.out.println(cliente);
                    }else System.out.println("Nao ha clientes registrados com este CPF");
                    break;
                }
                case 4:{
                    System.out.println("\nEncerrando o programa...");
                    break;
                }
                default:{
                    System.out.println("\nOpção inválida!");
                    break;
                }
            }
        }while(op!=4);

    }

}