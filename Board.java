
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

	public Stone[][] getBoard() {
		return this.board;
	}

	public void incTotalStone() {
		this.totalStone++;
	}

	public int getTotalStone() {
		return this.totalStone;
	}

	public boolean setStone(BoardGUI owner, int x, int y, int id) {
		if (this.board[x][y] != null) {
			new AlertDialog(owner, "すでに石が置かれています");
			System.out.println("すでに石が置かれています");
			return false;
		}
		if (!this.checkReverse(x, y, id)) {
			new AlertDialog(owner, "そこに置くことはできません");
			System.out.println("そこに置くことはできません");
			return false;
		}
		this.board[x][y] = new Stone(id);
		this.reverseStone(x, y, id);
		return true;
	}

	public boolean checkPut() {
		if (this.totalStone < this.MAXNUM) {
			return false;
		}
		return true;
	}

	boolean checkReverse(int x, int y, int id) {
		if (this.checkPHorizontal(x, y, id)) {
			// System.out.println("ph");
			return true;
		} else if (this.checkNHorizontal(x, y, id)) {
			// System.out.println("nh");
			return true;
		} else if (this.checkPVertical(x, y, id)){
			// System.out.println("pv");
			return true;
		} else if (this.checkNVertical(x, y, id)){
			// System.out.println("nv");
			return true;
		} else if (this.checkPPDiagonal(x, y, id)) {
			// System.out.println("ppd");
			return true;
		} else if (this.checkPNDiagonal(x, y, id)) {
			// System.out.println("pnd");
			return true;
		} else if (this.checkNPDiagonal(x, y, id)) {
			// System.out.println("npd");
			return true;
		} else if (this.checkNNDiagonal(x, y, id)) {
			// System.out.println("nnd");
			return true;
		}
		return false;
	}

	void reverseStone(int x, int y, int id) {
		this.reversePHorizontal(x,  y, id);  // 横軸の正
		this.reverseNHorizontal(x,  y, id);  // 横軸の負
		this.reversePVertical(x, y, id);  // 縦軸の正
		this.reverseNVertical(x, y, id);  // 縦軸の負
		this.reversePPDiagonal(x, y, id);   // 斜め軸の正正
		this.reverseNNDiagonal(x, y, id);   // 斜め軸の負負
		this.reversePNDiagonal(x, y, id);   // 斜め軸の正負
		this.reverseNPDiagonal(x, y, id);   // 斜め軸の負正
	}

	boolean checkPHorizontal(int x, int y, int id) {
		for (int i = y + 1; i < this.board[x].length; i++) {
			if (this.board[x][i] == null) return false;
			if (this.board[x][i].getColor() == id) {
				if (i != (y + 1)) return true;
			}
		}
		return false;
	}

	boolean checkNHorizontal(int x, int y, int id) {
		for (int i = y - 1; i >= 0; i--) {
			if (this.board[x][i] == null) return false;
			if (this.board[x][i].getColor() == id) {
				if (i != (y - 1)) return true;
			}
		}
		return false;
	}

	// boolean check(int pORn , int i){
	// 	if(pORn > 0){
	// 		return i < this.board[x].length;
	// 	}else{
	// 		return i >= 0;
	// 	}
	// }

	void reversePHorizontal(int x, int y, int id) {
		int dy = -1;
		for (int i = y + 1; i < this.board[x].length; i++) {
			if (this.board[x][i] == null) break;
			if (this.board[x][i].getColor() == id) {
				if (i != (y + 1)) {
					dy = i;
					break;
				}
			}
		}
		if (dy != -1) {
			for (int i = y + 1; i < dy; i++) {
				this.board[x][i].reverse();
			}
		}
	}

	void reverseNHorizontal(int x, int y, int id) {
		int dy = -1;
		for (int i = y - 1; i >= 0; i--) {
			if (this.board[x][i] == null) break;
			if (this.board[x][i].getColor() == id) {
				if (i != (y - 1)) {
					dy = i;
					break;
				}
			}
		}
		if (dy != -1) {
			for (int i = y - 1; i > dy; i--) {
				this.board[x][i].reverse();
			}
		}
	}

	boolean checkPVertical(int x, int y, int id) {
		for (int i = x + 1; i < this.board.length; i++) {
			if (this.board[i][y] == null) return false;
			if (this.board[i][y].getColor() == id) {
				if (i != (x + 1)) return true;
			}
		}
		return false;
	}

	boolean checkNVertical(int x, int y, int id) {
		for (int i = x - 1; i >= 0; i--) {
			if (this.board[i][y] == null) return false;
			if (this.board[i][y].getColor() == id) {
				if (i != (x - 1)) return true;
			}
		}
		return false;
	}

	void reversePVertical(int x, int y, int id) {
		int dx = -1;
		for (int i = x + 1; i < this.board.length; i++) {
			if (this.board[i][y] == null) break;
			if (this.board[i][y].getColor() == id) {
				if (i != (x + 1)) {
					dx = i;
					break;
				}
			}
		}
		if (dx != -1) {
			for (int i = x + 1; i < dx; i++) {
				this.board[i][y].reverse();
			}
		}
	}

	void reverseNVertical(int x, int y, int id) {
		int dx = -1;
		for (int i = x - 1; i >= 0; i --) {
			if (this.board[i][y] == null) break;
			if (this.board[i][y].getColor() == id) {
				if (i != (x - 1)) {
					dx = i;
					break;
				}
			}
		}
		if (dx != -1) {
			for (int i = x - 1; i > dx; i--) {
				this.board[i][y].reverse();
			}
		}
	}

	boolean checkPPDiagonal(int x, int y, int id) {
		int i, j;
		for (i = x + 1, j = y + 1; i < this.board.length && j < this.board[i].length; i++, j++) {
			if (this.board[i][j] == null) return false;
			if (this.board[i][j].getColor() == id) {
				if (i != (x + 1) && j != (y + 1)) return true;
			}
		}
		return false;
	}

	boolean checkPNDiagonal(int x, int y, int id) {
		int i, j;
		for (i = x + 1, j = y - 1; i < this.board.length && j >= 0; i++, j--) {
			if (this.board[i][j] == null) return false;
			if (this.board[i][j].getColor() == id) {
				if (i != (x + 1) && j != (j - 1)) return true;
			}
		}
		return false;
	}

	boolean checkNPDiagonal(int x, int y, int id) {
		int i, j;
		for (i = x - 1, j = y + 1; i >= 0 && j < this.board[i].length; i--, j++) {
			if (this.board[i][j] == null) return false;
			if (this.board[i][j].getColor() == id) {
				if (i != (x - 1) && j != (j + 1)) return true;
			}
		}
		return false;
	}

	boolean checkNNDiagonal(int x, int y, int id) {
		int i, j;
		for (i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
			if (this.board[i][j] == null) return false;
			if (this.board[i][j].getColor() == id) {
				if (i != (x - 1) && j != (y - 1)) return true;
			}
		}
		return false;
	}

	void reversePPDiagonal(int x, int y, int id) {
		int dx = -1;
		int dy = -1;
		int i, j;
		for (i = x + 1, j = y + 1; i < this.board.length && j < this.board[i].length; i++, j++) {
			if (this.board[i][j] == null) break;
			if (this.board[i][j].getColor() == id) {
				if (i != (x + 1) && j != (y + 1)) {
					dx = i;
					dy = j;
					break;
				}
			}
		}
		if (dx != -1 && dy != -1) {
			for (i = x + 1, j = y + 1; i < dx && j < dy; i++, j++) {
				this.board[i][j].reverse();
			}
		}
	}

	void reverseNNDiagonal(int x, int y, int id) {
		int dx = -1;
		int dy = -1;
		int i, j;
		for (i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
			if (this.board[i][j] == null) break;
			if (this.board[i][j].getColor() == id) {
				if (i != (x - 1) && j != (y - 1)) {
					dx = i;
					dy = j;
					break;
				}
			}
		}
		if (dx != -1 && dy != -1) {
			for (i = x - 1, j = y - 1; i > dx && j > dy; i--, j--) {
				this.board[i][j].reverse();
			}
		}
	}

	void reversePNDiagonal(int x, int y, int id) {
		int dx = -1;
		int dy = -1;
		int i, j;
		for (i = x + 1, j = y - 1; i < this.board.length && j >= 0; i++, j--) {
			if (this.board[i][j] == null) break;
			if (this.board[i][j].getColor() == id) {
				if (i != (x + 1) && j != (y - 1)) {
					dx = i;
					dy = j;
					break;
				}
			}
		}
		if (dx != -1 && dy != -1) {
			for (i = x + 1, j = y - 1; i < dx && j > dy; i++, j--) {
				this.board[i][j].reverse();
			}
		}
	}

	void reverseNPDiagonal(int x, int y, int id) {
		int dx = -1;
		int dy = -1;
		int i, j;
		for (i = x - 1, j = y + 1; i >= 0 && j < this.board[i].length; i--, j++) {
			if (this.board[i][j] == null) break;
			if (this.board[i][j].getColor() == id) {
				if (i != (x - 1) && j != (y + 1)) {
					dx = i;
					dy = j;
					break;
				}
			}
		}
		if (dx != -1 && dy != -1) {
			for (i = x - 1, j = y + 1; i > dx && j < dy; i--, j++) {
				this.board[i][j].reverse();
			}
		}
	}

	public int countStone(int id) {
		int cnt = 0;
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				if (this.board[i][j] != null && this.board[i][j].getColor() == id) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	public void printBoad() {
		final String HORIZONTAL = "-------------------------------------------";
		System.out.println("  | a  | b  | c  | d  | e  | f  | g  | h  |");
		System.out.println(HORIZONTAL);
		for (int i = 0; i < this.board.length; i++) {
			System.out.print(i + 1 + " |");
			for (int j = 0; j < this.board[i].length; j++) {
				if (this.board[i][j] == null) {
					System.out.print(" 　 |");
				} else {
					System.out.print(" " + this.board[i][j].color() + " |");
				}
			}
			System.out.println();
			System.out.println(HORIZONTAL);
		}
	}

}
