package LojaDeCarros;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.Random;


public class CarrosServidor {

	public static int sortearLider() {
		Random random = new Random();
		return random.nextInt(3) + 1;
	}

	public static void main(String[] args) {
		try {
			CarrosImpl refCarrosObjRemoto = new CarrosImpl(1104);
			CarrosInterface skeletonCarros = (CarrosInterface) UnicastRemoteObject.exportObject(refCarrosObjRemoto, 0);
			LocateRegistry.createRegistry(1101);
			Registry registro = LocateRegistry.getRegistry(1101);
			//Thread.sleep(10000); // Simular falha -> colocar para dormir
			//int sorteado = sortearLider();
			
			registro.bind("Carros", skeletonCarros);
			LocalDateTime agora = LocalDateTime.now();
			System.err.println(agora + " [carros] INFO - Servidor de Carros iniciado na porta 1101");
		} catch (Exception e) {
			System.err.println("servidor:" + e.toString());
			e.printStackTrace();
		}
	}
}
