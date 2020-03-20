package com.senai.scplus.repository.helper.configuracao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.senai.scplus.model.Configuracao;
import com.senai.scplus.repository.filter.ConfiguracaoFilter;

public interface ConfiguracoesQueries {

	public Page<Configuracao> filtrar(ConfiguracaoFilter filtro,Pageable pageable);

	public Configuracao buscar(Long codigo);
	
}
