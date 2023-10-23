package dao;

import domain.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface IClienteDao {
    void cadastrarProduto(Cliente cliente) throws SQLException;
    List<Cliente> buscarTodos();
    void excluirPoduto(Integer indexOfElement);
    Cliente buscarCliente(Integer indexOfElement);
    Integer length();
}
