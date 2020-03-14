package com.stefanini.resource;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.stefanini.model.Endereco;
import com.stefanini.model.Pessoa;
import com.stefanini.servico.EnderecoServico;
import com.stefanini.servico.PessoaServico;

@Path("pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TesteResource {

	@Inject
	private PessoaServico pessoaServico;
	
	@Inject
	private EnderecoServico enderecoServico;
	

	@GET
	public Response obterListaPessoa() {
		return Response.ok(pessoaServico.getList().get()).build();
	}

	@POST
	public Response obterListaPessoa(@Valid Pessoa pessoa) {
		
		return Response.ok(pessoaServico.salvar(pessoa)).build();
	}
	

	@GET
	@Path("{id}")
	public Response obterPessoa(@PathParam("id") Long id) {
		//return Response.status(Status.INTERNAL_SERVER_ERROR).entity("deu ruim").build();
		return Response.ok(pessoaServico.encontrar(id).get()).build();
	}
	
	
	
	@PATCH
	@Path("{id}")
	@Transactional
	public Response updatePessoa(Pessoa pessoa) {
		System.out.println("aqui");
		System.out.println(pessoa);
		return Response.ok(pessoaServico.atualizar(pessoa)).build();
		
		
	}

	
	@Path("{id}")
	@DELETE
	public void deletaPessoa(@PathParam("id") Long id) {
		Optional<Pessoa> pessoa = pessoaServico.encontrar(id);
		Optional<Endereco> endereco = enderecoServico.encontrar(pessoa.get().getId());
		if(endereco.isPresent()) {
		
		
	}
	}
		
}
