package gateway;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

// Aqui que sera simulado uma falha no Gateway
public class GatewayServidor {
	public static void main(String[] args) {
		 System.setProperty("java.rmi.server.hostname", "192.168.1.7");
		 System.setProperty("java.security.policy", "java.policy");
		
		try {
			GatewayImpl refObjRemoto = new GatewayImpl();
			GatewayInterface skeleton = (GatewayInterface) UnicastRemoteObject.exportObject(refObjRemoto, 0);
			LocateRegistry.createRegistry(1099);
			System.out.println(InetAddress.getLocalHost().getHostAddress());

			Registry registro = LocateRegistry.getRegistry(
					InetAddress.getLocalHost().getHostAddress(), 
					1099);
			registro.bind("Gateway",skeleton);
			System.err.println("Servidor Gateway Pronto:");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
