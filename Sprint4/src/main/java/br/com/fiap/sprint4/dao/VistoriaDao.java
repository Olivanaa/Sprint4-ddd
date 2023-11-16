package br.com.fiap.sprint4.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.fiap.sprint4.entity.Bicicleta;
import br.com.fiap.sprint4.entity.Vistoria;
import br.com.fiap.sprint4.enums.TipoStatusEnum;

public class VistoriaDao {
    private Connection conexao;

  
	public void inserir(Vistoria vistoria) throws SQLException {
        conexao = GerenciadorBD.obterConexao();
        PreparedStatement comandoSql = null;
        
        vistoria.FazerVistoria();
        
        String sql = "insert into vistoria (idvistoria, status, dta_vistoria, idbike" +
                "values(?,?,?,?)";
        comandoSql = conexao.prepareStatement(sql);
        comandoSql.setInt(1, vistoria.getIdVistoria());
        String status = vistoria.getStatus() == TipoStatusEnum.APROVADO ? "APROVADO" : "REPROVADO";
        comandoSql.setString(2, status);
        comandoSql.setDate(3, Date.valueOf(LocalDate.now()));
        comandoSql.setInt(4, vistoria.getBike().getId());
        comandoSql.executeUpdate();
        conexao.close();
        comandoSql.close();
    }
    


	public Vistoria buscar(int id)  {
        Vistoria vistoria = new Vistoria();
        BicicletaDao bDao = new BicicletaDao();
        PreparedStatement comandoSql = null;

        String sql = "Select * from vistoria where idvistoria = ?";
        conexao = GerenciadorBD.obterConexao();

        try {
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setInt(1, id);
            ResultSet rs = comandoSql.executeQuery();
            if (rs.next()) {
                vistoria.setIdVistoria(rs.getInt(1));
                vistoria.setDataVistoria(rs.getDate(3).toLocalDate());
                String status = rs.getString(2);
                if ("APROVADO".equals(status)) {
                    vistoria.setStatus(TipoStatusEnum.APROVADO);
                } else {
                    vistoria.setStatus(TipoStatusEnum.REPROVADO);
                }}
            int idBike = rs.getInt(4);
            vistoria.setBike(bDao.buscar(idBike));

	        comandoSql.close();
	        conexao.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        return vistoria;
    }
    
    public int buscarMaiorId(){
        int id = 0;
        try {
            conexao =  GerenciadorBD.obterConexao();
            PreparedStatement comandoSql = null;
            String sql = "select Max(idvistoria) from vistoria";
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

}
