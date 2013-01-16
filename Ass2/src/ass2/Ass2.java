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
    
    public static void putline(Graphics g, int x1, int y1, int x2, int y2){
        g.setColor(Color.RED);
        g.drawLine(x1, y1, x2, y2);
    }
}
