package ass2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import Jama.Matrix;

public class RansacUtil {
	
	final private static int minRadius = 1;
	final private static int maxRadius = 100;

	public static ArrayList<Point> findCircle(Matrix m, int trials, int dth, int percent) {
		
		int minPoints = (int)(m.getRowDimension()*percent/100);
		//sel = []
		
		for (int i = 0; i < trials; i++) {
			Random random = new Random();
			int index1 = random.nextInt(m.getRowDimension());
			
			int index2 = random.nextInt(m.getRowDimension());
			while (index2 == index1)
				index2 = random.nextInt(m.getRowDimension());
			
			int index3 = random.nextInt(m.getRowDimension());
			while (index3 == index2 || index3 == index1)
				index3 = random.nextInt(m.getRowDimension());
			
			Point p1 = new Point((int)m.get(index1, 0), (int)m.get(index1, 1));
			Point p2 = new Point((int)m.get(index2, 0), (int)m.get(index2, 1));
			Point p3 = new Point((int)m.get(index3, 0), (int)m.get(index3, 1));
			
			Circle c = CircleUtil.circleFromPoints(p1, p2, p3);
			
			if (c.radius >= minRadius && c.radius <= maxRadius) {
				Matrix d;
				double[][] aux = new double[m.getRowDimension()][2];
				for (int j = 0; j < m.getRowDimension(); j++) {
					aux[j][0] = c.center.x;
					aux[j][1] = c.center.y;
				}
				d = new Matrix(aux);
				
				
				d = m.minus(d);
				d = abs(sqrt(sum(multiply(d.transpose(),d.transpose())).transpose()).minus(new Matrix(m.getRowDimension(), 1, c.radius)));
				
				ArrayList<Integer> indices = new ArrayList<Integer>();
				for (int k = 0; k < d.getRowDimension(); k++) {
					if (d.get(k, 0) <= dth) {
						indices.add(k);
					}
				}
				
				
				if (indices.size() > minPoints) {
					ArrayList<Point> points = new ArrayList<Point>();
					for (Integer ind : indices) {
						points.add(new Point((int)Math.round(m.get(ind, 0)),(int)Math.round(m.get(ind, 1))));
					}
					return points;
				}
				
			}
		}
		
		System.out.println("NO SUCH CIRCLE FOUND!!!");
		return new ArrayList<Point>();
		
		
	}
	
	
	//Multiplication entry-by-entry
	private static Matrix multiply(Matrix mat1, Matrix mat2) {
		Matrix ret = new Matrix(mat1.getRowDimension(), mat1.getColumnDimension());
		for (int i = 0; i < mat1.getRowDimension(); i++) {
			for (int j = 0; j < mat1.getColumnDimension(); j++) {
				ret.set(i, j, mat1.get(i, j)*mat2.get(i, j));
			}
		}
		return ret;
	}

	private static Matrix abs(Matrix mat) {
		for (int i = 0; i < mat.getRowDimension(); i++) {
			for (int j = 0; j < mat.getColumnDimension(); j++) {
				mat.set(i, j, Math.abs(mat.get(i, j)));
			}
		}
		return mat;
	}

	private static Matrix sqrt(Matrix mat) {
		for (int i = 0; i < mat.getRowDimension(); i++) {
			for (int j = 0; j < mat.getColumnDimension(); j++) {
				mat.set(i, j, Math.sqrt(mat.get(i, j)));
			}
		}
		return mat;
	}

	private static Matrix sum(Matrix mat) {
		Matrix ret = new Matrix(1, mat.getColumnDimension());
		for (int i = 0; i < mat.getColumnDimension(); i++) {
			double sum = 0;
			for (int j = 0; j < mat.getRowDimension(); j++) {
				sum = sum + mat.get(j, i);
			}
			ret.set(0, i, sum);
		}
		return ret;
	}

/*	public static void main(String[] args) {
		
		double[][] mat = {{2.0, 5.0},{5.0, 2.0},{8.0,5.0}, {2.0, 4.0}, {6.0, 2.0}, {20.0, 20.0}};
		for (int i=0; i<mat.length; i++) {
			for (int j=0;j<2;j++) {
				System.out.print( mat[i][j]+" ");
			} 
			System.out.println();
		}
		System.out.println();
		System.out.println();
		ArrayList<Point> points = findCircle(new Matrix(mat), 100, 10, 80);
		for (Point p : points) {
			System.out.println(p.x + " " + p.y);
		}
	}*/
}
