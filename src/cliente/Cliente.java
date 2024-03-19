package cliente;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import autenticacao.AutenticacaoInterface;
import entity.Usuario;
import gateway.GatewayInterface;

public class Cliente {
	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println("conectando ao gateway...");
		try {
			Registry registro = LocateRegistry.getRegistry("localhost",1099);
			GatewayInterface objRemotoCliente = (GatewayInterface) registro.lookup("Gateway");
			AutenticacaoInterface ServidorRemotoAuth = objRemotoCliente.getServidorAutenticacao();
			boolean logado = false;
			Usuario user = null;
			while(user == null) {
				user = new Usuario();
				System.out.println("digite o login:");
				user.setLogin(scanner.nextLine());
				System.out.println("digite a senha:");
				user.setSenha(scanner.nextLine());
				user = ServidorRemotoAuth.autenticarUsuario(user);
				if(user!=null) {
					System.out.println("teste");
				}
			}
			System.out.println("logado");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
