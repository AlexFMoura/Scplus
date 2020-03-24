package com.senai.ecplus.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name="configuracao")
@DynamicUpdate
public class Configuracao implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long codigo;
	
	@NotBlank(message = "Banco é obrigatório")
	private String banco;
	
	@NotBlank(message = "Caminho do banco é obrigatório")
	private String caminhoBanco;
	
	@NotNull(message = "Porta é obrigatória")
	private Integer porta;
	
	@NotBlank(message = "Usuário é obrigatório")
	private String usuario;
	
	@NotBlank(message = "Senha é obrigatório")
	private String senha;
	
	@NotBlank(message = "Nome Banco é obrigatório")
	private String nomeBanco;
	
	private boolean ativo;
	
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	public boolean isNovo() {
		return codigo == null;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}	
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getCaminhoBanco() {
		return caminhoBanco;
	}
	public void setCaminhoBanco(String caminhoBanco) {
		this.caminhoBanco = caminhoBanco;
	}
	public Integer getPorta() {
		return porta;
	}
	public void setPorta(Integer porta) {
		this.porta = porta;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNomeBanco() {
		return nomeBanco;
	}
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuracao other = (Configuracao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
