package sokoban.model;

import java.util.ArrayList;
import java.util.List;


public class Warehouse {

	public static final Integer Block = 1;
	public static final Integer Storage = 2;
	public static final Integer Empty = 3;
	Integer[][] map;	
	public enum Movement {Right, Left, Up, Down};
	Player p;
	
	
	public List<Box> boxs;
	
	public Warehouse(Integer[][] map) {
		this.map = map;
		boxs = new ArrayList<Box>();
	}
	
	public void addBox(Box b) {
		this.boxs.add(b);
	}
	
	public Integer[][] getMap() {
		return this.map;
	}


	public void setPlayer(Player p) {
		this.p = p;
	}

	
	public void movePlayer(Movement m) {
		int x = this.p.getX();
		int y = this.p.getY();
		int a = 0;
		int b = 0;
		if (m == Movement.Right) {
			a = +1;
		}
		if (m == Movement.Left) {
			a = -1;
		}	
		if (m == Movement.Up) {
			b = -1;
		}
		if (m == Movement.Down) {
			b = +1;
		}	
		if (this.map[y+b][x + a] == Warehouse.Block)
			return;
		Box box = searchBoxIn(x+a, y+b);
		if (box == null) {
			this.p.addX(a);
			this.p.addY(b);
			return;
		}
		if ((this.map[y+b+b][x+a+a] == Warehouse.Block)
				||	(searchBoxIn(x+a+a, y+b+b) != null))
			return;
		this.p.addX(a);
		this.p.addY(b);
		box.addX(a);
		box.addY(b);
	}

	
	Box searchBoxIn(int x, int y) {
		if (!this.boxs.isEmpty()) {
			for(Box b: boxs) {
				if ((b.getX() == x) && (b.getY() == y)) {
					return b;
				}
			}
		}
		return null;
	}

	public Boolean isGameOver() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == Storage) {
					if (this.searchBoxIn(j, i) == null) {
						return false;
					}
				}
			}
		}
		return true;
	}

	
	
	

}
