package br.com.fiap.sprint4.dao;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import br.com.fiap.sprint4.entity.Bicicleta;
import br.com.fiap.sprint4.entity.Seguro;
import br.com.fiap.sprint4.entity.Vistoria;
import br.com.fiap.sprint4.enums.TipoSeguroEnum;
import br.com.fiap.sprint4.enums.TipoStatusEnum;

public class SeguroDao {
    private Connection conexao;

    public void inserir(Seguro seguro) throws SQLException {
        conexao = GerenciadorBD.obterConexao();
        PreparedStatement comandoSql = null;
        String sql = "insert into seguro (idSeguro, nro_apolice, dta_vigencia" +
                "dta_contratacao, tipo_seguro, idcliente, idbike values(?,?,?,?,?,?,?,?)";
        comandoSql = conexao.prepareStatement(sql);
        comandoSql.setInt(1, seguro.getIdSeguro());
        comandoSql.setInt(2, seguro.getNroApolice());
        comandoSql.setDate(4, Date.valueOf(seguro.getDataVigencia()));
        comandoSql.setDate(5, Date.valueOf(seguro.getDataContratacao()));
        TipoSeguroEnum tipoSeguro = seguro.getTipoSeguro();
        if (tipoSeguro.equals(TipoSeguroEnum.ELITE)){
            comandoSql.setString(6, "ELITE");
        }else if (tipoSeguro.equals(TipoSeguroEnum.ESSENCIAL)){
        	comandoSql.setString(6, "ESSENCIAL");
        }else {        
            comandoSql.setString(6, "LEVE");}
        
        comandoSql.setInt(7, seguro.getCliente().getId());
        comandoSql.setInt(8, seguro.getBike().getId());
        comandoSql.executeUpdate();
        conexao.close();
        comandoSql.close();
    }
    
    public int buscarMaiorId(){
        int id = 0;
        try {
            conexao =  GerenciadorBD.obterConexao();
            PreparedStatement comandoSql = null;
            String sql = "select Max(idseguro) from seguro";
            comandoSql = conexao.prepareStatement(sql);
            ResultSet rs = comandoSql.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
            comandoSql.close();
            conexao.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return id + 1 ;
    }
    
    public Seguro buscar(int id)  {
        Seguro seguro = new Seguro();
        ClienteDao cDao = new ClienteDao();
        BicicletaDao bDao = new BicicletaDao();
        PreparedStatement comandoSql = null;

        String sql = "Select * from seguro where idseguro = ?";
        conexao = GerenciadorBD.obterConexao();

        try {
			comandoSql = conexao.prepareStatement(sql);
	        comandoSql.setInt(1, id);
	        ResultSet rs = comandoSql.executeQuery();
	        if(rs.next()){
	        	seguro.setIdSeguro(rs.getInt(1));
	        	seguro.setNroApolice(rs.getInt(2));
	        	seguro.setDataContratacao(rs.getDate(3).toLocalDate());
	        	seguro.setDataContratacao(rs.getDate(4).toLocalDate());
	        	String tipo = rs.getString(5);
	        	TipoSeguroEnum tipoEnum = TipoSeguroEnum.valueOf(tipo);
	        	seguro.setTipoSeguro(tipoEnum);
	            int idCliente = rs.getInt(8);
	            seguro.setCliente(cDao.buscarPorId(idCliente));
	            int idBike = rs.getInt(9);
	            seguro.setBike(bDao.buscar(idBike));
	        	

	        }
	        comandoSql.close();
	        conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        return seguro;
    }


}
