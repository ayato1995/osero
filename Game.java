import java.util.Scanner;

public class Game {
	/*
	Player player1;
	Player player2;
	*/
	Player[] player;
	Board board;

	public Game() {
		this.player = new Player[2];
		this.player[0] = new Player(1);
		this.player[1] = new Player(-1);
		this.board = new Board();
	}
	public void start() {
		int player = 0;
		Scanner scan = new Scanner(System.in);
		while (true) {
			this.board.printBoad();
			boolean flag = false;
			System.out.println(this.player[player].getColor() + " の番です");
			do {
				System.out.println("縦軸を 1 - 8, 横軸を a - h で入力してください");
				flag = this.player[player].put(scan.nextInt(), scan.next(), this.board);
			} while(!flag);
			player = player == 1 ? 0 : 1;
			System.out.println(player);
			if (this.board.getTotalStone() == this.board.MAXNUM) break;
			else if (this.board.checkPut()) break;
		}
		scan.close();
		printWinner(this.player);
	}

	static void printWinner(Player[] player) {
		int p1Num = player[0].getCount();
		int p2Num = player[1].getCount();

		System.out.println("Player 1 : " + p1Num);
		System.out.println("Player 2 : " + p2Num);

		if (p1Num > p2Num)
			System.out.println("Player 1 の勝利です");
		else if (p1Num < p2Num)
			System.out.println("Player 2 の勝利です");
		else
			System.out.println("引き分けです");
	}
}
