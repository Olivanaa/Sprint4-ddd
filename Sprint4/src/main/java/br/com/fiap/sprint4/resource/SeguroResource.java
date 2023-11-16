package br.com.fiap.sprint4.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.sprint4.bo.SeguroBo;
import br.com.fiap.sprint4.entity.Seguro;


@Path("/seguro")
public class SeguroResource {
	
	
	private SeguroBo seguroBo;

	@POST
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(@PathParam("id") int idVistoria, Seguro seguro, @Context UriInfo uriInfo) {
		seguroBo = new SeguroBo();
		seguroBo.inserir(idVistoria, seguro);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString((seguro.getIdSeguro())));
		return Response.created(builder.build()).build();
		
	}
	

	
	@GET
	@Path("/{id}")
	public Seguro buscarPorId(@PathParam("id")int id) {
		seguroBo = new SeguroBo();
		return seguroBo.buscar(id);
	}

}
