package br.com.fiap.sprint4.resource;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import javax.ws.rs.core.UriInfo;

import br.com.fiap.sprint4.bo.BicicletaBo;
import br.com.fiap.sprint4.entity.Bicicleta;
import br.com.fiap.sprint4.entity.Cliente;

@Path("/bicicleta")
public class BicicletaResource {
	
	private BicicletaBo bikeBo;
	
    public BicicletaResource() {
        bikeBo = new BicicletaBo();
    }

	
	   @POST
	    @Path("/cadastrar/{id}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response cadastrar(@PathParam("id") int idCliente, Bicicleta bike, @Context UriInfo uriInfo) {
	        try {
	            bikeBo.cadastrarBicicleta(idCliente, bike);
	            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	            builder.path(Integer.toString(bike.getId()));
	            return Response.created(builder.build()).entity(bike.getId()).build();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao cadastrar bicicleta").build();
	        }
	    }

	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Bicicleta bike,@PathParam("id") int id) throws SQLException {
		bikeBo = new BicicletaBo();
		bike.setId(id);		
		bikeBo.atualizar(bike);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response remover(@PathParam("id") int id) {
		bikeBo = new BicicletaBo();
		bikeBo.excluir(id);
		return Response.ok().build();
		
	}
}
