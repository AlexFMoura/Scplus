package com.senai.ecplus.service;

import java.util.Date;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.ecplus.model.Configuracao;
import com.senai.ecplus.repository.Configuracoes;
import com.senai.ecplus.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroConfiguracaoService {
	
	@Autowired
	private Configuracoes configuracoes;

	@Transactional
	public void salvar(Configuracao configuracao) {
		Date date = new Date();
		configuracao.setDataCadastro(date);
		
		configuracoes.save(configuracao);
		
	}
	
	@Transactional
	public void alterarStatus(Long[] codigos, StatusConfiguracao statusConfiguracao) {
		statusConfiguracao.executar(codigos, configuracoes);
	}	

	@Transactional
	public void excluir(Long codigo) {
		try {
			configuracoes.delete(codigo);
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Não foi possével excluir!");
		}
	}

}
