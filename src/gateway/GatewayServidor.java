package gateway;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

// Aqui que sera simulado uma falha no Gateway
public class GatewayServidor {

	public static void main(String[] args) {
		 System.setProperty("java.rmi.server.hostname", "localhost");
		 System.setProperty("java.security.policy", "java.policy");
		
		try {
			GatewayImpl refObjRemoto = new GatewayImpl();
			GatewayInterface skeleton = (GatewayInterface) UnicastRemoteObject.exportObject(refObjRemoto, 0);
			LocateRegistry.createRegistry(1099);

			Registry registro = LocateRegistry.getRegistry(
					InetAddress.getLocalHost().getHostAddress(), 
					1099);
			registro.bind("Gateway",skeleton);
			LocalDateTime agora = LocalDateTime.now();
			System.err.println(agora + " [gatway] INFO - Gatway iniciado na porta 1099");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
