package Services;

import dao.ClienteDaoDB;
import dao.IClienteDao;

import javax.swing.*;

public class ClientService {
    public static void main(String[] args) {
        String mensagem = "oioi";

        String entradaUsuario = JOptionPane.showInputDialog("Digite algo:", mensagem);

        if (entradaUsuario != null) {
            JOptionPane.showMessageDialog(null, "Você digitou: " + entradaUsuario);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum texto foi inserido.");
        }
    }
    private static IClienteDao clienteDao;

}
