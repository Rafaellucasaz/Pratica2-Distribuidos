package cliente;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import LojaDeCarros.CarrosInterface;
import autenticacao.AutenticacaoInterface;
import entity.Carro;
import entity.Categorias;
import entity.TipoUsuario;
import entity.Usuario;
import gateway.GatewayInterface;

public class Cliente {
	public static Scanner scanner = new Scanner(System.in);
	
	public static void autenticacao(GatewayInterface objRemotoGateway) throws Exception{
		
		AutenticacaoInterface ServidorRemotoAuth = objRemotoGateway.getServidorAutenticacao();
		
		Usuario user = null;
		while(user == null) {
			user = new Usuario();
			System.out.println("digite o login:");
			user.setLogin(scanner.nextLine());
			System.out.println("digite a senha:");
			user.setSenha(scanner.nextLine());
			user = ServidorRemotoAuth.autenticarUsuario(user);
			if(user == null) {
				System.out.println("login ou senha incorreta");
			}
		}
		lojaCarros(objRemotoGateway,user);
	}
	
	public static void lojaCarros(GatewayInterface objRemotoGateway,Usuario user)throws Exception {
	
		CarrosInterface objRemotoCarros = objRemotoGateway.selectReplica();
		if(user.getTipo() == TipoUsuario.cliente) {
			menuCliente(objRemotoCarros,user);
		}
		else {
			menuFuncionario(objRemotoCarros,user);
		}
		
	}
	
	public static void menuCliente(CarrosInterface objRemotoCarros,Usuario user) {
		int escolha =0;
		while(escolha!=5) {
			System.out.println("1-Listar carros");
			System.out.println("2-Pesquisar carro por renavam ou nome");
			System.out.println("3-Exibir quantidade de carros");
			System.out.println("4-Comprar carro");
			System.out.println("5-Sair");
			escolha = scanner.nextInt();
			switch(escolha) {
				case 1:
					listarCarros(objRemotoCarros);
					break;
				case 2:
					pesquisarCarro(objRemotoCarros);
					break;
				case 3:
					exibirQuant(objRemotoCarros);
					break;
				case 4:
					comprarCarro(objRemotoCarros);
					break;
				case 5:
					break;
				default:
					System.out.println("opcao invalida");
					break;		
			}
		}
		
	}
	
	public static void menuFuncionario(CarrosInterface objRemotoCarros,Usuario user) {
		int escolha = 0;
		while(escolha!=7) {
			System.out.println("1-Adicionar carro");
			System.out.println("2-Apagar carro");
			System.out.println("3-Listar carros");
			System.out.println("4-Pesquisar carro por renavam ou nome");
			System.out.println("5-Editar carro");
			System.out.println("6-Exibir quantidade de carros");
			System.out.println("7-Sair");
			escolha = scanner.nextInt();
			switch(escolha) {
				case 1:
					adicionarCarro(objRemotoCarros);
					break;
				case 2: 
					apagarCarro(objRemotoCarros);
					break;
				case 3:
					listarCarros(objRemotoCarros);
					break;
				case 4:
					pesquisarCarro(objRemotoCarros);
					break;
				case 5:
					editarCarro(objRemotoCarros);
					break;
				case 6:
					exibirQuant(objRemotoCarros);
					break;
				case 7:
					break;
				default:
					System.out.println("opcao invalida");
					break;
		}
		}
	}
	
	public static void listarCarros(CarrosInterface objRemotoCarros) {
		ArrayList<Carro> listaCarros;
		try {
			listaCarros = objRemotoCarros.listarCarros();
			for (Carro carro : listaCarros) {
				System.out.println("------------------");
				System.out.println("Renavam: " + carro.getRenavam());
				System.out.println("Nome: " + carro.getNome());
				System.out.println("Preço: " + carro.getPreco());
				System.out.println("ano: " + carro.getAnoFabricacao());
				System.out.println("categoria: " + carro.getCategoria());
				System.out.println("------------------");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void pesquisarCarro(CarrosInterface objRemotoCarros) {
		scanner.nextLine();
		System.out.println("digite o renavam ou nome do carro:");
		String renavamOuNome = scanner.nextLine();
		try {
			Carro carro = objRemotoCarros.pesquisarCarro(renavamOuNome);
			if(carro == null) {
				System.out.println("carro não encontrado");
			}
			else {
				System.out.println("------------------");
				System.out.println("Renavam: " + carro.getRenavam());
				System.out.println("Nome: " + carro.getNome());
				System.out.println("Preço: " + carro.getPreco());
				System.out.println("ano: " + carro.getAnoFabricacao());
				System.out.println("categoria: " + carro.getCategoria());
				System.out.println("------------------");
			}
		} catch (RemoteException e) {
			System.out.println("não foi possivel conectar-se ao servidor... Tente novamente mais tarde");
		}
	}
	
	public static void exibirQuant(CarrosInterface objRemotoCarros) {
		try {
			System.out.println("quantidade de carros: " + objRemotoCarros.getQuantidade());
		} catch (RemoteException e) {
			System.out.println("não foi possivel conectar-se ao servidor... Tente novamente mais tarde");
		}
	}
	
	public static void comprarCarro(CarrosInterface objRemotoCarros) {
		scanner.nextLine();
		System.out.println("digite o nome do carro:");
		try {
			Carro carro = objRemotoCarros.pesquisarCarro(scanner.nextLine());
			if(carro ==null) {
				System.out.println("carro não encontrado");
			}
			else {
				System.out.println("Comprando o seguinte carro:");
				System.out.println("Modelo: "+ carro.getNome());
				System.out.println("Preco: " + carro.getPreco());
				System.out.println("Confirmar Compra ? S/N");
				if(scanner.nextLine().equalsIgnoreCase("S")) {
					boolean compra = objRemotoCarros.comprarCarro(carro);
					if(compra) {
						System.out.println("Parabens pelo novo carro!");
					}
					else {
						System.out.println("erro na compra... tente novamente mais tarde");
					}
				}
				else {
					System.out.println("fica pra proxima");
				}
			}
		} catch (RemoteException e) {
			System.out.println("erro na compra... tente novamente mais tarde");
		}
	}
	
	public static void adicionarCarro(CarrosInterface objRemotoCarros) {
		scanner.nextLine();
		System.out.println("entre com as informações do carro:");
		System.out.println("Renavam:");
		String renavam = scanner.nextLine();
		System.out.println("Modelo:");
		String modelo = scanner.nextLine();
		System.out.println("Ano de fabricação:");
		int ano = scanner.nextInt();
		scanner.nextLine();
		System.out.println("preco:");
		double preco = scanner.nextDouble();
		System.out.print("categoria:1- Economico 2- Intermediario 3- Executivo");
		int escolha = scanner.nextInt();
		Categorias categoria = null;
		switch(escolha) {
			case 1:
				categoria  = Categorias.economico;
				break;
			case 2:
				categoria = Categorias.intermediario;
				break;
			case 3:
				categoria = Categorias.executivo;
				break;
			default:
				System.out.println("opcao invalida");
				adicionarCarro(objRemotoCarros);
		}
		try {
			Boolean add =objRemotoCarros.adicionarCarro(renavam, modelo, ano, preco, categoria);
			if(add) {
				System.out.println("carro adicionado com sucesso");
			}
			else {
				System.out.println("carro já existe no registro");
				adicionarCarro(objRemotoCarros);
			}
		} catch (RemoteException e) {
			System.out.println("falha ao adicionar carro... Tente novamente mais tarde");
		}
	}
	
	public static void apagarCarro(CarrosInterface objRemotoCarros) {
		scanner.nextLine();
		System.out.println("digite o renavam do carro a ser excluido:");
		try {
			String renavam = scanner.nextLine();
			Boolean removido = objRemotoCarros.removerCarro(renavam);
			if(!removido) {
				System.out.println("carro nao encontrado");
			}
			else {
				System.out.println("carro removido!");
			}
			
		} catch (RemoteException e) {
			System.out.println("erro ao remover carro... Tente novamente mais tarde");
		}
	}
	
	public static void editarCarro(CarrosInterface objRemotoCarros) {
		scanner.nextLine();
		System.out.println("digite o renavam do carro a ser editado:");
		String renavam = scanner.nextLine();
		
		try {
			Carro carro = objRemotoCarros.pesquisarCarro(renavam);
			if(carro == null) {
				System.out.println("carro não encontrado");
			}
			else {
				System.out.println("mostrando informacoes do carro:");
				System.out.println("------------------");
				System.out.println("Renavam: " + carro.getRenavam());
				System.out.println("Nome: " + carro.getNome());
				System.out.println("Preço: " + carro.getPreco());
				System.out.println("ano: " + carro.getAnoFabricacao());
				System.out.println("categoria: " + carro.getCategoria());
				System.out.println("------------------");
				System.out.println("entre com os valores a serem modificados:");
				System.out.println("Modelo:");
				String modelo = scanner.nextLine();
				System.out.println("Ano de fabricação:");
				int ano = scanner.nextInt();
				scanner.nextLine();
				System.out.println("preco:");
				double preco = scanner.nextDouble();
				System.out.print("categoria:1- Economico 2- Intermediario 3- Executivo");
				int escolha = scanner.nextInt();
				Categorias categoria = null;
				switch(escolha) {
					case 1:
						categoria  = Categorias.economico;
						break;
					case 2:
						categoria = Categorias.intermediario;
						break;
					case 3:
						categoria = Categorias.executivo;
						break;
					default:
						System.out.println("opcao invalida");
						editarCarro(objRemotoCarros);
				}
				Boolean edit =objRemotoCarros.alterarCarro(renavam, modelo, ano, preco, categoria);
				if(edit) {
					System.out.println("carro editado com sucesso");
				}
				else {
					System.out.println("falha ao editar carro.... Tente novamente mais tarde");
				}
			}
		} catch (RemoteException e) {
			System.out.println("erro ao editar carro... Tente novamente mais tarde");
		}
	}
		
		
	
	
	public static void main(String[] args) {
        System.setProperty("java.security.policy", "java.policy");

		
		System.out.println("conectando ao gateway...");
		try {

			Registry registro = LocateRegistry.getRegistry("192.168.1.2",1099);
			GatewayInterface objRemotoGateway = (GatewayInterface) registro.lookup("Gateway");
			autenticacao(objRemotoGateway);
			LocalDateTime agora = LocalDateTime.now();
			System.err.println(agora + " [cliente] INFO - Cliente conectado com sucesso");
		} catch (Exception e) {
			System.out.println("imposivel conectar no gateway... Tente novamente mais tarde");
		}
	}
}
