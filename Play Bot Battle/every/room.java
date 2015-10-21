/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Dad
 */
public class room implements Serializable {
    
    private Random rand = new Random();
    
    
    /**The first room is 0, 0.  the room left of that would be -1, 0*/
    public int roomlistx;
    
    public int roomlisty;
    
    private ArrayList<wall> walls;
    private ArrayList<wall> mapwalls;
    private ArrayList<room> rooms;
    
    
    public boolean hasuproom = false;
    public boolean hasdownroom = false;
    public boolean hasrightroom = false;
    public boolean hasleftroom = false;
    
    public room uproom;
    public room downroom;
    public room leftroom;
    public room rightroom;
    
    
    private int roomcolor;
    private boolean isfirstroom = false;
    
    private int x = 0;
    private int y = 0;
    private int width = 100;
    private int height = 100;
    
    private boolean targetchosen = false;
    
    private boolean targetroomchosen = false;
    

    public room(map mainmap, int roomcolor, boolean has_targetroom) {
        this.mapwalls = mainmap.getData().getWalls();
        this.rooms = mainmap.getData().getRooms();
        this.roomcolor = roomcolor;
        
        
        walls = new ArrayList<>();
        
        targetroomchosen = has_targetroom;
        
        
        if ( !has_targetroom ) {
            generatewalls();
        }
        
    }
    
    public room(map mainmap, int roomcolor)
    {
        this(mainmap, roomcolor, false);
    }
    
    
    
    
    /**
     @param sideofbaseroom 1:up 2:right 3:down 4:left
     */
    public room( map mainmap, int roomcolor, room baseroom, int sideofbaseroom )
    {
        this(mainmap, roomcolor, true);
        
        
    }
    
    public void step( graphicscase g )
    {
//        g.setColor(Color.DARK_GRAY);
//        g.fillRect(x, y, width, height);
        
        g.getG2d().drawImage(g.floor.get(roomcolor).getImage(), x, y, width, height, null);
    }
    
    
    
    
    private void generatewalls()
    {
        
        
        
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isIsfirstroom() {
        return isfirstroom;
    }

    public void setIsfirstroom(boolean isfirstroom) {
        this.isfirstroom = isfirstroom;
    }

    public int getRoomcolor() {
        return roomcolor;
    }

    public void setRoomcolor(int roomcolor) {
        this.roomcolor = roomcolor;
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

    public int getRoomlistx() {
        return roomlistx;
    }

    public void setRoomlistx(int roomlistx) {
        this.roomlistx = roomlistx;
    }

    public int getRoomlisty() {
        return roomlisty;
    }

    public void setRoomlisty(int roomlisty) {
        this.roomlisty = roomlisty;
    }
    
}
