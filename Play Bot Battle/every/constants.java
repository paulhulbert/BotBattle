/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.awt.Color;

/**
 *
 * @author Dad
 */
public class constants {
    public final static Color darkred = new Color( 180, 0, 0 );
    public final static Color mainunitcolor = new Color( 0, 255, 255, 100 );
    public final static Color mainunitoutline = new Color( 0, 187, 187, 100 );
    
    /**Finds and returns the positive angle between two objects.  Angle is done from object 1 to object 2*/
    public static double findangle( int x1, int y1, int x2, int y2 ) {
        
        if ( x1 == x2 ) {
            if ( y1 > y2 ) {
                return 3*Math.PI / 2;
            } else {
                return Math.PI / 2;
            }
        }
        
        
        if ( y1 == y2 ) {
            if ( x1 > x2 ) {
                return Math.PI;
            } else {
                return 0;
            }
        }
        
        double preangle = Math.atan(slope(x1,y1,x2,y2));
        
        double finalangle = 0;
        
        if ( x1 < x2 ) {
            
            if ( y1 < y2 ) {
                finalangle = preangle;
            }else {
                finalangle = 2*Math.PI + preangle;
            }
            
        } else {
            if ( y1 < y2 ) {
                finalangle = Math.PI + preangle;
            }else {
                finalangle = Math.PI + preangle;
            }
        }
        return finalangle;
        
    }
    
    /**If slope is undefined, this will throw divide by zero exception*/
    public static double slope( int x1, int y1, int x2, int y2 ) {
        
        double xa = (double) x1;
        double xb = (double) x2;
        double ya = (double) y1;
        double yb = (double) y2;
        
        return ( yb - ya ) / ( xb - xa );
        
    }
    
    
    public static double distance( int x1, int y1, int x2, int y2 ) {
        
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        
    }
    
}
