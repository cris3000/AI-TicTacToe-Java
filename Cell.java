package tictactoe;

public class Cell {

	
	
	private int x;
	private int y;
	private int minimaxValue;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the minimaxValue
	 */
	public int getMinimaxValue() {
		return minimaxValue;
	}

	/**
	 * @param minimaxValue the minimaxValue to set
	 */
	public void setMinimaxValue(int minimaxValue) {
		this.minimaxValue = minimaxValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "("+this.x + ", "+this.y + ")";
	}
	
	
}
