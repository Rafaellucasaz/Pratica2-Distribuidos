package autenticacao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entity.Usuario;

public interface AutenticacaoInterface extends Remote{
	public Usuario autenticarUsuario(Usuario user) throws RemoteException;
}
