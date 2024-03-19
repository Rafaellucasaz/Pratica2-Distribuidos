package gateway;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import autenticacao.AutenticacaoInterface;

public class GatewayImpl implements GatewayInterface {

	@Override
	public AutenticacaoInterface getServidorAutenticacao() {
		try {
			Registry registro = LocateRegistry.getRegistry("localhost",1100);
			AutenticacaoInterface servidorAuth = (AutenticacaoInterface) registro.lookup("Autenticacao");
			return servidorAuth;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getServidorLoja() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
