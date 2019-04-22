
public class Stone {
	private int color; // block = -1, white = 1

	public Stone(int id) {
		this.color = id;
	}

	public int getColor() {
		return this.color;
	}

	public String color() {
		if (this.color == 1)
			return "白";
		else
			return "黒";
	}

	public boolean reverse() {
		this.color *= -1;
		return true;
	}
}
