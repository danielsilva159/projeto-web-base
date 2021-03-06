package com.stefanini.servico;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.validation.Valid;

import com.stefanini.dao.PerfilDao;
import com.stefanini.model.Perfil;

/**
 * 
 * Classe de servico, as regras de negocio devem estar nessa classe
 * 
 * @author Daniel Ferreira da Silva
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)

public class PerfilServico implements Serializable {
	
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Inject
		private PerfilDao dao;

		/**
		 * Salvar o tipo de perfil
		 */
		@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
		public Perfil salvar(Perfil perfil) {
			
			return dao.salvar(perfil);
		}

		/**
		 * Atualizar o dados de um perfil
		 */
//		@Override
		public Perfil atualizar(@Valid Perfil entity) {
			return dao.atualizar(entity);
		}

		/**
		 * Remover um perfil pelo id
		 */
//		@Override
		@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
		public void remover(@Valid Long id) {
			dao.remover(id);
		}

		/**
		 * Buscar uma lista de Perfil
		 */
//		@Override
		public Optional<List<Perfil>> getList() {
			return dao.getList();
		}

		/**
		 * Buscar um perfil pelo ID
		 */
//		@Override
		public Optional<Perfil> encontrar(Long id) {
			return dao.encontrar(id);
		}

	}


