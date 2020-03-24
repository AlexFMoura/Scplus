package com.senai.ecplus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.ecplus.model.Configuracao;
import com.senai.ecplus.repository.helper.configuracao.ConfiguracoesQueries;

public interface Configuracoes extends JpaRepository<Configuracao, Long>, ConfiguracoesQueries {

	public List<Configuracao> findByCodigoIn(Long[] codigos);
}
