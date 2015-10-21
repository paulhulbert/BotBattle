/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.io.Serializable;


/**
 *
 * @author Dad
 */
public class unitdata implements Serializable {
    
    private int x;
    private int y;
    
    private int mousex = 0;
    private int mousey = 0;
    
    private int playernum;
    
    
    private int imagenumber;
    
    private int life;
    private int maxlife;
    private int ondeadmousex;
    private int ondeadmousey;
    
    public int kills = 0;
    public int damagedone = 0;
    
    public unitdata( int x, int y, int playernum )
    {
        super();
        this.x = x;
        this.y = y;
        
        this.playernum = playernum;
        
    }

    public int getImagenumber() {
        return imagenumber;
    }

    public void setImagenumber(int imagenumber) {
        this.imagenumber = imagenumber;
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

    public int getMousex() {
        return mousex;
    }

    public void setMousex(int mousex) {
        this.mousex = mousex;
    }

    public int getMousey() {
        return mousey;
    }

    public void setMousey(int mousey) {
        this.mousey = mousey;
    }

public int getPlayernum() {
        return playernum;
    }

    public void setPlayernum(int playernum) {
        this.playernum = playernum;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getMaxlife() {
        return maxlife;
    }

    public void setMaxlife(int maxlife) {
        this.maxlife = maxlife;
    }

    public int getOndeadmousex() {
        return ondeadmousex;
    }

    public void setOndeadmousex(int ondeadmousex) {
        this.ondeadmousex = ondeadmousex;
    }

    public int getOndeadmousey() {
        return ondeadmousey;
    }

    public void setOndeadmousey(int ondeadmousey) {
        this.ondeadmousey = ondeadmousey;
    }


    
}
