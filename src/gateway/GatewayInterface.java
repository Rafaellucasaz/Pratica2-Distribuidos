package gateway;

import java.rmi.Remote;
import java.rmi.RemoteException;

import LojaDeCarros.CarrosInterface;
import autenticacao.AutenticacaoInterface;

public interface GatewayInterface extends Remote {
	public AutenticacaoInterface getServidorAutenticacao() throws RemoteException;
	public CarrosInterface getServidorLoja() throws RemoteException;
}
