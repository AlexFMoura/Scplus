package com.senai.ecplus.repository.helper.configuracao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.senai.ecplus.model.Configuracao;
import com.senai.ecplus.repository.filter.ConfiguracaoFilter;

public interface ConfiguracoesQueries {

	public Page<Configuracao> filtrar(ConfiguracaoFilter filtro,Pageable pageable);

	public Configuracao buscar(Long codigo);
	
}
