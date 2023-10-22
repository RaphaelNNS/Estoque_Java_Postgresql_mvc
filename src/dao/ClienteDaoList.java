package dao;

import domain.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ProdutodDaoList implements IProdutoDao {

    List<Cliente> clienteList = new ArrayList<>();

    @Override
    public void cadastrarProduto(Cliente cliente) {
        clienteList.add(cliente);
    }

    @Override
    public void atualizarProduto() {
        return;
    }

    @Override
    public Cliente buscarProduto() {
        return null;
    }

    @Override
    public List<Cliente> buscarTodos() {
        return clienteList;
    }

    @Override
    public void excluirPoduto() {

    }
}
