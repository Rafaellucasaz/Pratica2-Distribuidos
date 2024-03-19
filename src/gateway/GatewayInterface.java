package gateway;

import java.rmi.Remote;
import java.rmi.RemoteException;

import autenticacao.AutenticacaoInterface;

public interface GatewayInterface extends Remote {
	public AutenticacaoInterface getServidorAutenticacao() throws RemoteException;
	public String getServidorLoja() throws RemoteException;
}
