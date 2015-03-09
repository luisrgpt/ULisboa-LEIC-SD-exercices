package ttt;

import java.rmi.*;
//import java.rmi.Naming;
import java.util.Scanner;

public class Client {
	
    
	//@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
    	Scanner keyboardSc= new Scanner(System.in);
    	int winner = 0;
    	int player = 1;
    	int replay =1;
		
		//if(System.getSecurityManager() == null){
		//	System.setSecurityManager(new RMISecurityManager());
		//}else{
		//	System.out.println("Already has a security manager, so cant set RMI SM");
		//	}
		
		TTTService ttt = null;
		
    	try{
    		
    		ttt = (TTTService) Naming.lookup("//localhost:1099/TTT");
    		System.out.println("Found Server.");	
    		
    		int play;
    		boolean playAccepted;
    		do{
	    		do {
	    			player = ++player % 2;
	    			do {
	    				System.out.println(ttt.currentBoard());
	
	    				do {
	    					System.out.printf("\nPlayer %d, please enter the number of the square "
	    									+ "where you want to place your %c (or 0 to refresh the board): \n",
	    									player, (player == 1) ? 'X' : 'O');
	    					play = keyboardSc.nextInt();
	    				} while (play > 9 || play < 0);
	    				
	    				if (play != 0) {
	    					playAccepted = ttt.play( --play / 3, play % 3, player);
	    					if (!playAccepted)
	    						System.out.println("Invalid play! Try again.");
	    				} else
	    					playAccepted = false;
	    			} while (!playAccepted);
	    			winner = ttt.checkWinner();
	    		} while (winner == -1);
    		
    		if (winner == 2)
    			System.out.printf("\nHow boring, it is a draw\n");
    		else
    			System.out.printf(
    					"\nCongratulations, player %d, YOU ARE THE WINNER!\n",
    					winner);
    		
    		System.out.println("\nReplay?\nY[1]\nN[0]");
    		replay=keyboardSc.nextInt();
    		//ttt.restart();
    		}while(replay!=0);
    	}catch(RemoteException e){
    		System.out.println("allShapes: " + e.getMessage());	
    	}
    }

}
