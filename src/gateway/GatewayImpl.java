package gateway;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import LojaDeCarros.CarrosInterface;
import autenticacao.AutenticacaoInterface;

public class GatewayImpl implements GatewayInterface {

	@Override
	public AutenticacaoInterface getServidorAutenticacao() {
		try {
			Registry registro = LocateRegistry.getRegistry(1100);
			AutenticacaoInterface servidorAuth = (AutenticacaoInterface) registro.lookup("Autenticacao");
			System.out.println("retornando servidor de autenticação para cliente...");
			return servidorAuth;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CarrosInterface getServidorLoja() {
		try {
			Registry registro = LocateRegistry.getRegistry("localhost",1101);
			CarrosInterface servidorLoja = (CarrosInterface) registro.lookup("Carros");
			System.out.println("retornando servidor da loja para cliente...");
			return servidorLoja;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
