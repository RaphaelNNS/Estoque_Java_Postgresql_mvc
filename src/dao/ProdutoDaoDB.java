package dao;

import DB.ConnectionFactory;
import domain.Cliente;
import domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProdutoDaoDB implements IProdutoDao{

    @Override
    public Integer cadastrar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String query = getSqlCadastrar();
            stm = connection.prepareStatement(query);
            adicionarParametrosCadastrar(stm, produto);
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    private void adicionarParametrosCadastrar(PreparedStatement stm, Produto produto) throws SQLException {
            stm.setString(1, produto.getCodigo());
            stm.setString(2, produto.getNome());
            stm.setString(3, produto.getDescricao());
            stm.setString(4, produto.getValor());
            stm.setString(5, produto.getValor());
            stm.setString(6, produto.getQuantidade());
    }

    private String getSqlCadastrar() {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO tb_produtos (codigo, nome, descricao, valor, quantidade) ");
        query.append("VALUES (?, ?, ?, ?, ?)");
        return query.toString();
    }



    @Override
    public Integer atualizar(Produto produto) throws Exception {
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
    public Integer excluir(Produto produto) throws Exception {
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
