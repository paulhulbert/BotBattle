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
        
        
        if ( sideofbaseroom == 1 ) {
            downroom = baseroom;
            baseroom.uproom = this;
            baseroom.hasuproom = true;
            roomlistx = baseroom.getRoomlistx();
            roomlisty = baseroom.getRoomlisty() - 1;
            x = baseroom.getX();
            y = baseroom.getY() - 700;
        }
        if ( sideofbaseroom == 3 ) {
            uproom = baseroom;
            baseroom.downroom = this;
            baseroom.hasdownroom = true;
            roomlistx = baseroom.getRoomlistx();
            roomlisty = baseroom.getRoomlisty() + 1;
            x = baseroom.getX();
            y = baseroom.getY() + 700;
        }
        if ( sideofbaseroom == 2 ) {
            leftroom = baseroom;
            baseroom.rightroom = this;
            baseroom.hasrightroom = true;
            roomlistx = baseroom.getRoomlistx() + 1;
            roomlisty = baseroom.getRoomlisty();
            x = baseroom.getX() + 700;
            y = baseroom.getY();
        }
        if ( sideofbaseroom == 4 ) {
            rightroom = baseroom;
            baseroom.leftroom = this;
            baseroom.hasleftroom = true;
            roomlistx = baseroom.getRoomlistx() - 1;
            roomlisty = baseroom.getRoomlisty();
            x = baseroom.getX() - 700;
            y = baseroom.getY();
        }
        
        
        
        height = 700;
        width = 700;
        
        walls.add( new wall( x, y, 300, 20, new Color(roomcolor, 0, 0), Color.blue ) );
            
        walls.add( new wall( x + 400, y, 300, 20, new Color(roomcolor, 0, 0), Color.blue ) );

        walls.add( new wall( x, y + 680, 300, 20, new Color(roomcolor, 0, 0), Color.blue ) );

        walls.add( new wall( x + 400, y + 680, 300, 20, new Color(roomcolor, 0, 0), Color.blue ) );

        walls.add( new wall( x, y, 20, 300, new Color(roomcolor, 0, 0), Color.blue ) );

        walls.add( new wall( x, y + 400, 20, 300, new Color(roomcolor, 0, 0), Color.blue ) );

        walls.add( new wall( x + 680, y, 20, 300, new Color(roomcolor, 0, 0), Color.blue ) );

        walls.add( new wall( x + 680, y + 400, 20, 300, new Color(roomcolor, 0, 0), Color.blue ) );
        
        
        
        room temproom;
        if ( !hasuproom ) {
            temproom = mainmap.findroomatspot(roomlistx, roomlisty - 1);
            if ( temproom != null ) {
                uproom = temproom;
                hasuproom = true;
                temproom.hasdownroom = true;
                temproom.downroom = this;
            }
        }
        
        if ( !hasdownroom ) {
            temproom = mainmap.findroomatspot(roomlistx, roomlisty + 1);
            if ( temproom != null ) {
                downroom = temproom;
                hasdownroom = true;
                temproom.hasuproom = true;
                temproom.uproom = this;
            }
        }
        
        if ( !hasleftroom ) {
            temproom = mainmap.findroomatspot(roomlistx - 1, roomlisty);
            if ( temproom != null ) {
                leftroom = temproom;
                hasleftroom = true;
                temproom.hasrightroom = true;
                temproom.rightroom = this;
            }
        }
        
        if ( !hasrightroom ) {
            temproom = mainmap.findroomatspot(roomlistx + 1, roomlisty);
            if ( temproom != null ) {
                rightroom = temproom;
                hasrightroom = true;
                temproom.hasleftroom = true;
                temproom.leftroom = this;
            }
        }
        
        
        
        
        
        
        
        int terrainxmin = x + 90;
        int terrainxmax = x + 610;
        int terrainymin = y + 90;
        int terrainymax = y + 610;
        
        int amountofblocks = rand.nextInt(7);
        
        for ( int c = 0; c < amountofblocks; c++ ) {
            
            
            
            int xofblock = terrainxmin + rand.nextInt(500);
            int yofblock = terrainymin + rand.nextInt(500);
            
            int xspace = terrainxmax - xofblock;
            int yspace = terrainymax - yofblock;
            
            int widthofblock = 20 + rand.nextInt(xspace - 20);
            int heightofblock = 20 + rand.nextInt(yspace - 20);
            
            
            walls.add( new wall( xofblock, yofblock, widthofblock, heightofblock, new Color(roomcolor, 0, 0), Color.red ) );
        }
        
        
        
        
        
        if ( walls.isEmpty() ) {
            return;
        }
        
        for ( int c = 0; c < walls.size(); c++ ) {
            mapwalls.add(walls.get(c));
        }
        
    }
    
    public void step( graphicscase g )
    {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);
    }
    
    
    
    
    private void generatewalls()
    {
        
        /**1 = up, 2 = right, 3 = down, 4 = left*/
        int sideoffirstroom = 0;
        
        if ( rooms.isEmpty() ) {
            isfirstroom = true;
            x = -350;
            y = -350;
            width = 700;
            height = 700;
            
            roomlistx = 0;
            roomlisty = 0;
            
            walls.add( new wall( -350, -350, 300, 20, new Color(roomcolor, 0, 0), Color.blue ) );
            
            walls.add( new wall( 50, -350, 300, 20, new Color(roomcolor, 0, 0), Color.blue ) );
            
            walls.add( new wall( -350, 330, 300, 20, new Color(roomcolor, 0, 0), Color.blue ) );
            
            walls.add( new wall( 50, 330, 300, 20, new Color(roomcolor, 0, 0), Color.blue ) );
            
            walls.add( new wall( -350, -350, 20, 300, new Color(roomcolor, 0, 0), Color.blue ) );
            
            walls.add( new wall( -350, 50, 20, 300, new Color(roomcolor, 0, 0), Color.blue ) );
            
            walls.add( new wall( 330, -350, 20, 300, new Color(roomcolor, 0, 0), Color.blue ) );
            
            walls.add( new wall( 330, 50, 20, 300, new Color(roomcolor, 0, 0), Color.blue ) );
            
            
            
        } 
        
        if ( walls.isEmpty() ) {
            return;
        }
        
        for ( int c = 0; c < walls.size(); c++ ) {
            mapwalls.add(walls.get(c));
        }
        
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
