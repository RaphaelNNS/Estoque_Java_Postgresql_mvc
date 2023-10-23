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


    @Override
    public void cadastrarProduto(Cliente cliente) throws SQLException {
        
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
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM tb_clientes");
            String sql = sb.toString();
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
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setEndereco(endereco);
                cliente.setCidade(cidade);
                cliente.setEstado(estado);
                cliente.setTelefone(telefone);
                clienteList.add(cliente);
            }
            return clienteList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, result);
        }
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
    public void excluirPoduto(Integer indexOfElement) {

    }

    @Override
    public Cliente buscarCliente(Integer indexOfElement) {
        return null;
    }

    @Override
    public Integer length() {
        return 1;
    }
}
