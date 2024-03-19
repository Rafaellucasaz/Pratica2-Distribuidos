package autenticacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.Usuario;

public class AutenticacaoImpl implements AutenticacaoInterface{
	private List<Usuario> usuarios; 
	public AutenticacaoImpl() {
		usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario("funcionario1","senha123","funcionario"));
		usuarios.add(new Usuario("cliente1","senha321","cliente"));
	}
	@Override
	public Usuario autenticarUsuario(Usuario user) {
		for(int i=0;i<usuarios.size();i++) {
			System.out.println("tesste");
			System.out.println(user.getLogin());
			System.out.println(usuarios.get(i).getLogin());
			if(user.getLogin().equals(usuarios.get(i).getLogin()) && user.getSenha().equals(usuarios.s)) {
				System.out.println("usuario encontrado");
				return usuarios.get(i);
			}
		}
		return null;
	}

}
