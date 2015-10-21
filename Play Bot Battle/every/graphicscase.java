/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Dad
 */
public class graphicscase {
    
    private int offsetx = 0;
    private int offsety = 0;
    
    public ImageIcon bag1image = new ImageIcon( getClass().getResource("bad_robot.png") );
    public ImageIcon main1image = new ImageIcon( getClass().getResource("robot1.png") );
    
    public ImageIcon bad_shotimage = new ImageIcon( getClass().getResource("bad_shot.png") );
    public ImageIcon good_shotimage = new ImageIcon( getClass().getResource("good_shot.png") );
    public ImageIcon lifeimage = new ImageIcon( getClass().getResource("life.png") );
    
    public ImageIcon exitimage = new ImageIcon( getClass().getResource("exit.png") );
    public ImageIcon side_barimage = new ImageIcon( getClass().getResource("side_bar.png") );
    
//    public ImageIcon floor1 = new ImageIcon( getClass().getResource("floor1.png") );
//    public ImageIcon wall1 = new ImageIcon( getClass().getResource("wall1.png") );
    
    public ArrayList<ImageIcon> floor = new ArrayList<>();
    public ArrayList<ImageIcon> wall = new ArrayList<>();
    public ArrayList<ImageIcon> fire = new ArrayList<>();
    
    private Graphics2D g2d;

    public graphicscase(Graphics2D g2d, int offsetx, int offsety) {
        this.g2d = g2d;
        
        for ( int c = 0; c < 6; c++ ) {
            floor.add(new ImageIcon( getClass().getResource("floor" + c + ".png") ));
            wall.add(new ImageIcon( getClass().getResource("wall" + c + ".png") ));
        }
        
        for ( int c = 1; c < 11; c++ ) {
            fire.add(new ImageIcon( getClass().getResource("fire" + c + ".png")));
        }
        
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

    public void drawString( String s, int x, int y )
    {
        g2d.drawString(s, x, y);
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
