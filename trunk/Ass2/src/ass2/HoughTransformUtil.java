package ass2;

import java.awt.Point;
import java.util.ArrayList;

public class HoughTransformUtil {
	
	ArrayList<Circle> circles = null;
	int[][][] acc;
	
	public HoughTransformUtil(ArrayList<Point> points, int height, int width, int radius, int Ncircles) {
		
		circles = new ArrayList<Circle>();
		
		acc = new int[height][width][2];
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				acc[i][j][0] = 0;
				acc[i][j][1] = 0;
			}
		}
		int x0, y0;
		double t;
		for (Point p : points) {
			System.out.println(p.x + " "+ p.y);
			for (int i=0; i<height; i++) {
				for (int j=0; j<width; j++) {
					acc[i][j][1] = 0;
				}
			}
			for (int theta=0; theta<360; theta++) {
				t = (theta * 3.14159265) / 180;
				x0 = (int)Math.round(p.x - radius * Math.cos(t));
				y0 = (int)Math.round(p.y - radius * Math.sin(t));
				if(x0 < width && x0 > 0 && y0 < height && y0 > 0) {
					if (acc[y0][x0][1]!=1) {
						acc[y0][x0][0]+=1;
						acc[y0][x0][1]=1;
					}
				}
			}
		}
		
		int max=0;
		int imax = 0;
		int jmax = 0;
		
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				acc[i][j][1] = 0;
			}
		}
		

		// Find max acc value
		for (int n=0; n<Ncircles; n++) {
			max = 0;
			imax = -1;
			jmax = -1;
			for (int i=0; i<height; i++) {
				for (int j=0; j<width; j++) {
					if ((acc[i][j][0] > max) && (acc[i][j][1] != 1) && (acc[i][j][0] >= 3)) {
						//System.out.println(acc[i][j][0]);
						max = acc[i][j][0];
						imax = i;
						jmax = j;
					}
				}
			}
			if (imax != -1) {
				acc[imax][jmax][1] = 1;
				Point p = new Point(jmax, imax);
				circles.add(new Circle(p, radius));
			}
		}
		
	}
	
	public ArrayList<Circle> getCircles() {
		return circles;
	}

}
