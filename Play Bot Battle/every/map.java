/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Dad
 */
public class map implements Serializable {
    
    private mapdata data;
    private ArrayList<unit> units;
    private ArrayList<weapon> weapons;
    
    private Random rand = new Random();
    
    public map( ArrayList<unit> units1 )
    {
        data = new mapdata();
        units = units1;
        weapons = new ArrayList<>();
        
        
        
    }
    
    
    public void step(Graphics g) {
        
        
        
        
        
        int xa = units.get(units.get(0).getPanel1().getMainplayernum()).getData().getX();
        int ya = units.get(units.get(0).getPanel1().getMainplayernum()).getData().getY();
        
//        int xa = 10;
//        int ya = 10;
        
        graphicscase g2d = new graphicscase((Graphics2D) g,-xa + 350,-ya + 350);
        
//        g2d.translate(xa, ya);
        
        for ( int c = 0; c < data.getRooms().size(); c++ ) {
            data.getRooms().get(c).step(g2d);
        }
        
        
        
        for ( int c = 0; c < data.getWalls().size(); c++ ) {
            data.getWalls().get(c).step(g2d);
        }
        
        for ( int c = 0; c < units.size(); c++ ) {
            units.get(c).step(g2d);
        }
        
        
        if ( weapons.size() > 0 ) {
            
            for ( int c = 0; c < weapons.size(); c++ ) {
                weapons.get(c).step(g2d);
                
            }
        }
        
        
        
        g2d.getG2d().drawImage(g2d.side_barimage.getImage(), units.get(units.get(0).getPanel1().getMainplayernum()).getData().getX() + 350, units.get(units.get(0).getPanel1().getMainplayernum()).getData().getY() - 355, 100, 700, null);
            
        g2d.getG2d().drawImage(g2d.exitimage.getImage(), units.get(units.get(0).getPanel1().getMainplayernum()).getData().getX() + 355, units.get(units.get(0).getPanel1().getMainplayernum()).getData().getY() - 345, 90, 50, null);

        g.setColor(constants.darkred);
        g.fillRect(units.get(units.get(0).getPanel1().getMainplayernum()).getData().getX() - 350, units.get(units.get(0).getPanel1().getMainplayernum()).getData().getY() - 350, 100, 20);

        g.setColor(Color.red);

        double fraction = (double) units.get(units.get(0).getPanel1().getMainplayernum()).getData().getLife() / (double) units.get(units.get(0).getPanel1().getMainplayernum()).getData().getMaxlife();

        g.fillRect(units.get(units.get(0).getPanel1().getMainplayernum()).getData().getX() - 350, units.get(units.get(0).getPanel1().getMainplayernum()).getData().getY() - 350, (int) (100 * fraction), 20);
        
        
        g.setColor(Color.black);
        g.drawString(" " + units.get(units.get(0).getPanel1().getMainplayernum()).getData().damagedone, units.get(units.get(0).getPanel1().getMainplayernum()).getData().getX() + 365, units.get(units.get(0).getPanel1().getMainplayernum()).getData().getY() - 148);
        
        
        g.setColor(Color.black);
        g.drawString(" " + units.get(units.get(0).getPanel1().getMainplayernum()).getData().kills, units.get(units.get(0).getPanel1().getMainplayernum()).getData().getX() + 365, units.get(units.get(0).getPanel1().getMainplayernum()).getData().getY() - 18);
        
        
        
//        g2d.translate(-xa, -ya);
    }
    
    public void setData( mapdata d )
    {
        data = d;
    }

    public ArrayList<weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<weapon> weapons) {
        this.weapons = weapons;
    }

    public mapdata getData() {
        return data;
    }
    
    
}
