package gateway;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

import LojaDeCarros.CarrosInterface;
import autenticacao.AutenticacaoInterface;

public class GatewayImpl implements GatewayInterface {
	public int n = 0;
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
			Registry registro = LocateRegistry.getRegistry(1101);
			CarrosInterface servidorLoja = (CarrosInterface) registro.lookup("Carros");
			System.out.println("retornando servidor da loja para cliente...");
			return servidorLoja;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public CarrosInterface getServidorLojaReplica01() {
		try {
			Registry registro = LocateRegistry.getRegistry(1102);
			CarrosInterface servidorLoja = (CarrosInterface) registro.lookup("Carros");
			System.out.println("retornando replica01 da loja para cliente...");
			return servidorLoja;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public CarrosInterface getServidorLojaReplica02() {
		try {
			Registry registro = LocateRegistry.getRegistry(1103);
			CarrosInterface servidorLoja = (CarrosInterface) registro.lookup("Carros");
			System.out.println("retornando replica02 da loja para cliente...");
			return servidorLoja;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public CarrosInterface selectReplica() {
		
		switch (n) {
			case 0: {
				n = 1;
				return getServidorLoja();
			}
			case 1:{
				n = 2;
				return getServidorLojaReplica01();
			}
			case 2: {
				n = 0;
				return getServidorLojaReplica02();
			}
		}
		return null;
	}
	
}
