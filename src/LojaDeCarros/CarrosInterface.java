package LojaDeCarros;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.Carro;
import entity.Categorias;

public interface CarrosInterface extends Remote{
	// Operações de escrita -> Replica Líder
	public Boolean adicionarCarro(String renavam, String modelo, int ano, double preco, Categorias categoria) throws RemoteException;
	public Boolean removerCarro(String renavam)throws RemoteException;
	public Boolean alterarCarro(String renavam, String modelo, int ano, double preco, Categorias categoria)throws RemoteException;
	public Boolean comprarCarro(Carro carro)throws RemoteException;
	
	// Operações de leitura -> Replica Líder e seguidoras
	public ArrayList<Carro> listarCarros()throws RemoteException;
	public Carro pesquisarCarro(String RenavanOuNome)throws RemoteException;
	public int getQuantidade()throws RemoteException;
}
