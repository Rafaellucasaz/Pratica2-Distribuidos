package entity;

import java.io.Serializable;
import java.security.KeyStore.PrivateKeyEntry;

public class Carro implements Serializable{
	private enum categorias{
		economico,
		intermediario,
		executivo;
	}
	private String renavam;
	private String nome;
	private int anoFabricacao;
	private int qtd;
	private double preco;
	
}
