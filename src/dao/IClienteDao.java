package dao;

import domain.Cliente;

import java.util.List;

public interface IProdutoDao {
    void cadastrarProduto(Cliente cliente);
    void atualizarProduto();
    Cliente buscarProduto();
    List<Cliente> buscarTodos();
    void excluirPoduto();
}
