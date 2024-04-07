package LojaDeCarrosReplica;

import java.util.Map;

import entity.Carro;

public class CarrosImplReplica01 {
	Map<String, Carro> carros;
	
	public CarrosImplReplica01(){
		
	}

	public boolean atualizarBaseDados(Map<String, Carro> carros2) {
		if(carros != null) {
			carros.clear();
			carros.putAll(carros2);
			return true;
		}
		return false;
	}
}
