package com.stefanini.resource;

import java.time.LocalDateTime;
import java.util.Locale;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.model.Endereco;
import com.stefanini.model.Perfil;
import com.stefanini.model.Pessoa;
import com.stefanini.servico.PerfilServico;

@Path("perfil")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PerfilTestResource {
	
	@Inject
	private PerfilServico perfilServico = new PerfilServico();
	
	@GET
	public Response obterListaPessoa() {
		return Response.ok(perfilServico.getList().get()).build();
	}
	@POST
	@Transactional
	public Response salvar(Perfil perfil) {
		return Response.ok(perfilServico.salvar(perfil)).build();
	}
	
	@GET
	@Path("{id}")
	public Response obterEndereco(@PathParam("id") Long id) {
		return Response.ok(perfilServico.encontrar(id).get()).build();

	}
	@PATCH
	@Path("{id}")
	@Transactional
	public Response atualizarEndereco(Perfil perfil) {
		return Response.ok(perfilServico.atualizar(perfil)).build();
		//System.out.println(endereco);
	}
	
}
