package LojaDeCarrosBaseDados;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

import entity.Carro;
import entity.Categorias;

public interface BaseDadosInterface extends Remote{
	public void connectNext(int port) throws RemoteException;
	public boolean permissao(int cont) throws RemoteException;
	public void atualizarBaseDados(Map<String, Carro> carros,int cont) throws RemoteException;
	public void addCarro(Carro carro) throws RemoteException;
	public Boolean removeCarro(String renavam) throws RemoteException;
	public ArrayList<Carro> listarCarros()throws RemoteException;
	public Carro pesquisarCarro(String RenavanOuNome)throws RemoteException;
	public Boolean alterarCarro(String renavam, String modelo, int ano, double preco, Categorias categoria)throws RemoteException;
	public int getQuantidade() throws RemoteException;
	public Boolean comprarCarro(Carro carro)throws RemoteException;
}
