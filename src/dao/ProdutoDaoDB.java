package dao;

import DB.ConnectionFactory;
import domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProdutoDaoDB implements IProdutoDao{

    @Override
    public Integer cadastrar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String query = getSqlCadastrar();
            stm = connection.prepareStatement(query);
            adicionarParametrosCadastrar(stm);
            stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, stm, result);
        }
        return;
    }

    private void adicionarParametrosCadastrar(PreparedStatement stm) {

    }

    private String getSqlCadastrar() {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO tb_produtos (codigo, nome, descricao, valor, quantidade) ");
        query.append("VALUES (?, ?, ?, ?, ?)");
        return query.toString();
    }

    @Override
    public Integer atualizar(Cliente cliente) throws Exception {
        return null;
    }

    @Override
    public Cliente buscar(String codigo) throws Exception {
        return null;
    }

    @Override
    public List<Cliente> buscarTodos() throws Exception {
        return null;
    }

    @Override
    public Integer excluir(Cliente cliente) throws Exception {
        return null;
    }

    @Override
    public Integer quantidade() throws Exception {
        return null;
    }


    private void closeConnection(Connection connection, PreparedStatement stm, ResultSet result) {
        try{
            if( result != null && !result.isClosed()){
                result.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();

        }
    }
}
