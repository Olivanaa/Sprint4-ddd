package br.com.fiap.sprint4.bo;

import java.sql.SQLException;

import br.com.fiap.sprint4.dao.BicicletaDao;
import br.com.fiap.sprint4.dao.ClienteDao;
import br.com.fiap.sprint4.entity.Bicicleta;
import br.com.fiap.sprint4.entity.Cliente;

public class BicicletaBo {
	
	
    private BicicletaDao bikeDao;
    private ClienteDao clienteDao;

    public BicicletaBo() {
        bikeDao = new BicicletaDao();
        clienteDao = new ClienteDao();
    }

    public void cadastrarBicicleta(int idCliente, Bicicleta bike) throws SQLException {
//        Cliente cli = new Cliente();
//        cli.setId(idCliente);
    	Cliente cli = clienteDao.buscarPorId(idCliente);
        bike.setCliente(cli);

        bike.setId(bikeDao.buscarMaiorId());


        bikeDao.inserir(bike);
        System.out.println("Cadastro realizado com sucesso");
        System.out.println("ID Cliente: " + idCliente);
        System.out.println("Dados da Bicicleta: " + bike.toString());
    }
    
    public int buscarIdCliente(String login) throws SQLException {
    	return clienteDao.buscarIdLogin(login);
    }

	
	public Bicicleta buscar(int id) {
	
		return bikeDao.buscar(id);
	}
	
	public void atualizar(Bicicleta bike) throws SQLException {
	
		bikeDao.alterar(bike);
	}
	
	public void excluir(int id) {
		
		bikeDao.remover(id);
	}
}
