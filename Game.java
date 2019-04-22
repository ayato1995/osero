import java.util.Scanner;

public class Game {
	Player[] player;
	Board board;
	BoardGUI bgui;

	public Game() {
		this.player = new Player[2];
		this.player[0] = new Player(1);
		this.player[1] = new Player(-1);
		this.board = new Board();
		bgui = new BoardGUI(this.player);
		bgui.setVisible(true);
		bgui.setLabels(this.board.getBoard());
	}
	public void start() {
		int player = 0;
		// new AlertDialog(bgui, "test");
		Scanner scan = new Scanner(System.in);
		while (true) {
			this.board.printBoad();
			boolean flag = false;
			System.out.println(this.player[player].getColor() + " の番です");
			do {
				System.out.println("縦軸を 1 - 8, 横軸を a - h で入力してください");
				flag = this.player[player].put(this.bgui, scan.nextInt(), scan.next(), this.board);
			} while(!flag);
			player = player == 1 ? 0 : 1;
			if (this.board.getTotalStone() == this.board.MAXNUM) break;
			else if (this.board.checkPut()) break;
		}
		scan.close();
		this.printWinner();
	}

	public void printWinner() {
		int p1Num = this.board.countStone(this.player[0].getId());
		int p2Num = this.board.countStone(this.player[1].getId());

		String player1 = player[0].getColor() + " : " + p1Num;
		String player2 = player[1].getColor() + " : " + p2Num;
		System.out.println(player1);
		System.out.println(player2);


		if (p1Num > p2Num) {
			new ResultDialog(this.bgui, player1 + "\n" + player2 + "\n" + player[0].getColor() + " の勝利です");
			System.out.println(player[0].getColor() + " の勝利です");
		} else if (p1Num < p2Num) {
			new ResultDialog(this.bgui, player1 + "\n" + player2 + "\n" + player[1].getColor() + " の勝利です");
			System.out.println(player[1].getColor() + " の勝利です");
		} else {
			new ResultDialog(this.bgui, player1 + "\n" + player2 + "\n" + "引き分けです");
			System.out.println("引き分けです");
		}
	}
}
