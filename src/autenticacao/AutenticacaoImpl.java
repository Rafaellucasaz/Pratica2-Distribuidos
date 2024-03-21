package autenticacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.TipoUsuario;
import entity.Usuario;

public class AutenticacaoImpl implements AutenticacaoInterface{
	private List<Usuario> usuarios; 
	public AutenticacaoImpl() {
		usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario("funcionario1","senha123",TipoUsuario.funcionario));
		usuarios.add(new Usuario("cliente1","senha321",TipoUsuario.cliente));
	}
	@Override
	public Usuario autenticarUsuario(Usuario user) {
		for(int i=0;i<usuarios.size();i++) {
			if(user.equals(usuarios.get(i))) {
				System.out.println("Usuario:" + user.getLogin() + " autenticado");
				return usuarios.get(i);
			}
		}
		return null;
	}

}
