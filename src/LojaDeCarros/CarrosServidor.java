package LojaDeCarros;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import LojaDeCarrosReplica.CarrosImplReplica01;

public class CarrosServidor {

	public static int sortearLider() {
		Random random = new Random();
		return random.nextInt(3) + 1;
	}

	public static void main(String[] args) {
		try {
			CarrosImpl refCarrosObjRemoto = new CarrosImpl();
			CarrosInterface skeletonCarros = (CarrosInterface) UnicastRemoteObject.exportObject(refCarrosObjRemoto, 0);
			LocateRegistry.createRegistry(1101);
			Registry registro = LocateRegistry.getRegistry(1101);
			Thread.sleep(10000); // Simular falha -> colocar para dormir
			int sorteado = sortearLider();
			
			registro.bind("Carros", skeletonCarros);
			System.err.println("Servidor de Carros Pronto:");
		} catch (Exception e) {
			System.err.println("servidor:" + e.toString());
			e.printStackTrace();
		}
	}
}
