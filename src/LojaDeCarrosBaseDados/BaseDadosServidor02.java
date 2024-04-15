package LojaDeCarrosBaseDados;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

public class BaseDadosServidor02 {
	public static void main(String[] args) {
		try {
			BaseDadosInterface refBaseDadosObjRemoto = new BaseDadosImpl();
			BaseDadosInterface skeletonBaseDados = (BaseDadosInterface) UnicastRemoteObject.exportObject(refBaseDadosObjRemoto, 0);
			LocateRegistry.createRegistry(1124);
			Registry registro = LocateRegistry.getRegistry(1124);
			//Thread.sleep(10000); // Simular falha -> colocar para dormir
			//int sorteado = sortearLider();
			
			registro.bind("BaseDados", skeletonBaseDados);
			LocalDateTime agora = LocalDateTime.now();
			System.err.println(agora + " [database] INFO - Base de Dados iniciada na porta 1124");
			refBaseDadosObjRemoto.connectNext(1104);

		} catch (Exception e) {
			System.err.println("servidor:" + e.toString());
			e.printStackTrace();
		}
	}
}
