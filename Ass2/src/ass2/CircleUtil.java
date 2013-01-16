package ass2;

import java.awt.Point;

public class CircleUtil {
	static final double TOL = 0.0000001;

	public static Circle circleFromPoints(final Point p1, final Point p2, final Point p3) {
		final double offset = Math.pow(p2.x, 2) + Math.pow(p2.y, 2);
		final double bc = (Math.pow(p1.x, 2) + Math.pow(p1.y, 2) - offset) / 2.0;
		final double cd = (offset - Math.pow(p3.x, 2) - Math.pow(p3.y, 2)) / 2.0;
		final double det = (p1.x - p2.x) * (p2.y - p3.y) - (p2.x - p3.x)
				* (p1.y - p2.y);

		if (Math.abs(det) < TOL) {
			throw new IllegalArgumentException("Yeah, lazy.");
		}

		final double idet = 1 / det;

		final double centerx = (bc * (p2.y - p3.y) - cd * (p1.y - p2.y)) * idet;
		final double centery = (cd * (p1.x - p2.x) - bc * (p2.x - p3.x)) * idet;
		final double radius = Math.sqrt(Math.pow(p2.x - centerx, 2)
				+ Math.pow(p2.y - centery, 2));

		return new Circle(new Point((int)centerx, (int)centery), radius);
	}


/*	public static void main(String[] args) {
		Point p1 = new Point(0, 1);
		Point p2 = new Point(1, 0);
		Point p3 = new Point(2, 1);
		Circle c = circleFromPoints(p1, p2, p3);
		System.out.println(c);
	}*/

}
