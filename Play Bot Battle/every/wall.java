/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

/**
 *
 * @author Dad
 */
public class wall implements Serializable {
    
    private int x;
    private int y;
    private int width;
    private int height;
    private Color fill;
    private Color edge;
    
    /**Width is distance in the x-axis, Height is distance in the y-axis.*/
    public wall(int x, int y, int width, int height, Color fill, Color edge) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fill = fill;
        this.edge = edge;
    }

    public void step( graphicscase g )
    {
//        g.setColor(fill);
//        g.fillRect(x, y, width, height);
//        g.setColor(edge);
//        g.drawRect(x, y, width, height);
        
        if ( fill.getRed() > 6 ) {
            return;
        }
        g.getG2d().drawImage(g.wall.get(fill.getRed()).getImage(), x, y, width, height, null);
        
        
        if ( edge.getRed() == 255 ) {
            g.setColor(Color.black);
            g.drawRect(x, y, width, height);
        }
    }
    
    
    
    /**Returns the distance from point x,y to the closest point on the wall.*/
    public double distancefromwall( int x, int y ) {
        
        if ( x > this.x && x < this.x + width ) {
            if ( y < this.y ) {
                return this.y - y;
            } else {
                return y - (this.y + this.height);
            }
        }
        
        
        
        if ( y > this.y && y < this.y + height ) {
            if ( x < this.x ) {
                return this.x - x;
            } else {
                return x - (this.x + this.width);
            }
        }
        
        double closest;
        
        closest = constants.distance(x, y, this.x, this.y);
        
        if ( closest > constants.distance(x - width, y, this.x, this.y) ) {
            closest = constants.distance(x - width, y, this.x, this.y);
        }
        
        if ( closest > constants.distance(x - width, y - height, this.x, this.y) ) {
            closest = constants.distance(x - width, y - height, this.x, this.y);
        }
        
        if ( closest > constants.distance(x, y - height, this.x, this.y) ) {
            closest = constants.distance(x, y - height, this.x, this.y);
        }
        
        return closest;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
