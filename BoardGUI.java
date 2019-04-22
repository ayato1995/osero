import java.awt.*;
import java.awt.event.*;

class BoardGUI extends Frame {
	private MathBotton[][] btn;

	public BoardGUI(Player[] p) {
		this.setSize(80 * 8, 80 * 8);
		this.setLayout(new GridLayout(8, 8));
		this.setLocationRelativeTo(null);
		this.setTitle("おせろ");
		this.addWindowListener(new MyWindowAdapter());
		btn = new MathBotton[8][8];
		for (int i = 0; i < this.btn.length; i++) {
			for (int j = 0; j < this.btn[i].length; j++) {
				btn[i][j] = new MathBotton(this, i, j, p);
				add(btn[i][j]);
			}
		}
	}

	public void setLabels(Stone[][] s) {
		for (int i = 0; i < this.btn.length; i++) {
			for (int j = 0; j < this.btn.length; j++) {
				if (s[i][j] == null) continue;
				btn[i][j].setLabel(s[i][j].color());
			}
		}
	}
}

class MyWindowAdapter extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}

class MathBotton extends Button implements ActionListener {
	private BoardGUI bgui;
	private int x;
	private int y;
	private Player[] p;
	private Board board;
	private int id;
	// eventListenerを毎回登録し直す
	// removeActionListener(this);

	public MathBotton(BoardGUI bgui, int x, int y, Player[] p) {
		this.bgui = bgui;
		this.x = x;
		this.y = y;
		this.p = p;
		this.addActionListener(this);
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean actionPerformed(ActionEvent ae) {
		System.out.println(x + " " + y);
		return this.p[id].put(this.bgui, x, y, this.board);
	}

}

class AlertDialog extends Dialog {
	Label l;

	public AlertDialog(Frame owner, String str) {
		super(owner, "alert");
		this.setSize(200, 100);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		Panel p = new Panel();
		l = new Label(str);
		p.add(l);
		this.add(p);
		this.setVisible(true);
	}
}

class ResultDialog extends Dialog {
	Label l;

	public ResultDialog(Frame owner, String str) {
		super(owner, "result");
		this.setSize(200, 100);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new MyWindowAdapter());
		Panel p = new Panel();
		l = new Label(str);
		p.add(l);
		this.add(p);
		this.setVisible(true);
	}
}