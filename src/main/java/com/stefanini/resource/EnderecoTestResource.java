package com.stefanini.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.stefanini.model.Endereco;
import com.stefanini.model.Pessoa;
import com.stefanini.servico.EnderecoServico;
import com.stefanini.servico.PessoaServico;


@Path("endereco")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class EnderecoTestResource {
	
	@Inject
	private EnderecoServico enderecoServico = new EnderecoServico();
	
	@Inject
	private PessoaServico pessoaServico = new PessoaServico();
	
	private SessionFactory factoryJpa;
	
	@GET
	public Response obterListaEndereco() {
		return Response.ok(enderecoServico.getList().get()).build();
	}
	
	@POST
	@Transactional
	public Response SalvarEndereco(Endereco endereco) {
		System.out.println(endereco);
		return Response.ok(enderecoServico.salvar(endereco)).build();
	}
	
	@GET
	@Path("{id}")
	public Response obterEndereco(@PathParam("id") Long id) {
		return Response.ok(enderecoServico.encontrar(id).get()).build();

	}
	
	@Path("{id}")
	@DELETE
	public void deletaEndereco(@PathParam("id") Long id) {
		enderecoServico.remover(id);
	}

	
	@PATCH
	@Path("{id}")
	@Transactional
	public Response atualizarEndereco(Endereco endereco) {
		return Response.ok(enderecoServico.atualizar(endereco)).build();
		//System.out.println(endereco);
	}
	@GET
	@Path("pessoa/{id}")
	public Response buscarEnderecoPessoa(@PathParam("id") Long id) {
		 try (Session session =  factoryJpa.openSession()) {
			 Endereco endereco = enderecoServico.buscarEnderecoPessoa(session, id);
	            return Response.ok(enderecoServico.encontrar(endereco.getId())).build();
	        }
		
	}
}
