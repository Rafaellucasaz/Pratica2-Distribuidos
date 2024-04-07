package LojaDeCarrosReplica;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import LojaDeCarros.CarrosInterface;
import entity.Carro;
import entity.Categorias;

public class CarrosImplReplica03 implements CarrosInterface{
	Map<String, Carro> carros;
	
    CarrosImplReplica01 replica01;
    CarrosImplReplica02 replica02;
	
	   public CarrosImplReplica03() {
	        carros = new HashMap<>();
	    }

	public boolean atualizarBaseDados(Map<String, Carro> carros2) {
		if(carros2 != null) {
			if(carros != null)
				carros.clear();
			carros.putAll(carros2);
			return true;
		}
		return false;
	}
	
	private void atualizarReplicas(Map<String, Carro> carros2) {
		System.out.println("Base da replica 02 atualizada (?): " + replica01.atualizarBaseDados(carros2));
		System.out.println("Base da replica 03 atualizada (?): " + replica02.atualizarBaseDados(carros2));
	}
	
	// Os métodos abaixo só serão acessados se houver alguma falha
	@Override
	public Boolean adicionarCarro(String renavam, String modelo, int ano, double preco, Categorias categoria)throws RemoteException {
		Carro carro = new Carro(renavam,modelo,ano,preco,categoria);
		Carro aux = carros.get(carro);
		if(aux != null) {
			return false;
		}
		
		carros.put(renavam,carro);
		System.out.println("carro adicionado");
		atualizarReplicas(carros);
		return true;
	}
	@Override
	public Boolean removerCarro(String renavam) throws RemoteException{
		for(Map.Entry<String, Carro> entry:carros.entrySet()) {
			Carro carro = entry.getValue();
			if(carro.getRenavam().equalsIgnoreCase(renavam)) {
				Boolean removido = carros.remove(renavam, carro);
				 atualizarReplicas(carros);
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
		 atualizarReplicas(carros);
		return true;
	}
	@Override
	public int getQuantidade() throws RemoteException{
		return carros.size();
	}
	@Override
	public Boolean comprarCarro(Carro carro)throws RemoteException {
		 atualizarReplicas(carros);
		return carros.remove(carro.getRenavam(), carro);
	}
}
