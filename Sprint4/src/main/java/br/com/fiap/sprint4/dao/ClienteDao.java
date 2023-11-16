package br.com.fiap.sprint4.dao;


import java.sql.*;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import br.com.fiap.sprint4.entity.Cliente;

public class ClienteDao {
	
    private Connection conexao;

    public void inserir(Cliente cliente) {
        conexao = GerenciadorBD.obterConexao();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("insert into clientebike(idCliente, nome, " +
                    "sobrenome, cpf, genero, dtaNasc, cep, rua, numero, complemento, bairro, cidade, estado, telefone, email, login, senha) " +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            comandoSql.setInt(1, cliente.getId());
            comandoSql.setString(2, cliente.getNome());
            comandoSql.setString(3, cliente.getSobrenome());
            comandoSql.setString(4, cliente.getCpf());
            comandoSql.setString(5, cliente.getGenero());
            comandoSql.setDate(6,Date.valueOf(cliente.validaData(cliente.getDtaNasc())));
            comandoSql.setString(7, cliente.getCep());
            comandoSql.setString(8, cliente.getLogradouro());
            comandoSql.setString(9, cliente.getNumero());
            comandoSql.setString(10, cliente.getComplemento());
            comandoSql.setString(11, cliente.getBairro());
            comandoSql.setString(12, cliente.getLocalidade());
            comandoSql.setString(13, cliente.getUf());
            comandoSql.setString(14, cliente.getTelefone());
            comandoSql.setString(15, cliente.getEmail());
            comandoSql.setString(16, cliente.getLogin());
            String senhaCripto = Cliente.senhaCripto(cliente.getSenha());
            comandoSql.setString(17, senhaCripto);
            
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (DateTimeParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        

    }
    
    public boolean verificaLogin(String login) {
		conexao = GerenciadorBD.obterConexao();
		PreparedStatement comandoSQL = null;
		try {
			String sql = "select * from clientebike where login = ? ";
			comandoSQL = conexao.prepareStatement(sql);
			comandoSQL.setString(1, login);
			ResultSet rs =  comandoSQL.executeQuery();
			if(rs.next()) {
				return true;
			}		

			conexao.close();
			comandoSQL.close();
 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        
		return false;
    }
    

    public void alterar(Cliente cliente){
        conexao = GerenciadorBD.obterConexao();
        PreparedStatement comandoSql = null;
        try {
            String sql = "update clientebike set nome = ?, sobrenome = ?," +
                    "cep = ?, rua = ?,numero = ?,complemento = ?,bairro = ?,cidade = ?,estado = ?,telefone = ?, email=?" +
                    "where idcliente = ?";

            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setString(1, cliente.getNome());
            comandoSql.setString(2, cliente.getSobrenome());
            comandoSql.setString(3, cliente.getCep());
            comandoSql.setString(4, cliente.getLogradouro());
            comandoSql.setString(5, cliente.getNumero());
            comandoSql.setString(6, cliente.getComplemento());
            comandoSql.setString(7, cliente.getBairro());
            comandoSql.setString(8, cliente.getLocalidade());
            comandoSql.setString(9, cliente.getUf());
            comandoSql.setString(10, cliente.getTelefone());
            comandoSql.setString(11, cliente.getEmail());
            comandoSql.setInt(12, cliente.getId());
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

    public Cliente buscar(int id) throws SQLException{
        Cliente cliente = new Cliente();
        PreparedStatement comandosql = null;

        String sql = "select * from clientebike where idcliente = ?";
        conexao = GerenciadorBD.obterConexao();
        comandosql = conexao.prepareStatement(sql);
        comandosql.setInt(1,id);
        ResultSet rs = comandosql.executeQuery();
        if(rs.next()){
            cliente.setId(rs.getInt(1));
            cliente.setNome(rs.getString(2));
            cliente.setSobrenome(rs.getString(3));
            cliente.setCpf(rs.getString(4));
            cliente.setGenero(rs.getString(5));
            cliente.setDtaNasc(rs.getString(6));            
            cliente.setCep(rs.getString(7));
            cliente.setLogradouro(rs.getString(8));
            cliente.setNumero(rs.getNString(9));
            cliente.setComplemento(rs.getString(10));
            cliente.setBairro(rs.getString(11));
            cliente.setLocalidade(rs.getString(12));
            cliente.setUf(rs.getString(13));
            cliente.setTelefone(rs.getString(14));
            cliente.setEmail(rs.getString(15));
            comandosql.close();
            conexao.close();


        }
        return cliente;
    }


    public Cliente buscarPorId(int idCliente) throws SQLException {
        Cliente cliente = new Cliente();
        PreparedStatement comandosql = null;

        String sql = "select * from clientebike where idCliente = ?";
        conexao = GerenciadorBD.obterConexao();
        comandosql = conexao.prepareStatement(sql);
        comandosql.setInt(1,idCliente);
        ResultSet rs = comandosql.executeQuery();
        if(rs.next()){
            cliente.setId(rs.getInt(1));
            cliente.setNome(rs.getString(2));
            cliente.setSobrenome(rs.getString(3));
            cliente.setCpf(rs.getString(4));
            cliente.setGenero(rs.getString(5));
            cliente.setDtaNasc(rs.getString(6));
            cliente.setCep(rs.getString(7));
            cliente.setLogradouro(rs.getString(8));
            cliente.setNumero(rs.getNString(9));
            cliente.setComplemento(rs.getString(10));
            cliente.setBairro(rs.getString(11));
            cliente.setLocalidade(rs.getString(12));
            cliente.setUf(rs.getString(13));
            cliente.setTelefone(rs.getString(14));
            cliente.setEmail(rs.getString(15));
            cliente.setLogin(rs.getString(16));
            cliente.setSenha(rs.getString(17));
            comandosql.close();
            conexao.close();


        }
        return cliente;
    }

    

	
	public boolean validarUsuario(String login, String senha){
		Cliente cliente = new Cliente();
		conexao = GerenciadorBD.obterConexao();
		PreparedStatement comandoSQL = null;
		try {
			comandoSQL = conexao.prepareStatement("select * from clientebike where login = ? ");
			comandoSQL.setString(1, login);
			ResultSet rs =  comandoSQL.executeQuery();
			if (rs.next())
			{
				if(cliente.verificaSenha(senha, rs.getString(17)))
				return true;
			}
			conexao.close();
			comandoSQL.close();
 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}  





	public int buscarIdLogin(String login) throws SQLException {
        Cliente cliente = new Cliente();
        PreparedStatement comandosql = null;

        String sql = "select idcliente from clientebike where login = ?";
        conexao = GerenciadorBD.obterConexao();
        comandosql = conexao.prepareStatement(sql);
        comandosql.setString(1,login);
        ResultSet rs = comandosql.executeQuery();
        if(rs.next()){
            cliente.setId(rs.getInt(1));
            comandosql.close();
            conexao.close();
        }
        return cliente.getId();

    }
	
	public String buscarLoginId(int id) throws SQLException {
        Cliente cliente = new Cliente();
        PreparedStatement comandosql = null;
        
        try {
            String sql = "select login from clientebike where idcliente = ?";
            conexao = GerenciadorBD.obterConexao();
            comandosql = conexao.prepareStatement(sql);
            comandosql.setInt(1,id);
            ResultSet rs = comandosql.executeQuery();
            if(rs.next()){
                cliente.setId(rs.getInt(1));
                comandosql.close();
                conexao.close();
            }
        	
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return cliente.getLogin();

    }
	
    public int buscarMaiorId(){
        int id = 0;
        try {
            conexao =  GerenciadorBD.obterConexao();
            PreparedStatement comandoSql = null;
            String sql = "select Max(idcliente) from clientebike";
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
    
    public ArrayList<Cliente> buscarTodos(){
    	
    	ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
    	conexao = GerenciadorBD.obterConexao();
		PreparedStatement comandoSql = null;
		try {
			  String sql = "select * from clientebike";
	          comandoSql = conexao.prepareStatement(sql);
	          ResultSet rs = comandoSql.executeQuery();
	          while (rs.next()) {
	        	  Cliente cliente = new Cliente();
	              cliente.setId(rs.getInt(1));
	              cliente.setNome(rs.getString(2));
	              cliente.setSobrenome(rs.getString(3));
	              cliente.setCpf(rs.getString(4));
	              cliente.setGenero(rs.getString(5));
	              cliente.setDtaNasc(rs.getString(6));
	              cliente.setCep(rs.getString(7));
	              cliente.setLogradouro(rs.getString(8));
	              cliente.setNumero(rs.getNString(9));
	              cliente.setComplemento(rs.getString(10));
	              cliente.setBairro(rs.getString(11));
	              cliente.setLocalidade(rs.getString(12));
	              cliente.setUf(rs.getString(13));
	              cliente.setTelefone(rs.getString(14));
	              cliente.setEmail(rs.getString(15));
	              cliente.setLogin(rs.getString(16));
	              cliente.setSenha(rs.getString(17));
	              listaClientes.add(cliente);      	  
	        	  
	    }
	          conexao.close();
	          comandoSql.close();
	        	  
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listaClientes;
    	
    }
}