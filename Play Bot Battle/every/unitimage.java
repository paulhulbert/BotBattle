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
        super();
        playernum = pnum;
    }
    
    public void paintunit( graphicscase g, int x, int y, double angle, boolean ismain, int imagenumber, unitdata data, int ondeadanimation )
    {
        graphicscase g2d = g;
        
        g2d.translate(x, y);
        
        g2d.rotate(angle);
        
        if ( ismain ) {
            g2d.setColor(constants.mainunitcolor);
            g2d.fillOval(-35, -35, 70, 70);
            g2d.setColor(constants.mainunitoutline);
            g2d.drawOval(-35, -35, 70, 70);
            
        }
        
        
        if ( ondeadanimation < 5 ) {
            if ( imagenumber == 0 ) {
                g.getG2d().drawImage(g.main1image.getImage(), -25, -25, 50, 50, null);
            }
            if ( imagenumber == 1 ) {
                g.getG2d().drawImage(g.bag1image.getImage(), -25, -25, 50, 50, null);
            }
        }
        
        
        if ( ondeadanimation > 0 ) {
            g.getG2d().drawImage(g.fire.get(ondeadanimation - 1).getImage(), -50, -50, 100, 100, null);
        }
        
        g2d.rotate(-angle);
        
        
        
        g2d.translate(-x, -y);
        
        
    }
    
}
