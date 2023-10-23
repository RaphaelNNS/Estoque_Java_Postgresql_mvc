package dao;

import domain.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDaoList implements IClienteDao {

    List<Cliente> clienteList = new ArrayList<>();

    @Override
    public void cadastrarProduto(Cliente cliente) {
        clienteList.add(cliente);}


    @Override
    public List<Cliente> buscarTodos() {
        return clienteList;
    }

    @Override
    public void excluirPoduto(Integer indexOfElement) {
        clienteList.remove(indexOfElement);
    }

    @Override
    public Cliente buscarCliente(Integer indexOfElement) {

        return clienteList.get(indexOfElement);
    }

    @Override
    public Integer length() {

        return clienteList.size();
    }
}
