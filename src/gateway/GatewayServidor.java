package gateway;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

// Aqui que sera simulado uma falha no Gateway
public class GatewayServidor {
	public static void main(String[] args) {
		try {
			GatewayImpl refObjRemoto = new GatewayImpl();
			GatewayInterface skeleton = (GatewayInterface) UnicastRemoteObject.exportObject(refObjRemoto, 0);
			LocateRegistry.createRegistry(1099);
			Registry registro = LocateRegistry.getRegistry(1099);
			Thread.sleep(10000); // Simular falha -> colocar para dormir
			registro.bind("Gateway",skeleton);
			System.err.println("Servidor Gateway Pronto:");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
