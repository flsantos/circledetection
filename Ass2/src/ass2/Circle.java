package ass2;

import java.awt.Point;


public class Circle {
	final Point center;
	final double radius;

	public Circle(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Center= ").append(center)
		.append(", r=").append(radius).toString();
	}
}
