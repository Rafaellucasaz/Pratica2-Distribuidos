package LojaDeCarros;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class CarrosServidor {

	public static void sortearLider() {
		Random random = new Random();
		int numeroSorteado = random.nextInt(3) + 1;
		System.out.println("Líder sorteado: " + numeroSorteado);
	}

	public static void main(String[] args) {
		try {
			CarrosImpl refCarrosObjRemoto = new CarrosImpl();
			CarrosInterface skeletonCarros = (CarrosInterface) UnicastRemoteObject.exportObject(refCarrosObjRemoto, 0);
			LocateRegistry.createRegistry(1101);
			Registry registro = LocateRegistry.getRegistry(1101);
			Thread.sleep(10000); // Simular falha -> colocar para dormir
			registro.bind("Carros", skeletonCarros);
			System.err.println("Servidor de Carros Pronto:");
		} catch (Exception e) {
			System.err.println("servidor:" + e.toString());
			e.printStackTrace();
		}
	}
}
