package LojaDeCarrosBaseDados;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import entity.Carro;
import entity.Categorias;

public class BaseDadosImpl implements BaseDadosInterface{
	
	Map<String, Carro> carros;
	boolean emProcesso = false;
    
	BaseDadosInterface proxBaseDados;
	public BaseDadosImpl() {
		carros = new HashMap<String, Carro>();
		carros.put("23456789012", new Carro("23456789012", "Chevrolet Onix", 2019, 55000, Categorias.economico));
        carros.put("34567890123", new Carro("34567890123", "Ford Ka", 2020, 60000, Categorias.economico));
        carros.put("45678901234", new Carro("45678901234", "Hyundai HB20", 2017, 58000, Categorias.economico));
        carros.put("56789012345", new Carro("56789012345", "Nissan March", 2016, 52000, Categorias.economico));
        carros.put("67890123456", new Carro("67890123456", "Ford Ka Sedan", 2019, 72000, Categorias.intermediario));
        carros.put("78901234567", new Carro("78901234567", "Chevrolet Onix Plus", 2021, 78000, Categorias.intermediario));
        carros.put("89012345678", new Carro("89012345678", "Hyundai HB20S", 2020, 75000, Categorias.intermediario));
        carros.put("90123456789", new Carro("90123456789", "Renault Logan", 2018, 70000, Categorias.intermediario));
        carros.put("01234567890", new Carro("01234567890", "Toyota Etios", 2019, 68000, Categorias.intermediario));
        carros.put("09876543210", new Carro("09876543210", "Toyota Corolla", 2022, 120000, Categorias.executivo));
        carros.put("98765432109", new Carro("98765432109", "Honda Civic", 2021, 110000, Categorias.executivo));
        carros.put("87654321098", new Carro("87654321098", "Chevrolet Cruze", 2020, 105000, Categorias.executivo));
        carros.put("76543210987", new Carro("76543210987", "Audi A3", 2019, 125000, Categorias.executivo));
        
	}
	
	public void connectNext(int port) {
		try {
			Thread.sleep(10000);
			Registry registro = LocateRegistry.getRegistry(port);
			proxBaseDados = (BaseDadosInterface) registro.lookup("BaseDados");
			System.out.println("conectado na proxima base de dados na porta: " + port);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		
	}
	public boolean permissao(int cont) {
		if(cont > 2) {
			System.out.println("permissao para alterar base de dados concedida");
			return true;
		}
		if(emProcesso) {
			return false;
		}
		try {
			return proxBaseDados.permissao(cont+1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void atualizarBaseDados(Map<String, Carro> carros, int cont) {
		if(cont != 0 && cont < 3) {
			System.out.println("atualizando base de dados");
			this.carros = carros;
			try {
				proxBaseDados.atualizarBaseDados(carros, cont+1);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			if(cont == 3) {
				System.out.println("todas as bases atualizadas");
				
			}
			else {
				try {
					proxBaseDados.atualizarBaseDados(carros, cont+1);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	@Override
	public  void addCarro(Carro carro) {
		if(permissao(0)) {
			emProcesso = true;
			carros.put(carro.getRenavam(), carro);
			try {
				atualizarBaseDados(carros, 0);
				emProcesso = false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("ocupado");
		}
		
	}
	@Override
	public Boolean removeCarro(String renavam) throws RemoteException {
		if(permissao(0)) {
			emProcesso = true;
			for(Map.Entry<String, Carro> entry:carros.entrySet()) {
				Carro carro = entry.getValue();
				if(carro.getRenavam().equalsIgnoreCase(renavam)) {
					Boolean removido = carros.remove(renavam, carro);
					try {
						atualizarBaseDados(carros, 0);
						emProcesso = false;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return removido;
				}
			}
		}
		else {
			System.out.println("ocupado");
			
		}
		return false;
	}
	@Override
	public ArrayList<Carro> listarCarros() throws RemoteException {
		ArrayList<Carro> carrosLista = new ArrayList<Carro>(carros.values());
		Collections.sort(carrosLista,(carro1,carro2) -> carro1.getNome().compareTo(carro2.getNome()));
		return carrosLista;
		
	}

	@Override
	public Carro pesquisarCarro(String RenavanOuNome)throws RemoteException {
		for(Map.Entry<String, Carro> entry:carros.entrySet()) {
			Carro carro = entry.getValue();
			if(carro.getRenavam().equalsIgnoreCase(RenavanOuNome)|| carro.getNome().equalsIgnoreCase(RenavanOuNome)) {
				return carro;
			}
		}
		return null;
	}
	
	@Override
	public Boolean alterarCarro(String renavam, String modelo, int ano, double preco, Categorias categoria)throws RemoteException{
		if(permissao(0)) {
			emProcesso = true;
			Carro carro = pesquisarCarro(renavam);
			carro.setNome(modelo);
			carro.setAnoFabricacao(ano);
			carro.setPreco(preco);
			carro.setCategoria(categoria);
			try {
				atualizarBaseDados(carros, 0);
				emProcesso = false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("ocupado");
		}
		 
		return true;
	}
	
	@Override
	public int getQuantidade() throws RemoteException{
		return carros.size();
	}
	
	@Override
	public Boolean comprarCarro(Carro carro)throws RemoteException {
		if(permissao(0)) {
			emProcesso = true;
			Boolean carroRemovido = carros.remove(carro.getRenavam(), carro);
			try {
				atualizarBaseDados(carros, 0);
				emProcesso = false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return carroRemovido;
		}
		else {
			System.out.println("ocupado");
			return false;
		}
	
	}

	

}
