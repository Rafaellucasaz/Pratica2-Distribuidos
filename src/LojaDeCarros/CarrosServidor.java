package LojaDeCarros;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CarrosServidor {
	public static void main(String[] args) {
		try {
			CarrosImpl refCarrosObjRemoto = new CarrosImpl();
			CarrosInterface skeletonCarros = (CarrosInterface) UnicastRemoteObject.exportObject(refCarrosObjRemoto,0);
			LocateRegistry.createRegistry(1101);
			Registry registro = LocateRegistry.getRegistry(1101);
			registro.bind("Carros", skeletonCarros);
			System.err.println("Servidor Pronto:");
			
		} catch (Exception e) {
			System.err.println("servidor:" + e.toString());
			e.printStackTrace();
		}
	}
}
