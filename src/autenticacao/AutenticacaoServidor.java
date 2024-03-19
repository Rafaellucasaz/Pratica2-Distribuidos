package autenticacao;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class AutenticacaoServidor {
	public static void main(String[] args) {
		try {
			AutenticacaoImpl refObjRemoto = new AutenticacaoImpl();
			AutenticacaoInterface skeleton = (AutenticacaoInterface) UnicastRemoteObject.exportObject(refObjRemoto, 0);
			LocateRegistry.createRegistry(1100);
			Registry registro = LocateRegistry.getRegistry(1100);
			registro.bind("Autenticacao", skeleton);
			System.err.println("Servidor Pronto:");
			
		} catch (Exception e) {
			System.err.println("servidor: " + 	e.toString());
			e.printStackTrace();
		}
	}
}
