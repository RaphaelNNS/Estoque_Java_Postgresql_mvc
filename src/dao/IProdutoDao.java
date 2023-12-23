package dao;

import domain.Cliente;
import domain.Produto;

import java.util.List;

public interface IProdutoDao {
    public Integer cadastrar(Produto produto) throws Exception;

    public Integer atualizar(Produto produto) throws Exception;

    public Cliente buscar(String codigo) throws Exception;

    public List<Cliente> buscarTodos() throws Exception;

    public Integer excluir(Produto produto) throws Exception;

    Integer quantidade() throws Exception;

}
