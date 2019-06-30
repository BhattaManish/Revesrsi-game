package game.game_attribute;

public class ReversiGame{
	private ReversiElement array[][];
	private final int XMAX = 8;
	private final int YMAX = 8;
	private int countX;
	private int countO;

	/**
	 * Constrctor allocates the memory for the board
	 */
	public ReversiGame(){
		this.array = new ReversiElement[XMAX][YMAX];
		for (int y = 0; y<YMAX;y++ ) {
			for (int x = 0;x<XMAX ;x++ ) {
				setValue(x,y,ReversiElement.E);
		
			}
		}
		this.array[3][3] = ReversiElement.O;
		this.array[4][4] = ReversiElement.O;
		this.array[3][4] = ReversiElement.X;
		this.array[4][3] = ReversiElement.X;
		updateCount();
	}

	// getters and setters
	/**
	 * this funtion return the Object at the ceratin position
	 * @param x takes X value
	 * @param y takes y value
	 * @return if the Object at the position x,y
	 */
	public ReversiElement getValue(int x, int y){
		return this.array[x][y];
	}

	/**
	 * This funtion sets the Value at the givern position
	 * @param x takes X value
	 * @param y	takes the y value
	 * @param val takes Object that is to be kept at the Location
	 */
	public void setValue(int x, int y, ReversiElement val){
		this.array[x][y] = val;

	}

	/**
	 * this function return the score of X player
	 * @return an int value
	 */
	public int scoreX(){
		return this.countX;
	}
	/**
	 * this function return the score of O player
	 * @return an int value
	 */
	public int scoreO(){
		return this.countO;
	}
	/**
	 * this function works as a setter for the Score Variable
	 * @param valX  value of X player
	 * @param valO value of O variable
	 */
	private void setCountingValue(int valX, int valO){
		this.countX =valX;
		this.countO= valO;
	}
	/**
	 * this function is used to give the limit of the Object array
	 * @return int value
	 */
	public int  getXYMax(){
		return YMAX;
	}

	/**
	 * This function prints the Box
	 */
	public void printBox(){
		System.out.println(" |A|B|C|D|E|F|G|H|");
		for (int y=0; y<YMAX ;y++){
			System.out.print(y+"|");
			for (int x=0; x<XMAX;x++) {
				System.out.print(printValue(this.array[x][y])+"|");
			}
			System.out.println();
		}
	}
	/**
	 * this function is a helper fucntion for printBox
	 * @param ele takes the object
	 * @return a character of the Object
	 */
	private char printValue(ReversiElement ele){
		switch(ele){
			case O:  return 'O'; 
			case X:  return'X'; 
			default: return '_';
		}
	}
	/**
	 * this function checks if any variable is Outside the Limit
	 * @param x  value of x
	 * @param y  value of y
	 * @return is outside limit gives true else false
	 */
	public boolean outOfBound(int x, int y){
		return x < 0 || x >= XMAX || y < 0 || y >= YMAX ;
	}

	/*
	* this function just update the Score
	*/
	public void updateCount(){
		int count1=0,count2=0;
		for (int y = 0; y< YMAX ;y++ ) {
			for (int x = 0; x <XMAX ; x++ ) {
				if(getValue(x,y) == ReversiElement.O)count1++;
				if(getValue(x,y) == ReversiElement.X)count2++;
			}
		}
		setCountingValue(count2,count1);
	}



}