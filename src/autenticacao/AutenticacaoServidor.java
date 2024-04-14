package autenticacao;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

public class AutenticacaoServidor {
	public static void main(String[] args) {
		try {
			AutenticacaoImpl refObjRemoto = new AutenticacaoImpl();
			AutenticacaoInterface skeleton = (AutenticacaoInterface) UnicastRemoteObject.exportObject(refObjRemoto, 0);
			LocateRegistry.createRegistry(1100);
			Registry registro = LocateRegistry.getRegistry(1100);
			registro.bind("Autenticacao", skeleton);
			LocalDateTime agora = LocalDateTime.now();
			System.err.println(agora + " [autenticacao] INFO - Servidor de Autenticação iniciado na porta 1100");
			
		} catch (Exception e) {
			System.err.println("servidor: " + 	e.toString());
			e.printStackTrace();
		}
	}
}
