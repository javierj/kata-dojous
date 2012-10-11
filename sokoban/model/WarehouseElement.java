package sokoban.model;

public class WarehouseElement {

	protected int x;
	protected int y;

	public WarehouseElement(int i, int j) {
		x = i;
		y = j;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
/*
	public void incX() {
		this.x++;
	}
*/	
	public boolean isIn(int i, int j) {
		return (x == i) && (y == j);
	}
/*
	public void decX() {
		this.x--;
		
	}
*/
	public void addX(int a) {
		x+=a;
		
	}

	public void addY(int b) {
		y += b;
	}

}

