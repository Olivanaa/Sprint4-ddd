package br.com.fiap.sprint4.resource;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriBuilderException;
import javax.ws.rs.core.UriInfo;


import br.com.fiap.sprint4.bo.ClienteBo;
import br.com.fiap.sprint4.entity.Cliente;


@Path("/cliente")
public class ClienteResource {
	
	private ClienteBo cliBo;
	
	@POST
	@Path("/criarconta")	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrar(Cliente cliente, @Context UriInfo uriInfo) throws IllegalArgumentException, UriBuilderException, SQLException, ParseException {
		cliBo = new ClienteBo();
		if (cliBo.inserir(cliente)) {
			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			builder.path(Integer.toString((cliente.getId())));
			return Response.created(builder.build()).build();
		}
		else {
			return Response.status(Response.Status.BAD_REQUEST).entity("Você precisa ser maior de idade/Verfique seu CPF/Login já existente").build();
		}	
	}


	@GET
	@Path("/login")
	public Response validarUsuario(
	    @QueryParam("login") String login,
	    @QueryParam("senha") String senha) throws SQLException {

	    cliBo = new ClienteBo();
	    Boolean resp =  cliBo.validar(login, senha);
	    

	    if (resp) {
	    	int idCliente = cliBo.buscarPorId(login);
	        return Response.status(Response.Status.OK)
	            .entity("{\"IdCliente\": " + idCliente + "}")
	            .build();
	    } else {
	        return Response.status(Response.Status.UNAUTHORIZED)
	            .entity("Usuário inválido")
	            .build();
	    }
	}

	 
		@PUT
		@Path("/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response atualizar(Cliente cliente, 
							@PathParam("id") int id){
			cliente.setId(id);
			cliBo = new ClienteBo();
			try {
				cliBo.alterar(cliente);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Response.ok().build();
		}
		
		@GET
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Cliente buscarPorId(@PathParam("id")int id) throws SQLException {
			cliBo = new ClienteBo();
			return cliBo.buscar(id);
		}
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Cliente> buscar() {
			cliBo = new ClienteBo();
			return cliBo.buscarClientes();
		}
	

}
