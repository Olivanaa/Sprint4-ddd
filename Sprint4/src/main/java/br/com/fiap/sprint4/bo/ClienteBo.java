package br.com.fiap.sprint4.bo;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import br.com.fiap.sprint4.dao.ClienteDao;

import br.com.fiap.sprint4.entity.Cliente;


public class ClienteBo {
	
	private ClienteDao dao;
	
	
	public boolean inserir(Cliente cliente) throws SQLException, ParseException {
		dao = new ClienteDao();
		LocalDate datan = cliente.validaData(cliente.getDtaNasc());
		if (cliente.validaCPF(cliente.getCpf()) && cliente.validaIdade(datan)) {
			if(cliente.verificaLogin(cliente.getLogin())) {
				return false;
			}
	        int id = dao.buscarMaiorId();
	        cliente.setId(id);
			dao.inserir(cliente);
			return true;
		}
		else {
			return false;
		}		
	}
	
	public boolean validar(String login, String senha) throws SQLException {
		dao = new ClienteDao();
		return dao.validarUsuario(login, senha);

	}
	
	public void alterar(Cliente cliente) throws SQLException {
		dao = new ClienteDao();
		dao.buscarIdLogin(cliente.getLogin());
		dao.alterar(cliente);

	}

	public int buscarPorId(String login) throws SQLException {
		dao = new ClienteDao();
		return dao.buscarIdLogin(login);
	}
	
	public Cliente buscar(int id) throws SQLException {
		dao = new ClienteDao();
		return dao.buscar(id);
	}
	
	public List<Cliente> buscarClientes(){
		dao = new ClienteDao();
		return dao.buscarTodos();
	}

}
