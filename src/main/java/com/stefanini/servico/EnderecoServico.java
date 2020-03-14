package com.stefanini.servico;

import com.stefanini.dao.EnderecoDao;
import com.stefanini.model.Endereco;
import com.stefanini.model.Pessoa;
import com.stefanini.util.IGenericService;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

/**
 * 
 * Classe de servico, as regras de negocio devem estar nessa classe
 * @author Daniel Ferreira
 *
 */


public class EnderecoServico implements IGenericService<Endereco, Long> {
	
	
	@Inject
	private EnderecoDao dao;


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Endereco salvar(@Valid Endereco entity) {
		return dao.salvar(entity);
	}

	@Override
	public Endereco atualizar(@Valid Endereco entity) {
		return dao.atualizar(entity);
	}

	@Override
	public void remover(Long id) {
	dao.remover(id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Optional<List<Endereco>> getList() {
		return dao.getList();
	}

	@Override
	public Optional<Endereco> encontrar(Long id) {
		return dao.encontrar(id);
	}
	
	public Endereco buscarEnderecoPessoa(Session session, Long id) {
		 Query<Endereco> query = session.createQuery("select p from endereco p where p.idPessoa=?");
		 query.setParameter(1, id);
		 Endereco endereco = (Endereco) query.getResultList();
		 return endereco;
	}
}
