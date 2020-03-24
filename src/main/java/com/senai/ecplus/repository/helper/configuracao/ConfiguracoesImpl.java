package com.senai.ecplus.repository.helper.configuracao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.senai.ecplus.model.Configuracao;
import com.senai.ecplus.repository.filter.ConfiguracaoFilter;
import com.senai.ecplus.repository.paginacao.PaginacaoUtil;

public class ConfiguracoesImpl implements ConfiguracoesQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Configuracao> filtrar(ConfiguracaoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Configuracao.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
		
	}

	private void adicionarFiltro(ConfiguracaoFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getBanco())) {
				criteria.add(Restrictions.ilike("banco", filtro.getBanco(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filtro.getNomeBanco())) {
				criteria.add(Restrictions.ilike("nomeBanco", filtro.getNomeBanco(), MatchMode.START));
			}

		}
		
	}

	private Long total(ConfiguracaoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Configuracao.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		
		return (Long) criteria.uniqueResult();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Configuracao buscar(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Configuracao.class);
		criteria.add(Restrictions.eq("codigo", codigo));
		return (Configuracao) criteria.uniqueResult();
	}

}
