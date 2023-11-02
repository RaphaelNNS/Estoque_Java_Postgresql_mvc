import dao.ClienteDaoDB;
import dao.IClienteDao;
import domain.Cliente;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
     private IClienteDao dao;

     @Test
     public void cadastrarTest() throws Exception {
          dao = new ClienteDaoDB();
          Cliente clienteAdicionado = new Cliente();

          clienteAdicionado.setNome("Marciel");
          clienteAdicionado.setCidade("Rio de janeiro");
          clienteAdicionado.setEndereco("rua asdasdadas");
          clienteAdicionado.setEstado("Cidade do Rio de janeiro");
          clienteAdicionado.setTelefone("310459983");
          clienteAdicionado.setCpf("21293259047");

          dao.cadastrar(clienteAdicionado);

          Cliente clienteResgatado = dao.buscar(clienteAdicionado.getCpf());
          assertNotNull(clienteResgatado);
          assertEquals(clienteAdicionado, clienteResgatado);

          Integer contDel = dao.excluir(clienteResgatado);
          assertEquals(1, (contDel));
     }

     @Test
     public void buscarTest() throws Exception {
          dao = new ClienteDaoDB();
          Cliente clienteAdicionado = new Cliente();

          clienteAdicionado.setNome("Marciel");
          clienteAdicionado.setCidade("Cidade Rio de janeiro");
          clienteAdicionado.setEndereco("rua asdasdadas");
          clienteAdicionado.setEstado("Rio de janeiro");
          clienteAdicionado.setTelefone("310459983");
          clienteAdicionado.setCpf("21293259047");

          dao.cadastrar(clienteAdicionado);

          Cliente clienteResgatado = dao.buscar(clienteAdicionado.getCpf());
          assertNotNull(clienteResgatado);
          assertEquals(clienteAdicionado.getNome(), clienteResgatado.getNome());
          assertEquals(clienteAdicionado.getCidade(), clienteResgatado.getCidade());
          assertEquals(clienteAdicionado.getEstado(), clienteResgatado.getEstado());
          assertEquals(clienteAdicionado.getTelefone(), clienteResgatado.getTelefone());
          assertEquals(clienteAdicionado.getCpf(), clienteResgatado.getCpf());

          Integer contDel = dao.excluir(clienteResgatado);
     }

     @Test
     public void excluirTest() throws Exception {
          dao = new ClienteDaoDB();
          Cliente clienteAdicionado = new Cliente();

          clienteAdicionado.setNome("Marciel");
          clienteAdicionado.setCidade("Cidade Rio de janeiro");
          clienteAdicionado.setEndereco("rua asdasdadas");
          clienteAdicionado.setEstado("do Rio de janeiro");
          clienteAdicionado.setTelefone("310459983");
          clienteAdicionado.setCpf("21293259047");

          dao.cadastrar(clienteAdicionado);

          Cliente clienteResgatado = dao.buscar(clienteAdicionado.getCpf());
          assertNotNull(clienteResgatado);

          Integer contDel = dao.excluir(clienteResgatado);
          assertEquals(1, (contDel));
     }

     @Test
     public void buscarTodosTest() throws Exception {
          dao = new ClienteDaoDB();
          Cliente clienteAdicionado1 = new Cliente();

          clienteAdicionado1.setNome("Marciel");
          clienteAdicionado1.setCidade("Rio de janeiro");
          clienteAdicionado1.setEndereco("rua asdasdadas");
          clienteAdicionado1.setEstado("Sao paulo");
          clienteAdicionado1.setTelefone("310459983");
          clienteAdicionado1.setCpf("21293259047");
          Integer countCad  = dao.cadastrar(clienteAdicionado1);

          Cliente clienteAdicionado2 = new Cliente();

          clienteAdicionado2.setNome("Joel");
          clienteAdicionado2.setCidade("São Paulo");
          clienteAdicionado2.setEndereco("endereco 2");
          clienteAdicionado2.setEstado("Cidade do Rio de janeiro");
          clienteAdicionado2.setTelefone("310459983");
          clienteAdicionado2.setCpf("21293259047");
          countCad  += dao.cadastrar(clienteAdicionado2);

          assertEquals(2, countCad);

          List<Cliente> list = dao.buscarTodos();
          assertNotNull(list);
          assertEquals(2, list.size());

          int countDel = 0;
          for (Cliente cli : list) {
               dao.excluir(cli);
               countDel++;
          }
          assertEquals(list.size(), countDel);

          list = dao.buscarTodos();
          assertEquals(list.size(), 0);

     }

     @Test
     public void atualizarTest() throws Exception {
          dao = new ClienteDaoDB();
          Cliente clienteAdicionado1 = new Cliente();

          clienteAdicionado1.setNome("Marciel");
          clienteAdicionado1.setCidade("Rio de janeiro");
          clienteAdicionado1.setEndereco("rua asdasdadas");
          clienteAdicionado1.setEstado("Sao paulo");
          clienteAdicionado1.setTelefone("310459983");
          clienteAdicionado1.setCpf("10");
          Integer countCad  = dao.cadastrar(clienteAdicionado1);
          assertTrue(countCad == 1);

          Cliente clienteBD = dao.buscar("10");
          assertNotNull(clienteBD);
          assertEquals(clienteBD.getCpf(), clienteBD.getCpf());
          assertEquals(clienteBD.getNome(), clienteBD.getNome());

          clienteBD.setCpf("20");
          clienteBD.setNome("Outro nome");
          Integer countUpdate = dao.atualizar(clienteBD);
          assertTrue(countUpdate == 1);

          Cliente clienteBD1 = dao.buscar("10");
          assertNull(clienteBD1);

          Cliente clienteBD2 = dao.buscar("20");
          assertNotNull(clienteBD2);
          assertEquals(clienteBD.getId(), clienteBD2.getId());
          assertEquals(clienteBD.getCpf(), clienteBD2.getCpf());
          assertEquals(clienteBD.getNome(), clienteBD2.getNome());

          List<Cliente> list = dao.buscarTodos();
          for (Cliente cli : list) {
               dao.excluir(cli);
          }
     }



}
