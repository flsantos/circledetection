/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ass2;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.Graphics;
/**
 *
 * @author tiagopereira
 */
public class Ass2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    }
    
    public static void putline(Graphics g, int x1, int y1, int x2, int y2, Color color){
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
    
    public static void putcircle(Graphics g, int x, int y, int r){
        g.drawOval(x-r, y-r, 2*r, 2*r);
    }
    
    
    public static void putpoint(Graphics g, int x,  int y, Color c) {
    	Ass2.putline(g, x-2, y, x+2, y, c);
		Ass2.putline(g, x, y-2, x, y+2, c);
    }
    
    
}
