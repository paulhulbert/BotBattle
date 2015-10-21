/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.awt.Color;
import java.io.Serializable;


/**
 *
 * @author Dad
 */
public class weapon implements Serializable {
    
    private int user;
    private int attack;
    private int speed;
    private double angle;
    private int x;
    private int y;

    public weapon(int user, int attack, int speed, double angle, int x, int y) {
        this.user = user;
        this.attack = attack;
        this.speed = speed;
        this.angle = angle;
        this.x = x;
        this.y = y;
    }

    
    
    public void step( graphicscase g )
    {
//        movement();
        
        paintit(g);
    }
    
    public void paintit( graphicscase g )
    {
        if ( attack == 9 ) {
            g.getG2d().drawImage(g.bad_shotimage.getImage(), x - 10, y - 10, 20, 20, null);
            return;
        }
        if ( attack < 0 ) {
            g.getG2d().drawImage(g.lifeimage.getImage(), x - 10, y - 10, 20, 20, null);
            return;
        }
//        g.fillOval(x - 10, y-10, 20, 20);
        
        g.getG2d().drawImage(g.good_shotimage.getImage(), x - 10, y - 10, 20, 20, null);
    }
    
    
    public void movement()
    {
        
        double xmoved = speed * Math.cos(angle);
        double ymoved = speed * Math.sin(angle);
        
        int xmove = (int) xmoved;
        int ymove = (int) ymoved;
        
        x += xmove;
        y += ymove;
        
        
        
//        int movexdir = 0;
//        int moveydir = 0;
//        boolean angled = false;
//        double anglednum = 1;
        
//        if ( goingleft ) {
//            data.setX(data.getX() - speed);
//        }
//        if ( goingright ) {
//            data.setX(data.getX() + speed);
//        }
//        if ( goingup ) {
//            data.setY(data.getY() - speed);
//        }
//        if ( goingdown ) {
//            data.setY(data.getY() + speed);
//        }
        
//        if ( goingleft || goingright ) {
//            if ( goingup || goingdown ) {
//                angled = true;
//                anglednum = Math.sqrt(2);
//            }
//        }
//        
//        if ( goingleft && !goingright ) {
//            if ( angled ) {
//                movexdir = -1;
//            } else {
//                movexdir = -1;
//            }
//        }
//        if ( !goingleft && goingright ) {
//            if ( angled ) {
//                movexdir = 1;
//            } else {
//                movexdir = 1;
//            }
//        }
//        if ( !goingup && goingdown ) {
//            if ( angled ) {
//                moveydir = 1;
//            } else {
//                moveydir = 1;
//            }
//        }
//        if ( goingup && !goingdown ) {
//            if ( angled ) {
//                moveydir = -1;
//            } else {
//                moveydir = -1;
//            }
//        }
//        
//        
//        
//        for ( int c = 0; c < speed / anglednum; c++ ) {
//            if ( mainmap.canmoveinto(data.getX() + movexdir, data.getY(), playernum) ) {
//                data.setX(data.getX() + movexdir);
//            }
//            if ( mainmap.canmoveinto(data.getX(), data.getY() + moveydir, playernum) ) {
//                data.setY(data.getY() + moveydir);
//            }
//        }
        
        
        
        
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
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
