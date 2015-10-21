/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Dad
 */
public class mapdata implements Serializable {
    private ArrayList<wall> walls;
    
    private ArrayList<room> rooms;
    
    
    public mapdata()
    {
        walls = new ArrayList<>();
        rooms = new ArrayList<>();
    }

    public ArrayList<wall> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<wall> walls) {
        this.walls = walls;
    }

    public ArrayList<room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<room> rooms) {
        this.rooms = rooms;
    }

    
}
