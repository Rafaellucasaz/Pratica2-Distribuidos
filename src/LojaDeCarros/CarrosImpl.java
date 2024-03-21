package LojaDeCarros;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import entity.Carro;
import entity.Categorias;

public class CarrosImpl implements CarrosInterface{
	Map<String, Carro> carros;
	public CarrosImpl() {
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
	@Override
	public Boolean adicionarCarro(String renavam, String modelo, int ano, double preco, Categorias categoria)throws RemoteException {
		Carro carro = new Carro(renavam,modelo,ano,preco,categoria);
		Carro aux = carros.get(carro);
		if(aux != null) {
			return false;
		}
		
		carros.put(renavam,carro);
		System.out.println("carro adicionado");
		return true;
		
	}
	@Override
	public Boolean removerCarro(String renavam) throws RemoteException{
		for(Map.Entry<String, Carro> entry:carros.entrySet()) {
			Carro carro = entry.getValue();
			if(carro.getRenavam().equalsIgnoreCase(renavam)) {
				Boolean removido = carros.remove(renavam, carro);
				return removido;
			}
		}
		return false;
	}
	@Override
	public ArrayList<Carro> listarCarros()throws RemoteException {
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
		Carro carro = pesquisarCarro(renavam);
		carro.setNome(modelo);
		carro.setAnoFabricacao(ano);
		carro.setPreco(preco);
		carro.setCategoria(categoria);
		return true;
	}
	@Override
	public int getQuantidade() throws RemoteException{
		return carros.size();
	}
	@Override
	public Boolean comprarCarro(Carro carro)throws RemoteException {
		return carros.remove(carro.getRenavam(), carro);
	}
}
