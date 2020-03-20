package com.senai.scplus.service;

import java.util.Date;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.senai.scplus.model.Usuario;
import com.senai.scplus.repository.Usuarios;
import com.senai.scplus.service.exception.EmailUsuarioJaCadastradoException;
import com.senai.scplus.service.exception.ImpossivelExcluirEntidadeException;
import com.senai.scplus.service.exception.SenhaObrigatoriaUsuarioException;

@Service
public class CadastroUsuarioService {

	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarios.findByEmail(usuario.getEmail());
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new EmailUsuarioJaCadastradoException("Email já cadastrado");
		}
		
		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatório para usuário novo");
		}
		
		if (usuario.isNovo() || !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
			Date data = new Date();
			usuario.setDataCadastro(data);
		} else if (StringUtils.isEmpty(usuario.getSenha())) {	
			usuario.setSenha(usuarioExistente.get().getSenha());
			usuario.setDataCadastro(usuarioExistente.get().getDataCadastro());
		}
		
		if (usuario.isNovo() && usuario.getAtivo() == null) {
			usuario.setAtivo(usuarioExistente.get().getAtivo());
		}
		
		usuario.setConfirmacaoSenha(usuario.getSenha());
		
		
		usuarios.save(usuario);	
	}

	@Transactional
	public void alterarStatus(Long[] codigos, StatusUsuario statusUsuario) {
		statusUsuario.executar(codigos, usuarios);
	}
	
	@Transactional
	public void excluir(Long codigo) {
		try {
			usuarios.delete(codigo);
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Não foi possével excluir!");
		}
	}
}
