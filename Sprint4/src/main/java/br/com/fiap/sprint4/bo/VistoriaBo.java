package br.com.fiap.sprint4.bo;

import java.sql.SQLException;

import br.com.fiap.sprint4.dao.VistoriaDao;
import br.com.fiap.sprint4.entity.Bicicleta;
import br.com.fiap.sprint4.entity.Vistoria;

public class VistoriaBo {
	
	private VistoriaDao dao;
	
	public void inserir(int idBike, Vistoria vistoria) {
		Bicicleta bike = new Bicicleta();
		dao = new VistoriaDao();
		bike.setId(idBike);
		vistoria.setBike(bike);
		
		vistoria.setIdVistoria(dao.buscarMaiorId());

		try {
			dao.inserir(vistoria);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Vistoria buscar(int id) {
		dao = new VistoriaDao();
		return dao.buscar(id);
	}


}
