/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Dad
 */
public class graphicscase {
    
    private int offsetx = 0;
    private int offsety = 0;
    
    private Graphics2D g2d;

    public graphicscase(Graphics2D g2d, int offsetx, int offsety) {
        this.g2d = g2d;
        
        this.offsetx = offsetx;
        this.offsety = offsety;
        
        this.g2d.translate(offsetx, offsety);
    }
    
    public void setColor( Color c )
    {
        g2d.setColor(c);
        
    }
    
    public void fillRect( int x, int y, int width, int height )
    {
        g2d.fillRect(x, y, width, height);
    }
    
    public void drawRect( int x, int y, int width, int height )
    {
        g2d.drawRect(x, y, width, height);
    }
    
    public void drawOval( int x, int y, int width, int height )
    {
        g2d.drawOval(x, y, width, height);
    }
    
    public void fillOval( int x, int y, int width, int height )
    {
        g2d.fillOval(x, y, width, height);
    }
    
    public void rotate( double theta )
    {
        g2d.rotate(theta);
    }
    
    public void translate( int x, int y )
    {
        g2d.translate(x, y);
        
    }

    
    
    
    
    
    
    
    
    
    
    
    public Graphics2D getG2d() {
        return g2d;
    }

    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public int getOffsetx() {
        return offsetx;
    }

    public void setOffsetx(int offsetx) {
        this.offsetx = offsetx;
    }

    public int getOffsety() {
        return offsety;
    }

    public void setOffsety(int offsety) {
        this.offsety = offsety;
    }
    
    
}
