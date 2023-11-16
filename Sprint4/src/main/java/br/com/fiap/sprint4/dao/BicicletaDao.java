package br.com.fiap.sprint4.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import br.com.fiap.sprint4.entity.Bicicleta;
import br.com.fiap.sprint4.enums.TipoSeguroEnum;

public class BicicletaDao {

    private Connection conexao;

    public void inserir(Bicicleta bicicleta) throws SQLException {
        conexao = GerenciadorBD.obterConexao();
        PreparedStatement comandoSql = null;
        String sql = "insert into bike (idbike, marca,modelo,numero_de_Serie, valor_Nota, " +
                "valor_Atual, tipo_seguro, idcliente) values(?,?,?,?,?,?,?,?)";
        comandoSql = conexao.prepareStatement(sql);
        comandoSql.setInt(1,  bicicleta.getId());
        comandoSql.setString(2, bicicleta.getMarca());
        comandoSql.setString(3, bicicleta.getModelo());
        comandoSql.setString(4, bicicleta.getNumeroDeSerie());
        comandoSql.setDouble(5, bicicleta.getValorNota());
        comandoSql.setDouble(6, bicicleta.getValorAtual());
        TipoSeguroEnum tipoSeguro = bicicleta.getTipoSeguro();
        if (tipoSeguro.equals(TipoSeguroEnum.ELITE)){
            comandoSql.setString(7, "ELITE");
        }else if (tipoSeguro.equals(TipoSeguroEnum.ESSENCIAL)){
        	comandoSql.setString(7, "ESSENCIAL");
        }else {        
            comandoSql.setString(7, "LEVE");}
        comandoSql.setInt(8,bicicleta.getCliente().getId());
        comandoSql.executeUpdate();
        conexao.close();
        comandoSql.close();
    }

    public void alterar(Bicicleta bicicleta) throws SQLException {
        conexao = GerenciadorBD.obterConexao();
        PreparedStatement comandoSql = null;
        String sql = "update bike set marca = ?,modelo = ?, numero_de_Serie = ?, " +
                "valor_nota = ?,valor_atual = ?, tipo_seguro=? where idbike = ?";

            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setString(1, bicicleta.getMarca());
            comandoSql.setString(2, bicicleta.getModelo());
            comandoSql.setString(3, bicicleta.getNumeroDeSerie());
            comandoSql.setDouble(4, bicicleta.getValorNota());
            comandoSql.setDouble(5, bicicleta.getValorAtual());  
            TipoSeguroEnum tipoSeguro = bicicleta.getTipoSeguro();
            if (tipoSeguro.equals(TipoSeguroEnum.ELITE)){
                comandoSql.setString(6, "ELITE");
            }else if (tipoSeguro.equals(TipoSeguroEnum.ESSENCIAL)){
            	comandoSql.setString(6, "ESSENCIAL");
            }else {        
                comandoSql.setString(6, "LEVE");}
            comandoSql.setInt(7, bicicleta.getId());
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();

    }

    public Bicicleta buscar(int id)  {
        Bicicleta bicicleta = new Bicicleta();
        ClienteDao cDao = new ClienteDao();
        PreparedStatement comandoSql = null;

        String sql = "Select * from bike where idbike = ?";
        conexao = GerenciadorBD.obterConexao();

        try {
			comandoSql = conexao.prepareStatement(sql);
	        comandoSql.setInt(1, id);
	        ResultSet rs = comandoSql.executeQuery();
	        if(rs.next()){
	            bicicleta.setId(rs.getInt(1));
	            bicicleta.setMarca(rs.getString(2));
	            bicicleta.setModelo(rs.getString(3));
	            bicicleta.setNumeroDeSerie(rs.getString(4));
	            bicicleta.setValorNota(rs.getDouble(5));
	            bicicleta.setValorAtual(rs.getDouble(6));
	            String tipoSeguro = rs.getString(7);
	            TipoSeguroEnum tipoEnum = TipoSeguroEnum.valueOf(tipoSeguro);
	            bicicleta.setTipoSeguro(tipoEnum);
	            int idCliente = rs.getInt(8);
	            bicicleta.setCliente(cDao.buscarPorId(idCliente));
	        }
	        comandoSql.close();
	        conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        return bicicleta;
    }
    
	public void remover(int id) {
		conexao = GerenciadorBD.obterConexao();
		PreparedStatement comandoSQL = null;
		try {
			comandoSQL = conexao.prepareStatement("delete from bike where idbike = ?");
			comandoSQL.setInt(1, id);
			comandoSQL.executeUpdate();
			conexao.close();
			comandoSQL.close();		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public int buscarMaiorId(){
        int id = 0;
        try {
            conexao =  GerenciadorBD.obterConexao();
            PreparedStatement comandoSql = null;
            String sql = "select Max(idbike) from bike";
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

    public int buscarIdCliente(int idBicicleta) {
        int idCliente = -1; 

        PreparedStatement comandoSql = null;
        String sql = "SELECT idcliente FROM bike WHERE idbike = ?";

        conexao = GerenciadorBD.obterConexao();

        try {
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setInt(1, idBicicleta);
            ResultSet rs = comandoSql.executeQuery();

            if (rs.next()) {
                idCliente = rs.getInt(1);
            }

            comandoSql.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idCliente;
    }
}
