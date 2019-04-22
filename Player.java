
public class Player {
	private int id; // 1: player1, -1: player2
	private String color; // 1: 白 -1: 黒
	private int count; // 自分の石の数

	public Player() {}
	public Player(int id) {
		this.id = id;
		this.color = id == 1? "白" : "黒";
		this.count = 0;
	}

	public int getCount() {
		return this.count;
	}

	public String getColor() {
		return this.color;
	}

	public boolean put(int x, String sy, Board b) {
		int y = convertX(sy);
		if (y == -1 || x < 1 || x > 8) {
			System.out.println("想定外の入力が入っています");
			return false;
		}
		if (!b.setStone(x - 1, y - 1, this.id)) {
			return false;
		}
		return true;
	}

	static int convertX(String x) {
		int num = -1;
		switch(x) {
		case "a":
			num = 1; break;
		case "b":
			num = 2; break;
		case "c":
			num = 3; break;
		case "d":
			num = 4; break;
		case "e":
			num = 5; break;
		case "f":
			num = 6; break;
		case "g":
			num = 7; break;
		case "h":
			num = 8; break;
		}
		return num;
	}
}
