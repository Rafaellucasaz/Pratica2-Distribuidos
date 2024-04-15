package LojaDeCarrosBaseDados;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

import LojaDeCarros.CarrosImpl;
import LojaDeCarros.CarrosInterface;

public class BaseDadosServidor {
	public static void main(String[] args) {
		try {
			BaseDadosInterface refBaseDadosObjRemoto = new BaseDadosImpl();
			BaseDadosInterface skeletonBaseDados = (BaseDadosInterface) UnicastRemoteObject.exportObject(refBaseDadosObjRemoto, 0);
			LocateRegistry.createRegistry(1104);
			Registry registro = LocateRegistry.getRegistry(1104);
			//Thread.sleep(10000); // Simular falha -> colocar para dormir
			//int sorteado = sortearLider();
			
			registro.bind("BaseDados", skeletonBaseDados);
			LocalDateTime agora = LocalDateTime.now();
			System.err.println(agora + " [database] INFO - Base de Dados iniciada na porta 1104");
			refBaseDadosObjRemoto.connectNext(1114);

		} catch (Exception e) {
			System.err.println("servidor:" + e.toString());
			e.printStackTrace();
		}
	}
}
