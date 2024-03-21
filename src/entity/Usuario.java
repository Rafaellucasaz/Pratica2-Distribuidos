package entity;

import java.io.Serializable;
import java.util.Objects;


public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private String login;
	private String senha;
	private TipoUsuario tipo;
	public Usuario() {};
	public Usuario(String login,String senha,TipoUsuario tipo) {
		setLogin(login);
		setSenha(senha);
		setTipo(tipo);
	}
	
	public TipoUsuario getTipo() {
		return tipo;
	}
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(login, senha);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(login, other.login) && Objects.equals(senha, other.senha);
	}
	
}
