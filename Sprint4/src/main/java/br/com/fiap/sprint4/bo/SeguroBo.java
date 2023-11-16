package br.com.fiap.sprint4.bo;

import java.sql.SQLException;

import br.com.fiap.sprint4.dao.SeguroDao;
import br.com.fiap.sprint4.dao.VistoriaDao;
import br.com.fiap.sprint4.entity.Bicicleta;
import br.com.fiap.sprint4.entity.Seguro;
import br.com.fiap.sprint4.entity.Vistoria;


public class SeguroBo {
	
	private SeguroDao dao;
	
	public void inserir(int idVistoria, Seguro seguro) {
		Vistoria vistoria = new Vistoria();
		dao = new SeguroDao();
		vistoria.setIdVistoria(idVistoria);
		seguro.setIdVistoria(dao.buscarMaiorId());		
		
		try {
			dao.inserir(seguro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public Seguro buscar(int id) {
		dao = new SeguroDao();
		return dao.buscar(id);
	}

}
