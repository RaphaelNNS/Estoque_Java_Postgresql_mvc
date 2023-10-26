package dao;

import DB.ConnectionFactory;
import domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoDB implements IClienteDao {
    /*
    [ ]cadastrar produto
    [ ]get cadastrar query
    [X]buscar todos
    [X]buscar todos query
    [X]buscar produto
    [X]buscar produto query
    [ ]excluir produto
    [ ]excluir produto query
    [ ]atualizar produto
    [ ]atualizar produtos query
     */


    @Override
    public Integer cadastrar(Cliente cliente) throws SQLException {

        return null;
    }

    @Override
    public Integer atualizar(Cliente cliente) throws Exception {
        return null;
    }

    @Override
    public List<Cliente> buscarTodos() {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet result = null;
        List<Cliente> clienteList = new ArrayList<>();
        Cliente cliente = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSelectAll();
            stm = connection.prepareStatement(sql);
            result = stm.executeQuery();

            while (result.next()){
                cliente = new Cliente();

                long id = result.getLong("clienteid");
                String nome = result.getString("clientenome");
                String endereco = result.getString("clienteendereco");
                String cidade = result.getString("clientecidade");
                String estado = result.getString("clienteestado");
                String telefone = result.getString("clientetelefone");
                String cpf =  result.getString("clientecpf");

                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setEndereco(endereco);
                cliente.setCidade(cidade);
                cliente.setEstado(estado);
                cliente.setTelefone(telefone);
                cliente.setCpf(cpf);
                clienteList.add(cliente);
            }
            return clienteList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, result);
        }
    }

    private String getSelectAll(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_clientes");
        return sb.toString();
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

    @Override
    public Integer excluir(Cliente cliente) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlDelete();
            adicionarParametrosDelete(stm, cliente);
            stm = connection.prepareStatement(sql);
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    private void adicionarParametrosDelete(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getCpf());
    }

    private String getSqlDelete() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM tb_clientes ");
        sb.append("WHERE clientecpf = ?");
        return sb.toString();
    }

    @Override
    public Cliente buscar(String codigo) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet resultSet = null;
        Cliente cliente = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSelect();
            stm = connection.prepareStatement(sql);
            adicionarParametrosSelect(stm, codigo);
            resultSet = stm.executeQuery();


            if (resultSet.next()) {
                Long id = resultSet.getLong("clienteid");
                String nome = resultSet.getString("clientenome");
                String endereco = resultSet.getString("clienteendereco");
                String cidade = resultSet.getString("clientecidade");
                String estado = resultSet.getString("clienteestado");
                String telefone = resultSet.getString("clientetelefone");
                String cpf = resultSet.getString("clientecpf");

                cliente = new Cliente();
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setEndereco(endereco);
                cliente.setCidade(cidade);
                cliente.setEstado(estado);
                cliente.setTelefone(telefone);
                cliente.setCpf(cpf);
            }

            return cliente;

        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, stm, resultSet);
        }

    }

    private void adicionarParametrosSelect(PreparedStatement stm, String codigo) throws SQLException {
        stm.setString(1, codigo);
    }

    private String getSelect() {
        StringBuilder sb =  new StringBuilder();
        sb.append("SELECT * FROM tb_clientes ");
        sb.append("WHERE clientecpf = ?");
        return sb.toString();
    }

    @Override
    public Integer length() {
        return 1;
    }
}
