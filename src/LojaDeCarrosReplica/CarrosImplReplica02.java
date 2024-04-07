package LojaDeCarrosReplica;

import java.util.HashMap;
import java.util.Map;

import entity.Carro;

public class CarrosImplReplica02 {
	Map<String, Carro> carros;
	
	   public CarrosImplReplica02() {
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
}
