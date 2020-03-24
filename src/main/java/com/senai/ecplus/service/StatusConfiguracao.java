package com.senai.ecplus.service;

import com.senai.ecplus.repository.Configuracoes;

public enum StatusConfiguracao {

	ATIVAR {
		@Override
		public void executar(Long[] codigos, Configuracoes configuracoes) {
			configuracoes.findByCodigoIn(codigos).forEach(u -> u.setAtivo(true));
		}
	},
	
	DESATIVAR {
		@Override
		public void executar(Long[] codigos, Configuracoes configuracoes) {
			configuracoes.findByCodigoIn(codigos).forEach(u -> u.setAtivo(false));
		}
	};
	
	public abstract void executar(Long[] codigos, Configuracoes configuracoes);
}
