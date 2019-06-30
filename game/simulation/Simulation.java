package game.simulation;
import game.game_attribute.*;

public class Simulation{
	/**
	 * this funcion checks the String if valid  turns the Object fom 
	 * @param game takes the game object 
	 * @param currPlayer is current player
	 * @param str  takes the string from thw input
	 * @return  if sucessfully eceuted return true else false
	 */
	public boolean turnValid(ReversiGame game, ReversiElement currPlayer,String str){
		int inputArray[] = null;
		if((inputArray = checkString(str)) != null){
			if((game.getValue(inputArray[0],inputArray[1]) == ReversiElement.E) && isPositionValid(game,currPlayer,inputArray)){
				turnAllfields(game,currPlayer,inputArray);
				return true;
			}

		}
		return false;
	}
	/**
	 * this funtion takes the User array and checks validity and gives back a array
	 * @param str takse the User string
	 * @return gives a int array 
	 */
	public int [] checkString(String str){
		int arr[] = null;
		str = str.trim();
		if( str.trim().length() == 2){
			if(str.charAt(0) >= 'A' && str.charAt(0) <= 'H' || str.charAt(0) >='a' && str.charAt(0) <='h'){
				if(Character.getNumericValue(str.charAt(1)) >= 0 && Character.getNumericValue(str.charAt(1)) <= 9){
					arr = new int[2];
					if( str.charAt(0) >='a' && str.charAt(0) <='h'){
						arr[0]=str.charAt(0)-'a';
					}else{
						arr[0]= str.charAt(0)-'A';
					}
					arr[1] = str.charAt(1)-'0';
				}
			}
		}
		return arr;
	}
	/**
	 * this function checks the the user Place is valid
	 * @param game  takes the game Object to get the Game data
	 * @param currPlayer  is the current Player accesing it
	 * @param inputArray	is the location where the Value is supposed to be inserted
	 * @return if successful gives true else false
	 */

	public boolean isPositionValid(ReversiGame game, ReversiElement currPlayer, int inputArray[]){
		boolean stats =false;
			int [][] vectors = {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
			for ( int [] vector: vectors ) {
				if(!stats && legalDir(vector[0],vector[1],game,currPlayer,inputArray)){
					stats = true;
					break;
				}	
			}

		return stats;
	}

	/**
	 *  this function checks the validity form one place to onother 
	 * @param dx the value that is to be substracted with x
	 * @param dy the value that is to be substracted with y
	 * @param game takes the game Object to get the Game data
	 * @param currPlayer  is the current Player accesing it
	 * @param inputArray is the location where the Value is supposed to be inserted
	 * @return if successful gives true else false
	 */

	private boolean legalDir(int dx, int dy, ReversiGame game, ReversiElement currPlayer, int inputArray[]){
		int x = inputArray[0];
		int y = inputArray[1];
		ReversiElement nextPlayer = (currPlayer == ReversiElement.X)? ReversiElement.O: ReversiElement.X;
		// first checks if the value goes outside the range then cheks if the next place belongs to the next Player

		if(!game.outOfBound((x+=dx),(y+=dy)) ){
			if(game.getValue(x,y) == nextPlayer){
				while(!game.outOfBound(x,y)){
					if(game.getValue(x,y) == currPlayer)  return true;
					x += dx;
					y += dy;
				}
			}
		}
			
		return false;
	}

	/**
	 * this function checks the Object in  between
	 * @param game  takes the game Object to get the Game data
	 * @param currPlayer  is the current Player accesing it
	 * @param inputArray	is the location where the Value is supposed to be inserted
	 */
	private void turnAllfields(ReversiGame game, ReversiElement currPlayer,int inputArray[]){
		int [][] vectors = {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
		for (int [] vector: vectors){
			int x = inputArray[0];
			int y = inputArray[1];
			if(legalDir(vector[0],vector[1],game,currPlayer,inputArray)){
				do{
					game.setValue(x,y,currPlayer);
					x+=vector[0];
					y+=vector[1];
				}while(game.getValue(x,y) != currPlayer);
			}
		}
	}

	/**
	 * this function checks if the game is over or Is there place to enter for the next User
	 * @param game  takes the game Object to get the Game data
	 * @param player is the current Player accesing it
	 * @return if successful gives true else false
	 */
	public boolean isPlaceAvilable(ReversiGame game,ReversiElement player){
		if(checkFull(game)) return true; // checks if all the Places are taken
		int arr[] = null;
		for (int y = 0; y<game.getXYMax() ; y++){
			for (int x = 0;  x < game.getXYMax();x++){
				if(game.getValue(x,y) == ReversiElement.E){
					arr = new int[2];
					arr[0] =x;
					arr[1] =y;
					if(isPositionValid(game,player,arr)){
						return false;
					}
				}
			}
		}
		return true;
	}
	/**
	 * this function checks if there is  place to enter for the next User
	 * @param game takes the game Object to get the Game data
	 * @return if successful gives true else false
	 */
	boolean checkFull(ReversiGame game){
		boolean element= true;
		for (int y = 0; y<game.getXYMax() ; y++){
			for (int x = 0;  x < game.getXYMax();x++){
				if(game.getValue(x,y) == ReversiElement.E){
					element = false;
					break;
				} 
			}
		}
		return element;
	}
}
