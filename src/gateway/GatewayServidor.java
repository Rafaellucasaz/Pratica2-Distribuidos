package gateway;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class GatewayServidor {
	public static void main(String[] args) {
		try {
			GatewayImpl refObjRemoto = new GatewayImpl();
			GatewayInterface skeleton = (GatewayInterface) UnicastRemoteObject.exportObject(refObjRemoto, 0);
			LocateRegistry.createRegistry(1099);
			Registry registro = LocateRegistry.getRegistry(1099);
			registro.bind("Gateway",skeleton);
			System.err.println("Servidor Pronto:");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
