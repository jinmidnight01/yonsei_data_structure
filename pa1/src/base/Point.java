public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	@Override
	public boolean equals(Object otherObject){
		if(otherObject instanceof Point){
			Point otherPoint = (Point)otherObject;
			return otherPoint.x == x && otherPoint.y == y;
		}
		return false;
	}
}
