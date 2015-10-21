/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author Dad
 */
public class unitimage implements Serializable {
    
    public int playernum;
    
    public unitimage( int pnum )
    {
        playernum = pnum;
    }
    
    public void paintunit( graphicscase g, int x, int y, double angle, boolean ismain, int imagenumber, unitdata data )
    {
        graphicscase g2d = g;
        
        g2d.translate(x, y);
        
        g2d.rotate(angle);
        
        
        
        g.setColor(Color.red);
        g.fillOval( -25, -25, 50, 50);
        g.setColor(Color.blue);
        g.fillRect(20, -5, 5, 10);
        
        g2d.rotate(-angle);
        
        if ( ismain ) {
            g.setColor(Color.green);
            g.fillRect(250, -350, 100, 50);
            
            g.setColor(constants.darkred);
            g.fillRect(-350, -350, 100, 20);
        }
        
        g2d.translate(-x, -y);
        
        
    }
    
}
