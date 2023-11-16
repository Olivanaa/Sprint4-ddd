package br.com.fiap.sprint4.resource;

import java.io.File;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.sprint4.bo.VistoriaBo;
import br.com.fiap.sprint4.entity.ReconhecimentoImagem;
import br.com.fiap.sprint4.entity.Vistoria;

@Path("/vistoria")
public class VistoriaResource {
	
	private VistoriaBo vistoriaBo;

//	@GET
//	@Path("/{id}")
//	public Vistoria cadastrar(@PathParam("id") int idBike, Vistoria vistoria, @Context UriInfo uriInfo) {
//		vistoriaBo = new VistoriaBo();
//		vistoriaBo.inserir(idBike, vistoria);		
//		return vistoriaBo.buscar(vistoria.getIdVistoria());
//		
//	}
//
//	
//	@Path("/api/vistoria/processar-imagem/{id}")
//	@POST
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response processarImagem(@PathParam("id") int idBike, @FormParam("imagem") InputStream imagemStream) {
//
//	    File imagem = salvarImagemTemporaria(imagemStream);
//
//	    ReconhecimentoImagem resultadoReconhecimento = new ReconhecimentoImagem(true, true, "Descrição simulada", "Descrição simulada", true);
//
//	   
//	    return Response.ok(resultadoReconhecimento).build();
//	}
//	
//
//	
//	private File salvarImagemTemporaria(InputStream imagemStream) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@GET
//	@Path("/{id}")
//	public Vistoria buscarPorId(@PathParam("id")int id) {
//		vistoriaBo = new VistoriaBo();
//		return vistoriaBo.buscar(id);
//	}

}
