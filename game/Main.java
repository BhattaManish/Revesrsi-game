package game;

import game.Simulation;
import game.game_attribute.*;

import java.util.Scanner; 

public class Main{
	public static void main(String [] args){
		String turn= null;
		boolean checkInput = false;


		Simulation simulate = new Simulation();
		ReversiGame game = new ReversiGame();
		Scanner scan = new Scanner(System.in);


		ReversiElement player = ReversiElement.X;
		do{
			game.printBox();
			System.out.println("Value of X "+game.scoreX()+":"+game.scoreO()+" O");
			do{
				System.out.print("Player "+player+"'s Turn: ");
				turn = scan.nextLine();
				if(!(checkInput = simulate.turnValid(game,player,turn)) ) System.out.println("Invalid Turn");
			}while(checkInput != true);
			player = (player == ReversiElement.X) ? ReversiElement.O : ReversiElement.X;
			game.updateCount();
		}while(!simulate.isPlaceAvilable(game,player));
		// game ends
		game.printBox();
		game.updateCount();
		if(game.scoreX() > game.scoreO()){
			System.out.println("Winner is Player X !");
		}else if(game.scoreX() < game.scoreO()){
			System.out.println("Winner is Player O !");
		}else{
			System.out.println("The Match is a Tie !");
		}
		
	}
}
