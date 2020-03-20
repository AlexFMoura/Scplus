package com.senai.scplus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.scplus.model.Configuracao;
import com.senai.scplus.repository.helper.configuracao.ConfiguracoesQueries;

public interface Configuracoes extends JpaRepository<Configuracao, Long>, ConfiguracoesQueries {

	public List<Configuracao> findByCodigoIn(Long[] codigos);
}
