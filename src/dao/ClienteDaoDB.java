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
    private final String T_CLIENTES = "tb_clientes";
    private final String C_ID = "clienteid";
    private final String C_NOME = "clientenome";
    private final String C_ENDERECO = "clienteendereco";
    private final String C_CIDADE = "clientecidade";
    private final String C_ESTADO = "clienteestado";
    private final String C_TELEFONE = "clientetelefone";
    private final String C_CPF = "clientecpf";
    /*
    [X]cadastrar produto
    [X]get cadastrar query
    [X]buscar todos
    [X]buscar todos query
    [X]buscar produto
    [X]buscar produto query
    [X]excluir produto
    [X]excluir produto query
    [X]atualizar produto
    [X]atualizar produtos query
    [X]criar tela de atualizar ou excluir na pesquisa por cpf
    [X]criar tela de excluir
    [X]criar tela de atualizar
     */


    @Override
    public Integer cadastrar(Cliente cliente) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        String sql = getSqlCadastrar();
        PreparedStatement stm = connection.prepareStatement(sql);
        try {
            adicionarParametrosCadastrar(stm, cliente);
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(connection, stm, null);
        }


    }

    private String getSqlCadastrar() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO " + T_CLIENTES + " (" + C_NOME + ", "
            + C_ENDERECO + ", " + C_ESTADO + ", " + C_CIDADE + ", "
            + C_TELEFONE + ", " + C_CPF + ")");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?)");
        return sb.toString();
    }

    private void adicionarParametrosCadastrar(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getNome());
        stm.setString(2, cliente.getEndereco());
        stm.setString(3, cliente.getEstado());
        stm.setString(4, cliente.getCidade());
        stm.setString(5, cliente.getTelefone());
        stm.setString(6, cliente.getCpf());
    }

    private String getSqlUpdate() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE tb_clientes ");
        sb.append("SET " + C_NOME + " = ?, "
                + C_ENDERECO + " = ?, " + C_CIDADE + " = ?, "
                + C_ESTADO + " = ?, " + C_TELEFONE + " = ?, "
                + C_CPF + " = ? ");
        sb.append("WHERE " + C_ID + " = ?");
        return sb.toString();
    }

    @Override
    public Integer atualizar(Cliente cliente) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        String sql = getSqlAtualizar();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        try {
            adicionarParametrosAtualizar(preparedStatement, cliente);
            return preparedStatement.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
            closeConnection(connection, preparedStatement, null);
        }
    }

    private void adicionarParametrosAtualizar(PreparedStatement preparedStatement, Cliente cliente) throws SQLException {
        preparedStatement.setString(1, cliente.getNome());
        preparedStatement.setString(2, cliente.getEndereco());
        preparedStatement.setString(3, cliente.getCidade());
        preparedStatement.setString(4, cliente.getEstado());
        preparedStatement.setString(5, cliente.getTelefone());
        preparedStatement.setString(6, cliente.getCpf());
        preparedStatement.setLong(7, cliente.getId());
    }

    private String getSqlAtualizar() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE " + T_CLIENTES);
        sb.append(" SET " + C_NOME + " = ?, " + C_ENDERECO+ " = ?, " + C_CIDADE + " = ?, " + C_ESTADO + " = ?, " + C_TELEFONE+ " = ?, " + C_CPF+ " = ? " );
        sb.append("WHERE " + C_ID + " = ?");
        return sb.toString();
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

                long id = result.getLong(C_ID);
                String nome = result.getString(C_NOME);
                String endereco = result.getString(C_ENDERECO);
                String cidade = result.getString(C_CIDADE);
                String estado = result.getString(C_ESTADO);
                String telefone = result.getString(C_TELEFONE);
                String cpf =  result.getString(C_CPF);

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
            stm = connection.prepareStatement(sql);
            adicionarParametrosDelete(stm, cliente);
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
