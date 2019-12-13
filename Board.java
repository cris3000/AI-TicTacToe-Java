package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
	
	private List<Cell> emptyCells;
	private Scanner scanner;
	private Player[][] board;
	private List<Cell> rootValues;
	
	public Board() {
		initializeBoard();
		
	}

	private void initializeBoard() {
		this.rootValues = new ArrayList<>();
		this.scanner = new Scanner(System.in);
		this.board = new Player[Constants.Board_Size][Constants.Board_Size];
		
	}

	public boolean isRunning() {
		
		if(isWinning(Player.COMPUTER)) {
			return false;
		}
		if(isWinning(Player.USER)) {
			return false;
		}
		if(getEmptyCells().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

	public List<Cell> getEmptyCells() {

		emptyCells = new ArrayList<>();
		
		for(int i=0;i<Constants.Board_Size;i++) {
			for(int j=0;j<Constants.Board_Size;j++) {
				if(board[i][j] == Player.NONE) {
					emptyCells.add(new Cell(i,j));
				}
			}
		}
		
		return emptyCells;
	}

	public boolean isWinning(Player player) {
		
		if(board[0][0] == player && board[1][1]==player && board[2][2]==player) {
			return true;
		}
		if(board[0][2] == player && board[1][1]==player && board[2][0]==player) {
			return true;
		}
		
		
		for (int i=0; i<Constants.Board_Size;i++) {
			if(board[i][0] == player && board[i][1] == player && board[i][2]== player) {
				return true;
			}
			if(board[0][i] == player && board[1][i] == player && board[2][i]== player) {
				return true;
			}
		}
		return false;
	}
	
	public void move(Cell cell, Player player) {
		this.board[cell.getX()][cell.getY()] = player;
	}
	
	public Cell getBestMove() {
		int max = Integer.MIN_VALUE;
		int best = Integer.MIN_VALUE;
		
		for(int i=0;i<rootValues.size();i++) {
			if(max < rootValues.get(i).getMinimaxValue()) {
				max = rootValues.get(i).getMinimaxValue();
				best = i; 
			}
		}
		
		return rootValues.get(best);
	}
	
	public void makeUserInput() {
		System.out.println("Users move");
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		Cell cell = new Cell(x,y);
		move(cell,Player.USER);
	}
	
	public void displayBoard() {
		System.out.println();
		
		for(int i=0;i<Constants.Board_Size;i++) {
			for (int j=0;j<Constants.Board_Size;j++) {
				System.out.println(board[i][j]);
			}
			System.out.println();
		}
	}
	
	public int returnMin(List<Integer> list) {
		int min = Integer.MAX_VALUE;
		int index = Integer.MIN_VALUE;
		
		for(int i=0;i<list.size();i++) {
			if (list.get(i)< min) {
				min = list.get(i);
				index = i;
			}
		}
		return list.get(index);
	}
	
	public int returnMax(List<Integer> list) {
		int max = Integer.MIN_VALUE;
		int index = Integer.MIN_VALUE;
		
		for(int i=0;i<list.size();i++) {
			if (list.get(i) > max) {
				max = list.get(i);
				index = i;
			}
		}
		return list.get(index);
	}
	
	public void callMiniMax(int depth, Player player) {
		rootValues.clear();
		minimax(depth, player);
	}

	private int minimax(int depth, Player player) {
		
		if(isWinning(Player.COMPUTER)) {
			return +1;
		}
		if(isWinning(Player.USER)) {
			return -1;
		}
		
		List<Cell> availableCells = getEmptyCells();
		
		if(availableCells.isEmpty() ) {
			return 0;
		}
		
		List<Integer> scores = new ArrayList<>();
		
		for (int i=0; i < availableCells.size();i++){
			Cell point = availableCells.get(i);
			
			if(player == Player.COMPUTER) {
				move(point, Player.COMPUTER);
				int currentScore = minimax(depth+1, Player.USER);
				scores.add(currentScore);
				
				if(depth ==0) {
					point.setMinimaxValue(currentScore);
					rootValues.add(point);
				}
			}else if(player == Player.USER) {
				move(point, Player.USER);
				scores.add(minimax(depth+1, Player.COMPUTER)); 
			}
			
			board[point.getX()][point.getY()] = Player.NONE;
		}
		
		if (player== Player.COMPUTER) {
			return returnMax(scores);
		}
		
		return returnMin(scores);
	}  
	
	public void setupBoard() {
		for (int i=0; i<Constants.Board_Size;i++) {
			for(int j=0; j<Constants.Board_Size;j++) {
				board[i][j] = Player.NONE;
			}
		}
	}

	/**
	 * @return the scanner
	 */
	public Scanner getScanner() {
		return scanner;
	}

	/**
	 * @param scanner the scanner to set
	 */
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * @return the board
	 */
	public Player[][] getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Player[][] board) {
		this.board = board;
	}

	/**
	 * @return the rootValues
	 */
	public List<Cell> getRootValues() {
		return rootValues;
	}

	/**
	 * @param rootValues the rootValues to set
	 */
	public void setRootValues(List<Cell> rootValues) {
		this.rootValues = rootValues;
	}

	/**
	 * @param emptyCells the emptyCells to set
	 */
	public void setEmptyCells(List<Cell> emptyCells) {
		this.emptyCells = emptyCells;
	}
	
	
}
