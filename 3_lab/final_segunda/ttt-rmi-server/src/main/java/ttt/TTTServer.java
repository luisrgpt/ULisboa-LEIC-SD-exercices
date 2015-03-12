package ttt;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TTTServer {

	//@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		int registryPort =1099;
		
		//System.setSecurityManager(new RMISecurityManager());
		System.out.println("Main OK");
		
		try{
			TTTService _ticTacToe=new TTT();
			System.out.println("after create");
			
			Registry reg = LocateRegistry.createRegistry(registryPort);
			reg.rebind("TTT", _ticTacToe);
			
			Naming.rebind("TTT", _ticTacToe);
			System.out.println("TTT server ready");
		}catch(Exception e){
			System.out.println("TTT server main "+ e.getMessage());
			
		}
		
	}

}
