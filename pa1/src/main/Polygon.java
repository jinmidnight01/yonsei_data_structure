/*
 * Name: Jinhyo Park
 * Student ID #: 2018121022
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public class Polygon implements IPolygon {
	ICLL<Point> boundary;

	public Polygon(ICLL<Point> p) {
		boundary = p;
	}

	@Override
	public boolean pointInPolygon(Point p) {
		/*
		 * Input:
		 *	- point in 2D space
		 *
		 * Output:
		 *  - the number of elements in the linked list.
		 *
		 * Note: You should use the "Ray-Casting" algorithm described in the
		 *       handout.
		 *
		 * Note: Your ray should have slope 0, meaning it extends to infinity
		 *       directly to the right of the point (see edge case #4)
		 *
		 * Note: You DO NOT have to consider the following edge cases:
		 *          1) There are two points in the polygon with the same x and y
		 *          2) The test point lies on a straight line with two adjacent
		 *             points on the boundary, or lies exactly on the boundary
		 *          3) The test point has the same x and y as a point
		 *              already in the polygon
		 *          4) The ray intersecting the boundary at a vertex (where two
		 *             edges meet).
		 *                 i) This is the reason for the slope 0 ray
		 */
		int count = 0;
		for (int i = 0; i < boundary.size(); i++) {
			Point v1 = boundary.getHead();
			boundary.rotate(Direction.TO_NEXT);
			Point v2 = boundary.getHead();

			double intersection_x = (double) (p.getY() - v1.getY()) / (double) (v2.getY() - v1.getY()) * (double) (v2.getX() - v1.getX()) + (double) v1.getX();
			double intersection_y = p.getY();

			double min;
			double max;
			if (v1.getY() < v2.getY()) {
				min = v1.getY();
				max = v2.getY();
			}
			else {
				min = v2.getY();
				max = v1.getY();
			}

			if ((intersection_y > min) && (intersection_y < max) && (intersection_x > p.getX())) {
				count++;
			}
		}

        return count % 2 == 1;
	}
}

