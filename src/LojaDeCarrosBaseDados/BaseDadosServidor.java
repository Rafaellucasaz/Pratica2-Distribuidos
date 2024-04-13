package LojaDeCarrosBaseDados;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

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
			System.err.println("Base de dados da loja pronto");
		} catch (Exception e) {
			System.err.println("servidor:" + e.toString());
			e.printStackTrace();
		}
	}
}
