
public class NQueen {
	int size = 8;
	int board[][];
	public NQueen() {
		this.board = new int[this.size][this.size];
		this.initializeBoard();
		System.out.println("Board initialized");
		//System.out.println(this.printBoard(this.board));
	}
	public static void main(String []args) {
		NQueen nqueen = new NQueen();
		//System.out.println("Playing..!");
		nqueen.placeQueen(0,0);
		System.out.println("Final output");
		System.out.println(nqueen.printBoard(nqueen.board));

	}
	public boolean placeQueen(int row, int numberofQueensPlaced) {
		//System.out.println("Called for row:" + numberofQueensPlaced);
		if(numberofQueensPlaced==this.size) return true;
		for(int i=0; i<this.size; i++) {
			//System.out.println("Called for row:" + row + " column:" + i);
			//System.out.println(this.printBoard(this.board));
			if(doesConflict(this.board, new Move(row, i))){
				//this.board[numberofQueensPlaced][i] = 0;
			} 
			else {
				this.board[numberofQueensPlaced][i]=1;
				if(numberofQueensPlaced==this.size) return true;
				//System.out.println("Queen placed at row: "+ row + " column:" +  i);
				//System.out.println(this.printBoard(this.board));
				if(this.placeQueen(row+1, numberofQueensPlaced+1)) {
					return true;
					//return true;
				} else {
					this.board[numberofQueensPlaced][i]=0;
				}
			}
		}
		return false;
	}

	public void initializeBoard() {
		int [][] board = this.board;
		for(int i=0;i<this.size;i++) {
			for(int j=0;j<this.size;j++) {
				board[i][j]=0;
			}
		}
	}
	public boolean doesConflict(int[][] board, Move move) {
		//System.out.println("Calculating conflict with move " + move);
		for(int i=0;i<this.size;i++) {
			for(int j=0;j<this.size;j++) {
				//For all Queens
				if(board[i][j]==1) {
					if(move.row==i || move.column==j) {
						//System.out.println("Conflicts left and right for row:" + move.row + " column: " + move.column);
						return true; //Conflicts left and right
					}
					if(Math.abs(i-move.row)==Math.abs(j-move.column)) {
						//System.out.println("Diagonal conflict for row:" + move.row + " column: " + move.column);
						return true; //Diagonal
					}
					if(Math.abs(i+j)==Math.abs(move.row+move.column)) {
						//System.out.println("Diagonal conflict for row:" + move.row + " column: " + move.column);
						return true; //Diagonal
					}
				}
			}
		}
		return false;
	}
	public String printBoard(int[][] board) {
		StringBuffer output=new StringBuffer("");
		for(int i=0;i<this.size;i++) {
			for(int j=0;j<this.size;j++) {
				output.append(board[i][j]+"\t");
			}
			output.append("\n");
		}
		return output.toString();
	}
}

class Move{
	int row;
	int column;
	public Move(int row, int column) {
		this.row=row;
		this.column=column;
	}
	public String toString() {
		return "Row: "+ this.row  + " Column :" + this.column;
	}
}