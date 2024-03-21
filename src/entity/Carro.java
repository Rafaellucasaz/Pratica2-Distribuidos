package entity;

import java.io.Serializable;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.Objects;

public class Carro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String renavam;
	private String nome;
	private int anoFabricacao;
	private double preco;
	private Categorias categoria;
	

	public Carro(String renavam, String nome, int anoFabricacao, double preco, Categorias categoria) {
		super();
		setRenavam(renavam);
		setNome(nome);
		setAnoFabricacao(anoFabricacao);
		setPreco(preco);
		setCategoria(categoria);
	}
	public String getRenavam() {
		return renavam;
	}
	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}
	public String getNome() {
		return nome;
	}	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Categorias getCategoria() {
		return categoria;
	}
	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}
	@Override
	public int hashCode() {
		return Objects.hash(renavam);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		return Objects.equals(renavam, other.renavam);
	}
	
	
	
}
