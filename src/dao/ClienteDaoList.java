package dao;

import domain.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDaoList implements IClienteDao {

    List<Cliente> clienteList = new ArrayList<>();
    Integer idCount = 0;

    @Override
    public Integer cadastrar(Cliente cliente) {
        cliente.setId(idCount);
        clienteList.add(cliente);
        idCount++;
        return 1;
    }

    @Override
    public Integer atualizar(Cliente cliente) throws Exception {
        for (Cliente c : clienteList){
            if (c.getId() == cliente.getId()){
                c = cliente;
            }
        }
        return null;
    }




    @Override
    public List<Cliente> buscarTodos() {
        return clienteList;
    }

    @Override
    public Integer excluir(Cliente cliente) throws Exception {
        clienteList.remove(cliente);
        return 1;
    }


    @Override
    public Cliente buscar(String codigo) {
        Cliente cliente = null;
        Integer index = 0;
        for (Cliente c : clienteList){
            if(c.getCpf().equals(codigo)) return c;
        }
        return null;
    }

    @Override
    public Integer length() {

        return clienteList.size();
    }
}
