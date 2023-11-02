import dao.ClienteDaoDB;
import dao.IClienteDao;
import domain.Cliente;
import org.junit.jupiter.api.Test;

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




}
