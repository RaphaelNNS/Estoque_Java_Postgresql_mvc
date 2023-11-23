package Services;

import dao.ClienteDaoDB;
import dao.IClienteDao;

public class ClientService {
    private IClienteDao clienteDao;

    public int count(){
        return clienteDao.length();
    }
}
