
public class Board {
	private Stone[][] board;
	private int totalStone;
	final int MAXNUM = 8 * 8;

	public Board() {
		this.board = new Stone[8][8];
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				Stone s = null;
				if (i == 3 && j == 3) s = new Stone(-1);
				else if (i == 3 && j == 4) s = new Stone(1);
				else if (i == 4 && j == 4) s = new Stone(-1);
				else if (i == 4 && j == 3) s = new Stone(1);
				this.board[i][j] = s;
			}
		}
		this.totalStone = 0;
	}

	public void incTotalStone() {
		this.totalStone++;
	}

	public int getTotalStone() {
		return this.totalStone;
	}

	public boolean setStone(int x, int y, int id) {
		if (this.board[x][y] != null) {
			System.out.println("すでに石が置かれています");
			return false;
		}
		if (!this.checkReverse(x, y, id)) {
			System.out.println("そこに置くことはできません");
			return false;
		}
		this.board[x][y] = new Stone(id);
		return true;
	}

	public boolean checkPut() {
		if (this.totalStone < this.MAXNUM) {
			return false;
		}
		return true;
	}

	boolean checkReverse(int x, int y, int id) {
		if (this.checkHorizontal(x, y, id)) return true;
		else if (this.checkVertical(x, y, id)) return true;
		else if (this.checkDiagonal(x, y, id)) return true;
		return false;
	}

	boolean checkHorizontal(int x, int y, int id) {
		for (int i = y + 1; i < this.board[x].length; i++) {
			if (this.board[x][i] == null) break;
			if (id == this.board[x][i].getColor()) {
				if (i != (y + 1)) {
					return true;
				} else {
					break;
				}
			}
		}
		for (int i = y - 1; i >= 0; i--) {
			if (this.board[x][i] == null) break;
			if (id == this.board[x][i].getColor()) {
				if (i != (y - 1)) {
					return true;
				} else {
					break;
				}
			}
		}
		return false;
	}

	boolean checkVertical(int x, int y, int id) {
		for (int i = x + 1; i < this.board.length; i++) {
			if (this.board[i][y] == null) break;
			if (id == this.board[i][y].getColor()) {
				if (i != (x + 1)) {
					return true;
				} else {
					break;
				}
			}
		}
		for (int i = x - 1; i >= 0; i--) {
			if (this.board[i][y] == null) break;
			if (id == this.board[i][y].getColor()) {
				if (i != (x - 1)) {
					return true;
				} else {
					break;
				}
			}
		}
		return false;
	}

	boolean checkDiagonal(int x, int y, int id) {
		int i = x + 1;
		int j = y + 1;
		for (; i < this.board.length || j < this.board[i].length; i++, j++) {
			if (this.board[i][j] == null) break;
			if (id == this.board[i][j].getColor()) {
				if (i != (x + 1) && j != (y + 1)) {
					return true;
				} else {
					break;
				}
			}
		}
		i = x - 1;
		j = y - 1;
		for (; i > 0 || j > 0; i--, j--) {
			if (this.board[i][j] == null) break;
			if (id == this.board[i][j].getColor()) {
				if (i != (x - 1) && j != (y - 1)) {
					return true;
				} else {
					break;
				}
			}
		}
		return false;
	}

	public void printBoad() {
		final String HORIZONTAL = "----------------------------------";
		System.out.println("  | a | b | c | d | e | f | g | h |");
		System.out.println(HORIZONTAL);
		for (int i = 0; i < this.board.length; i++) {
			System.out.print(i + 1 + " |");
			for (int j = 0; j < this.board[i].length; j++) {
				if (this.board[i][j] == null) {
					System.out.print(" 0 |");
				} else {
					System.out.print(" " + this.board[i][j].color() + " |");
				}
			}
			System.out.println();
			System.out.println(HORIZONTAL);
		}
	}

}
